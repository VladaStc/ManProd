package edu.man.prod.repository;

import edu.man.prod.domain.KonstruktivnaSastavnica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KonstruktivnaSastavnica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KonstruktivnaSastavnicaRepository extends JpaRepository<KonstruktivnaSastavnica, Long> {

}
