package com.prueba.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.web.entity.Departamento;
import com.prueba.web.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Override
	@Transactional(readOnly = true)
	public Optional<Departamento> encontrarDepartamento(Integer id) {
		Optional<Departamento> depar = departamentoRepository.findById(id.longValue());
		return depar;
	}

}
