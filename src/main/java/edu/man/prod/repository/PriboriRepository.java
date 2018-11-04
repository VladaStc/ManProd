package edu.man.prod.repository;

import edu.man.prod.domain.Pribori;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pribori entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriboriRepository extends JpaRepository<Pribori, Long> {

}
