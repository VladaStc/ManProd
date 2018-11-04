package edu.man.prod.repository;

import edu.man.prod.domain.Radnici;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Radnici entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadniciRepository extends JpaRepository<Radnici, Long> {

}
