package com.taenan.reduz.domain.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.taenan.reduz.api.ResourceUriHelper;
import com.taenan.reduz.api.assembler.LinkInputDisassembler;
import com.taenan.reduz.api.assembler.LinkModelAssembler;
import com.taenan.reduz.api.model.LinkModel;
import com.taenan.reduz.api.model.input.LinkInput;
import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.exception.DomainException;
import com.taenan.reduz.domain.exception.EntityNotFoundException;
import com.taenan.reduz.domain.model.Link;
import com.taenan.reduz.domain.model.User;
import com.taenan.reduz.domain.model.repository.LinkRepository;
import com.taenan.reduz.domain.validator.UrlValidator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LinkService {

	private static final String GOOGLE_API = "https://www.google.com/s2/favicons?domain=";
	private static final String MSG_URL_EXISTS = "O link para a URL informada já existe.";

	private UserService userService;
	private LinkRepository linkRepository;
	private LinkModelAssembler linkModelAssembler;
	private LinkInputDisassembler linkInputDisassembler;

	public LinkModel findById(@Positive @NotNull Long id) {
		return linkModelAssembler.toModel(linkRepository.findByIdAndUser(id, userService.getUserLogged())
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(Link.class, id)));
	}

	public LinkModel loadUrl(@NotNull String url) {
		LinkModel linkModel = new LinkModel();

		HttpResponse<String> response;
		try {
			if (!UrlValidator.isValid(url)) {
				throw new DomainException("URL inválida");
			}
			response = HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create(url)).build(),
					HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new DomainException(String.format("Não foi possível obter resposta do site %s", url));
		}

		linkModel.setTitle(findTitle(response.body()));
		linkModel.setFavicon(findFavicon(url));

		return linkModel;
	}

	public LinkModel findBySlug(String slug) {
		return linkModelAssembler.toModel(linkRepository.findBySlug(slug)
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(Link.class)));
	}

	public CollectionModel<LinkModel> findAll() {
		User user = userService.getUserLogged();
		List<Link> links = linkRepository.findByUser(user, Sort.by(Sort.Direction.DESC, "id"));
		return linkModelAssembler.toCollectionModel(links);
	}

	public LinkModel create(@Valid LinkInput linkInput) {

		linkRepository.findByOriginalIgnoreCaseAndUser(linkInput.getOriginal(), userService.getUserLogged()).stream().findAny().ifPresent(c -> {
			throw new DomainException(MSG_URL_EXISTS);
		});

		Link link = linkInputDisassembler.toDomainObject(linkInput);
		link.generateSlug();
		link.setStatus(Status.ACTIVE);
		link.setUser(userService.getUserLogged());
		link = linkRepository.save(link);
		LinkModel linkModel = linkModelAssembler.toModel(link);

		ResourceUriHelper.addUriInResponseHeader(linkModel.getId());

		return linkModel;
	}

	public void delete(@Positive @NotNull Long id) {
		linkRepository.delete(linkRepository.findByIdAndUser(id, userService.getUserLogged())
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(Link.class, id)));
	}

	public void increaseCounter(@Positive @NotNull Long id) {
		Link link = linkRepository.findById(id)
				.orElseThrow(() -> EntityNotFoundException.throwIfEntityNotFound(Link.class, id));
		link.setCounter(link.getCounter() + 1);
		linkRepository.save(link);
	}

	private String findFavicon(String url) {
		String apiURL = String.format("%s%s&sz=128", GOOGLE_API, url);
		var rest = new RestTemplate();
		HttpEntity<String> requestEntity = new HttpEntity<String>("", new HttpHeaders());
		ResponseEntity<String> responseEntity = rest.exchange(apiURL, HttpMethod.GET, requestEntity, String.class);
		List<String> contentLocation = responseEntity.getHeaders().get("content-location");
		return contentLocation != null ? contentLocation.get(0) : null;
	}

	private String findTitle(String text) {
		if (text == null || text.isEmpty()) {
			return "Sem título";
		}

		final Pattern titleRegExp = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);
		final Matcher titleMatcher = titleRegExp.matcher(text);
		return titleMatcher.find() ? titleMatcher.group(1) : "Sem título";
	}

}
