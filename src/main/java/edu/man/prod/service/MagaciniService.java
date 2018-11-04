package edu.man.prod.service;

import edu.man.prod.domain.Magacini;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Magacini.
 */
public interface MagaciniService {

    /**
     * Save a magacini.
     *
     * @param magacini the entity to save
     * @return the persisted entity
     */
    Magacini save(Magacini magacini);

    /**
     * Get all the magacinis.
     *
     * @return the list of entities
     */
    List<Magacini> findAll();


    /**
     * Get the "id" magacini.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Magacini> findOne(Long id);

    /**
     * Delete the "id" magacini.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
