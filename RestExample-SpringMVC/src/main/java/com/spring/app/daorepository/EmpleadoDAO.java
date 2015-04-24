package com.spring.app.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.entity.Empleado;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado,Long> {
}
