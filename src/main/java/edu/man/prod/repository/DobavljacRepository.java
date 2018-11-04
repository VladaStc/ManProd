package edu.man.prod.repository;

import edu.man.prod.domain.Dobavljac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dobavljac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DobavljacRepository extends JpaRepository<Dobavljac, Long> {

}
