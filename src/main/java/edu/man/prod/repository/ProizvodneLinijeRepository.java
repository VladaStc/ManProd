package edu.man.prod.repository;

import edu.man.prod.domain.ProizvodneLinije;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProizvodneLinije entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProizvodneLinijeRepository extends JpaRepository<ProizvodneLinije, Long> {

}
