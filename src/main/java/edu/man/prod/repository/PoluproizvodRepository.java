package edu.man.prod.repository;

import edu.man.prod.domain.Poluproizvod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Poluproizvod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoluproizvodRepository extends JpaRepository<Poluproizvod, Long> {

}
