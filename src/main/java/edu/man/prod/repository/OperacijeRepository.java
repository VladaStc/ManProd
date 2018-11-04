package edu.man.prod.repository;

import edu.man.prod.domain.Operacije;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Operacije entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperacijeRepository extends JpaRepository<Operacije, Long> {

}
