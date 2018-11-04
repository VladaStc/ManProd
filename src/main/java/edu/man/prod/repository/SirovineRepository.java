package edu.man.prod.repository;

import edu.man.prod.domain.Sirovine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sirovine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SirovineRepository extends JpaRepository<Sirovine, Long> {

}
