package com.prueba.web.DTO;

public class EmpleadoDTO {
	private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private Double salario;
    private String nombreDepartamento;
    
	public EmpleadoDTO() {
		super();
	}
	public EmpleadoDTO(Long id, String nombre, String apellido, String direccion, Double salario,
			String nombreDepartamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.salario = salario;
		this.nombreDepartamento = nombreDepartamento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
}
