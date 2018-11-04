package edu.man.prod.service;

import edu.man.prod.domain.Odelenje;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Odelenje.
 */
public interface OdelenjeService {

    /**
     * Save a odelenje.
     *
     * @param odelenje the entity to save
     * @return the persisted entity
     */
    Odelenje save(Odelenje odelenje);

    /**
     * Get all the odelenjes.
     *
     * @return the list of entities
     */
    List<Odelenje> findAll();


    /**
     * Get the "id" odelenje.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Odelenje> findOne(Long id);

    /**
     * Delete the "id" odelenje.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
