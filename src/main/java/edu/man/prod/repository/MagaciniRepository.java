package edu.man.prod.repository;

import edu.man.prod.domain.Magacini;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Magacini entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagaciniRepository extends JpaRepository<Magacini, Long> {

}
