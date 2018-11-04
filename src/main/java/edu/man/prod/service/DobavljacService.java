package edu.man.prod.service;

import edu.man.prod.domain.Dobavljac;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Dobavljac.
 */
public interface DobavljacService {

    /**
     * Save a dobavljac.
     *
     * @param dobavljac the entity to save
     * @return the persisted entity
     */
    Dobavljac save(Dobavljac dobavljac);

    /**
     * Get all the dobavljacs.
     *
     * @return the list of entities
     */
    List<Dobavljac> findAll();


    /**
     * Get the "id" dobavljac.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Dobavljac> findOne(Long id);

    /**
     * Delete the "id" dobavljac.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
