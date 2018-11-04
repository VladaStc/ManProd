package edu.man.prod.service;

import edu.man.prod.domain.StavkeUMagacinu;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StavkeUMagacinu.
 */
public interface StavkeUMagacinuService {

    /**
     * Save a stavkeUMagacinu.
     *
     * @param stavkeUMagacinu the entity to save
     * @return the persisted entity
     */
    StavkeUMagacinu save(StavkeUMagacinu stavkeUMagacinu);

    /**
     * Get all the stavkeUMagacinus.
     *
     * @return the list of entities
     */
    List<StavkeUMagacinu> findAll();


    /**
     * Get the "id" stavkeUMagacinu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StavkeUMagacinu> findOne(Long id);

    /**
     * Delete the "id" stavkeUMagacinu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
