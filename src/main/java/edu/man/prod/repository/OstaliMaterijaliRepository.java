package edu.man.prod.repository;

import edu.man.prod.domain.OstaliMaterijali;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OstaliMaterijali entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OstaliMaterijaliRepository extends JpaRepository<OstaliMaterijali, Long> {

}
