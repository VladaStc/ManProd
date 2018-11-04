package edu.man.prod.repository;

import edu.man.prod.domain.PotrosniMaterijal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PotrosniMaterijal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PotrosniMaterijalRepository extends JpaRepository<PotrosniMaterijal, Long> {

}
