package edu.man.prod.repository;

import edu.man.prod.domain.Odelenje;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Odelenje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OdelenjeRepository extends JpaRepository<Odelenje, Long> {

}
