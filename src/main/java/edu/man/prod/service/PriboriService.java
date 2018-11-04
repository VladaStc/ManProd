package edu.man.prod.service;

import edu.man.prod.domain.Pribori;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Pribori.
 */
public interface PriboriService {

    /**
     * Save a pribori.
     *
     * @param pribori the entity to save
     * @return the persisted entity
     */
    Pribori save(Pribori pribori);

    /**
     * Get all the priboris.
     *
     * @return the list of entities
     */
    List<Pribori> findAll();


    /**
     * Get the "id" pribori.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Pribori> findOne(Long id);

    /**
     * Delete the "id" pribori.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
