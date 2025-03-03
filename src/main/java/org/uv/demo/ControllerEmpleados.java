package org.uv.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
// hace que se cree el rest controller para que se hagan las operaciones pack de invocacion de rest api
@RestController
@RequestMapping("/empleados")

public class ControllerEmpleados {
     // singleton genera una variable de repository empleado, nosotros no instanciamos el repositorio sino spring boot lo hace 
    @Autowired
RepositoryEmpleados repositoryEmpleado;
    
    @Autowired
RepositoryDepartamentos repositoryDepartamento;
    
    // lo responde get 
    @GetMapping()
    public List<Empleado> list() {
        return repositoryEmpleado.findAll();
    }
    
    // regresa respuesta de entidad
    // optional es tema de hibernate con jpa, por si no regresa algo
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable long id) {
        
       Optional<Empleado> emp = repositoryEmpleado.findById(id);
       if (!emp.isPresent()){
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok(emp.get());
    }
    // query path 
    // terminar put y delete 
    // pathvariable = variable de la ruta. 
    // recupero, modifico y publico
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Empleado empleado) {
        Optional<Empleado> resEmp = repositoryEmpleado.findById(id);
        if (!resEmp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Empleado empleadoExisted = resEmp.get();
        empleadoExisted.setNombre(empleado.getNombre());
        empleadoExisted.setTelefono(empleado.getTelefono());
        empleadoExisted.setDireccion(empleado.getDireccion());
        
        repositoryEmpleado.save(empleadoExisted);
        
               
        return new ResponseEntity<>(empleadoExisted, HttpStatus.OK);
    }
    // el request toma y convierte json a Empleado pojo , el api de web service lo hace automaticamente
    @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado entrada) {
        
        
        
        Optional<Departamento> depto= 
                repositoryDepartamento.findById(entrada.getDepto().getClave());
        if (depto.isPresent()){
            entrada.setDepto(depto.get());
            Empleado empNew= repositoryEmpleado.save(entrada);
            
        }
        
        return ResponseEntity.ok();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable long id) {
        Optional<Empleado> emp = repositoryEmpleado.findById(id);
        if (!emp.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repositoryEmpleado.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
