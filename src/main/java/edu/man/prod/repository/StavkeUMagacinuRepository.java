package edu.man.prod.repository;

import edu.man.prod.domain.StavkeUMagacinu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StavkeUMagacinu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StavkeUMagacinuRepository extends JpaRepository<StavkeUMagacinu, Long> {

}
