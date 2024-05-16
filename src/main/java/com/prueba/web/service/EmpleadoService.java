package com.prueba.web.service;

import java.util.List;
import java.util.Optional;

import com.prueba.web.DTO.EmpleadoDTO;
import com.prueba.web.entity.Empleado;

public interface EmpleadoService {
	public List<EmpleadoDTO> listarEmpleados();
	public void guardarEmpleado(Empleado empleado);
	public void editarEmpleado(Empleado empleado);
	public void eliminarEmpleado(Empleado empleado);
	public Optional<Empleado> encontrarEmpleado(Long id);
}
