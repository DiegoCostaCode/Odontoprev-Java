package com.example.Odontoprev_Java.DTO;

import jakarta.validation.constraints.NotNull;

public class CarterinhaRequestDTO {
    @NotNull
    private Long usuarioId;

    @NotNull
    private Long planoId;

    // Getters e setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }
}
