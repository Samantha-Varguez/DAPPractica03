
package org.uv.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samantha
 */
@Repository
public interface RepositoryDepartamentos extends JpaRepository <Departamento, Long> {
    
}
