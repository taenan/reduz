package com.taenan.reduz.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/invalid-data", "Dados inválidos"),
	ACCESS_DENIED("/access-denied", "Acesso negado"),
	SYSTEM_ERROR("/system-error", "Erro de sistema"),
	INVALID_PARAMETER("/invalid-parameter", "Parâmetro inválido"),
	NOT_UNDERSTANDABLE_MESSAGE("/not-understandable-message", "Mensagem incompreensível"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado"),
	ENTITY_IN_USE("/entity-in-use", "Entidade em uso"),
	DOMAIN_ERROR("/domain-error", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = path;
		this.title = title;
	}
	
}
