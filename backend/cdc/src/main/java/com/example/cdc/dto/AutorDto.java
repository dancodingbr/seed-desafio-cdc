package com.example.cdc.dto;

import java.time.Instant;

import lombok.Builder;

@Builder
public record AutorDto(Instant instante, String email, String nome, String descricao) {
}
