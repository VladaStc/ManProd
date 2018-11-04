package edu.man.prod.repository;

import edu.man.prod.domain.Zahvati;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Zahvati entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZahvatiRepository extends JpaRepository<Zahvati, Long> {

}
