package com.prueba.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.web.DTO.EmpleadoDTO;
import com.prueba.web.entity.Departamento;
import com.prueba.web.entity.Empleado;
import com.prueba.web.service.DepartamentoService;
import com.prueba.web.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/empleados")
    public ResponseEntity<?> listaDeEmpleados(){
        HashMap<String, Object> respuesta = new HashMap<>();
        List<EmpleadoDTO> lista = empleadoService.listarEmpleados();
        try {
            if (lista.isEmpty()) {
                respuesta.put("validacion", "No hay empleados registrados");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
            }
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
	
	@PostMapping("/guardar")
	public ResponseEntity<?> insertarEmpleado(@RequestBody HashMap<String, Object> request){
		HashMap<String, String> respuesta = new HashMap<String, String>();
		try {
			String nombre = (String) request.get("nom");
			if (nombre == null || nombre.isBlank() || nombre.isEmpty()) {
				respuesta.put("validacion", "El nombre no debe estar vacio");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			String apellido = (String) request.get("ape");
			if (apellido == null || apellido.isBlank() || apellido.isEmpty()) {
				respuesta.put("validacion", "El apellido no debe estar vacio");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			String direc = (String) request.get("direc");
			if (direc == null || direc.isBlank() || direc.isEmpty()) {
				respuesta.put("validacion", "la direccion no debe estar vacia");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			Double salario = (Double) request.get("sal");
			if (salario <= 0) {
				respuesta.put("validacion", "El salario no puede ser menor o igual a 0");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			Integer departamento = (Integer) request.get("depart");
			Optional<Departamento> departamentoEncontrado = departamentoService.encontrarDepartamento(departamento);
			if (!departamentoEncontrado.isPresent()) {
				respuesta.put("validacion", "No existe ese departamento");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			} else if(departamento <= 0) {
				respuesta.put("validacion", "No puede ingresar menos de 0 ni 0 en el departamento");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			Empleado emp = new Empleado();
			emp.setNombre(nombre);
			emp.setApellido(apellido);
			emp.setDireccion(direc);
			emp.setSalario(salario);
			emp.setDepartamento(departamentoEncontrado.get());
			
			empleadoService.guardarEmpleado(emp);
			respuesta.put("mensaje", "Empleado guardado con exito");
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.put("error: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}
	
	@PostMapping("/editar/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody HashMap<String, Object> request) {
		HashMap<String, String> respuesta = new HashMap<String, String>();
		try {
			Optional<Empleado> ide = empleadoService.encontrarEmpleado(id);
			if (ide.isEmpty()) {
				respuesta.put("validacion", "No se encontro el usuario");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
			}
			
			Empleado emp = ide.get();
			
			String nombre = (String) request.get("nom");
			if (nombre == null || nombre.isBlank() || nombre.isEmpty()) {
				respuesta.put("validacion", "El nombre no debe estar vacio");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			String apellido = (String) request.get("ape");
			if (apellido == null || apellido.isBlank() || apellido.isEmpty()) {
				respuesta.put("validacion", "El apellido no debe estar vacio");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			String direc = (String) request.get("direc");
			if (direc == null || direc.isBlank() || direc.isEmpty()) {
				respuesta.put("validacion", "la direccion no debe estar vacia");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			Double salario = (Double) request.get("sal");
			if (salario <= 0) {
				respuesta.put("validacion", "El salario no puede ser menor o igual a 0");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
			
			Integer departamento = (Integer) request.get("depart");
			Optional<Departamento> departamentoEncontrado = departamentoService.encontrarDepartamento(departamento);
			if (!departamentoEncontrado.isPresent()) {
				respuesta.put("validacion", "No existe ese departamento");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			} else if(departamento <= 0) {
				respuesta.put("validacion", "No puede ingresar menos de 0 ni 0 en el departamento");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
			}
	
			emp.setNombre(nombre);
			emp.setApellido(apellido);
			emp.setDireccion(direc);
			emp.setSalario(salario);
			emp.setDepartamento(departamentoEncontrado.get());
			
			empleadoService.editarEmpleado(emp);
			respuesta.put("mensaje", "Empleado editado con exito");
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.put("error: ", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}
	
	@PostMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
		HashMap<String, String> respuesta = new HashMap<String, String>();
		try {
			Optional<Empleado> ide = empleadoService.encontrarEmpleado(id);
			if (ide.isEmpty()) {
				respuesta.put("validacion", "No se encontro el empleado");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
			}
			Empleado ideEmpleado = ide.get();
			empleadoService.eliminarEmpleado(ideEmpleado);
			respuesta.put("mensaje", "empleado eliminado con exito");
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
		}
	}
}
