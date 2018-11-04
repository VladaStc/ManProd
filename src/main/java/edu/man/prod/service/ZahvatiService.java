package edu.man.prod.service;

import edu.man.prod.domain.Zahvati;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Zahvati.
 */
public interface ZahvatiService {

    /**
     * Save a zahvati.
     *
     * @param zahvati the entity to save
     * @return the persisted entity
     */
    Zahvati save(Zahvati zahvati);

    /**
     * Get all the zahvatis.
     *
     * @return the list of entities
     */
    List<Zahvati> findAll();


    /**
     * Get the "id" zahvati.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Zahvati> findOne(Long id);

    /**
     * Delete the "id" zahvati.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
