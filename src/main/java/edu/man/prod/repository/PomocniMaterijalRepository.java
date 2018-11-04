package edu.man.prod.repository;

import edu.man.prod.domain.PomocniMaterijal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PomocniMaterijal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PomocniMaterijalRepository extends JpaRepository<PomocniMaterijal, Long> {

}
