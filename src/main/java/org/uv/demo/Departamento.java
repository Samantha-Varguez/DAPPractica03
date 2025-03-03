
package org.uv.demo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(
            generator="departamentos_clave_seq", 
            strategy = GenerationType.SEQUENCE)
      @SequenceGenerator(name="departamentos_clave_seq", 
              sequenceName = "departamentos_clave_seq", 
              initialValue=1, 
              allocationSize=1)
    
    private Long clave;
    private String nombre;
    
    @OneToMany
    @JoinColumn(name = "clave")
    private List <Empleado> lstEmpleados;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
