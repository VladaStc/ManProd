package edu.man.prod.repository;

import edu.man.prod.domain.Komponenete;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Komponenete entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KomponeneteRepository extends JpaRepository<Komponenete, Long> {

}
