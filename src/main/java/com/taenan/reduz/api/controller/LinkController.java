package com.taenan.reduz.api.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taenan.reduz.api.model.LinkModel;
import com.taenan.reduz.api.model.input.LinkInput;
import com.taenan.reduz.domain.service.LinkService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/links", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinkController {

	private LinkService linkService;

	@GetMapping
	public CollectionModel<LinkModel> findAll() {
		return linkService.findAll();
	}
	
	@GetMapping("/{id}")
	public LinkModel findById(@PathVariable @Positive @NotNull Long id) {
		return linkService.findById(id);
	}
	
	@GetMapping("/public/findbyslug")
	public LinkModel findBySlug(@RequestParam("slug") String slug) {
		return linkService.findBySlug(slug);
	}
	
	@GetMapping("loadurl")
	public LinkModel loadUrl(@RequestParam("url") String url) {
		return linkService.loadUrl(url);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public LinkModel create(@RequestBody @Valid LinkInput linkInput) {
		return linkService.create(linkInput);
	}
	
	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
		linkService.delete(id);
    }
	
	@PutMapping("/public/{id}/increasecounter")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void increaseCounter(@PathVariable @Positive @NotNull Long id) {
		System.out.println("increasing counter");
		linkService.increaseCounter(id);
    }
	
	
}
