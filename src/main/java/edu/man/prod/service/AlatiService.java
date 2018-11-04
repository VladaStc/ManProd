package edu.man.prod.service;

import edu.man.prod.domain.Alati;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Alati.
 */
public interface AlatiService {

    /**
     * Save a alati.
     *
     * @param alati the entity to save
     * @return the persisted entity
     */
    Alati save(Alati alati);

    /**
     * Get all the alatis.
     *
     * @return the list of entities
     */
    List<Alati> findAll();


    /**
     * Get the "id" alati.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Alati> findOne(Long id);

    /**
     * Delete the "id" alati.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
