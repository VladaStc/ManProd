package edu.man.prod.service;

import edu.man.prod.domain.Sirovine;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Sirovine.
 */
public interface SirovineService {

    /**
     * Save a sirovine.
     *
     * @param sirovine the entity to save
     * @return the persisted entity
     */
    Sirovine save(Sirovine sirovine);

    /**
     * Get all the sirovines.
     *
     * @return the list of entities
     */
    List<Sirovine> findAll();


    /**
     * Get the "id" sirovine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Sirovine> findOne(Long id);

    /**
     * Delete the "id" sirovine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
