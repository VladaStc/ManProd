package edu.man.prod.repository;

import edu.man.prod.domain.PomocniProizvod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PomocniProizvod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PomocniProizvodRepository extends JpaRepository<PomocniProizvod, Long> {

}
