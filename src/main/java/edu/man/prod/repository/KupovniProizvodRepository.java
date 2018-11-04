package edu.man.prod.repository;

import edu.man.prod.domain.KupovniProizvod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KupovniProizvod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KupovniProizvodRepository extends JpaRepository<KupovniProizvod, Long> {

}
