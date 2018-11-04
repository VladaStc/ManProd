package edu.man.prod.repository;

import edu.man.prod.domain.KupovniMaterijal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KupovniMaterijal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KupovniMaterijalRepository extends JpaRepository<KupovniMaterijal, Long> {

}
