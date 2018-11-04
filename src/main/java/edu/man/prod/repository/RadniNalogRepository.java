package edu.man.prod.repository;

import edu.man.prod.domain.RadniNalog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RadniNalog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadniNalogRepository extends JpaRepository<RadniNalog, Long> {

}
