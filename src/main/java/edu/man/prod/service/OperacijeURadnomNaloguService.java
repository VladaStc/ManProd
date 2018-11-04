package edu.man.prod.service;

import edu.man.prod.domain.OperacijeURadnomNalogu;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OperacijeURadnomNalogu.
 */
public interface OperacijeURadnomNaloguService {

    /**
     * Save a operacijeURadnomNalogu.
     *
     * @param operacijeURadnomNalogu the entity to save
     * @return the persisted entity
     */
    OperacijeURadnomNalogu save(OperacijeURadnomNalogu operacijeURadnomNalogu);

    /**
     * Get all the operacijeURadnomNalogus.
     *
     * @return the list of entities
     */
    List<OperacijeURadnomNalogu> findAll();


    /**
     * Get the "id" operacijeURadnomNalogu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OperacijeURadnomNalogu> findOne(Long id);

    /**
     * Delete the "id" operacijeURadnomNalogu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
