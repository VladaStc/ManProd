package edu.man.prod.repository;

import edu.man.prod.domain.OperacijeURadnomNalogu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OperacijeURadnomNalogu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperacijeURadnomNaloguRepository extends JpaRepository<OperacijeURadnomNalogu, Long> {

}
