package org.uv.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepositoryEmpleados extends JpaRepository<Empleado,Long> {
    
}

