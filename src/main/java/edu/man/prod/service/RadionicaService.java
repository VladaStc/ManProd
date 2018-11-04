package edu.man.prod.service;

import edu.man.prod.domain.Radionica;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Radionica.
 */
public interface RadionicaService {

    /**
     * Save a radionica.
     *
     * @param radionica the entity to save
     * @return the persisted entity
     */
    Radionica save(Radionica radionica);

    /**
     * Get all the radionicas.
     *
     * @return the list of entities
     */
    List<Radionica> findAll();


    /**
     * Get the "id" radionica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Radionica> findOne(Long id);

    /**
     * Delete the "id" radionica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
