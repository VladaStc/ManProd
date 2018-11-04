package edu.man.prod.repository;

import edu.man.prod.domain.ProizvodniPogoni;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProizvodniPogoni entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProizvodniPogoniRepository extends JpaRepository<ProizvodniPogoni, Long> {

}
