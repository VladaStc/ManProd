package edu.man.prod.repository;

import edu.man.prod.domain.Alati;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Alati entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlatiRepository extends JpaRepository<Alati, Long> {

}
