package edu.man.prod.repository;

import edu.man.prod.domain.Oprema;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Oprema entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpremaRepository extends JpaRepository<Oprema, Long> {

}
