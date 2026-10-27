package com.condominio.acceso.dto;

import com.condominio.acceso.model.Unidad;

public record UnidadResponse(Long id, String torre, String numero, boolean activa) {
    public static UnidadResponse from(Unidad u) {
        return new UnidadResponse(u.getId(), u.getTorre(), u.getNumero(), u.isActiva());
    }
}
