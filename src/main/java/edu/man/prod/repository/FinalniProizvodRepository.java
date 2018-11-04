package edu.man.prod.repository;

import edu.man.prod.domain.FinalniProizvod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FinalniProizvod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalniProizvodRepository extends JpaRepository<FinalniProizvod, Long> {

}
