package edu.man.prod.service;

import edu.man.prod.domain.OstaliMaterijali;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OstaliMaterijali.
 */
public interface OstaliMaterijaliService {

    /**
     * Save a ostaliMaterijali.
     *
     * @param ostaliMaterijali the entity to save
     * @return the persisted entity
     */
    OstaliMaterijali save(OstaliMaterijali ostaliMaterijali);

    /**
     * Get all the ostaliMaterijalis.
     *
     * @return the list of entities
     */
    List<OstaliMaterijali> findAll();


    /**
     * Get the "id" ostaliMaterijali.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OstaliMaterijali> findOne(Long id);

    /**
     * Delete the "id" ostaliMaterijali.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
