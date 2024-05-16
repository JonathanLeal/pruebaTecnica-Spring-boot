package com.prueba.web.service;

import java.util.Optional;

import com.prueba.web.entity.Departamento;

public interface DepartamentoService {
	public Optional<Departamento> encontrarDepartamento(Integer id);
}
