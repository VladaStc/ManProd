package edu.man.prod.repository;

import edu.man.prod.domain.MerniAlati;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MerniAlati entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MerniAlatiRepository extends JpaRepository<MerniAlati, Long> {

}
