package com.taenan.reduz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taenan.reduz.api.model.input.LinkInput;
import com.taenan.reduz.domain.model.Category;
import com.taenan.reduz.domain.model.Link;

@Component
public class LinkInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Link toDomainObject(LinkInput linkInput) {
		return modelMapper.map(linkInput, Link.class);
	}
	
	public void copyToDomainObject(LinkInput linkInput, Link link) {
		link.setCategory(new Category());
		
		modelMapper.map(linkInput, link);
	}

}
