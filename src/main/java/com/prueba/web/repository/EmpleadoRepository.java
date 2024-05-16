package com.prueba.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.web.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	@Query(value = "SELECT e.id, e.nombre, e.apellido, e.direccion, e.salario, d.nombreDepartamento " +
            "FROM empleado e " +
            "INNER JOIN departamento d ON e.departamento_id = d.id", nativeQuery = true)
    List<Object[]> listarEmpleados();
}
