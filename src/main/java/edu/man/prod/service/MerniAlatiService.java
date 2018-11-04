package edu.man.prod.service;

import edu.man.prod.domain.MerniAlati;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MerniAlati.
 */
public interface MerniAlatiService {

    /**
     * Save a merniAlati.
     *
     * @param merniAlati the entity to save
     * @return the persisted entity
     */
    MerniAlati save(MerniAlati merniAlati);

    /**
     * Get all the merniAlatis.
     *
     * @return the list of entities
     */
    List<MerniAlati> findAll();


    /**
     * Get the "id" merniAlati.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MerniAlati> findOne(Long id);

    /**
     * Delete the "id" merniAlati.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
