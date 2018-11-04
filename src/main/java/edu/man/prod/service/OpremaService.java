package edu.man.prod.service;

import edu.man.prod.domain.Oprema;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Oprema.
 */
public interface OpremaService {

    /**
     * Save a oprema.
     *
     * @param oprema the entity to save
     * @return the persisted entity
     */
    Oprema save(Oprema oprema);

    /**
     * Get all the opremas.
     *
     * @return the list of entities
     */
    List<Oprema> findAll();


    /**
     * Get the "id" oprema.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Oprema> findOne(Long id);

    /**
     * Delete the "id" oprema.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
