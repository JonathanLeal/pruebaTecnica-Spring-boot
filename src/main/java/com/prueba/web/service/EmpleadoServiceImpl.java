package com.prueba.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.web.DTO.EmpleadoDTO;
import com.prueba.web.entity.Empleado;
import com.prueba.web.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarEmpleados() {
        List<Object[]> resultados = empleadoRepository.listarEmpleados();
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();

        for (Object[] fila : resultados) {
            Long id = (Long) fila[0];
            String nombre = (String) fila[1];
            String apellido = (String) fila[2];
            String direccion = (String) fila[3];
            Double salario = (Double) fila[4];
            String nombreDepartamento = (String) fila[5];

            EmpleadoDTO empleadoDTO = new EmpleadoDTO(id, nombre, apellido, direccion, salario, nombreDepartamento);
            empleadosDTO.add(empleadoDTO);
        }

        return empleadosDTO;
    }

	@Override
	@Transactional
	public void guardarEmpleado(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	@Override
	@Transactional
	public void editarEmpleado(Empleado empleado) {
		Optional<Empleado> ide = empleadoRepository.findById(empleado.getId());
		Empleado ideEncontrado = ide.get();
		empleadoRepository.save(ideEncontrado);
	}

	@Override
	@Transactional
	public void eliminarEmpleado(Empleado empleado) {
		Optional<Empleado> ide = empleadoRepository.findById(empleado.getId());
		Empleado ideEncontrado = ide.get();
		empleadoRepository.delete(ideEncontrado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empleado> encontrarEmpleado(Long id) {
		Optional<Empleado> ide = empleadoRepository.findById(id);
		return ide;
	}

}
