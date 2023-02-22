package com.taenan.reduz.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.taenan.reduz.domain.enums.Status;
import com.taenan.reduz.domain.enums.converter.StatusConverter;
import com.taenan.reduz.domain.validator.ValidUrl;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@SQLDelete(sql = "UPDATE Link SET status = 'Inactive' WHERE id=?")
@Where(clause = "status <> 'Inactive'")
public class Link {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ValidUrl
	@NotBlank
	@NotNull
	@Column(nullable = false)
	private String original;

	@NotBlank
	@NotNull
	@Column(nullable = false)
	@Length(max = 7)
	private String slug;
	
	@Length(max = 255)
	private String title;

	@Length(max = 255)
	private String favicon;
	
	private int counter;

	@CreationTimestamp
	private OffsetDateTime createdAt;

	@NotNull
	@Column(length = 8, nullable = false)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.ACTIVE;

	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;

	public void generateSlug() {
		this.slug = UUID.randomUUID().toString().substring(0, 7);
	}

}
