package edu.man.prod.repository;

import edu.man.prod.domain.TransakcijeUMagacinu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransakcijeUMagacinu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransakcijeUMagacinuRepository extends JpaRepository<TransakcijeUMagacinu, Long> {

}
