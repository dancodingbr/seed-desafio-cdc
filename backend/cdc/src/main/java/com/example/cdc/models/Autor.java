package com.example.cdc.models;

import java.math.BigInteger;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

	public static final String EMAIL_REGEX = "^(.+)@(\\S+)$";

	@Id
	private BigInteger id;

	@NotNull(message = "Instante não pode ser nulo.")
	private Instant instante;
	
	@NotEmpty(message = "E-mail deve ser preenchido.")
	@Email(message = "E-mail está em formato inválido.", regexp = EMAIL_REGEX)
	private String email;
	
	@NotEmpty(message = "Nome deve ser preenchido.")
	private String nome;
	
	@NotEmpty(message = "Descrição deve ser preenchida.")
	@Size(max = 500)
	private String descricao;
	
}
