package edu.man.prod.repository;

import edu.man.prod.domain.RadnoMesto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RadnoMesto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadnoMestoRepository extends JpaRepository<RadnoMesto, Long> {

}
