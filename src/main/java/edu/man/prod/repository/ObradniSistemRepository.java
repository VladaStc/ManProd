package edu.man.prod.repository;

import edu.man.prod.domain.ObradniSistem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ObradniSistem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObradniSistemRepository extends JpaRepository<ObradniSistem, Long> {

}
