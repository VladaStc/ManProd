package edu.man.prod.repository;

import edu.man.prod.domain.Radionica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Radionica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadionicaRepository extends JpaRepository<Radionica, Long> {

}
