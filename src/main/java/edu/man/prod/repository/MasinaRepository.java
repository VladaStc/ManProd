package edu.man.prod.repository;

import edu.man.prod.domain.Masina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Masina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasinaRepository extends JpaRepository<Masina, Long> {

}
