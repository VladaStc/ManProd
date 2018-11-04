package edu.man.prod.service;

import edu.man.prod.domain.Poluproizvod;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Poluproizvod.
 */
public interface PoluproizvodService {

    /**
     * Save a poluproizvod.
     *
     * @param poluproizvod the entity to save
     * @return the persisted entity
     */
    Poluproizvod save(Poluproizvod poluproizvod);

    /**
     * Get all the poluproizvods.
     *
     * @return the list of entities
     */
    List<Poluproizvod> findAll();


    /**
     * Get the "id" poluproizvod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Poluproizvod> findOne(Long id);

    /**
     * Delete the "id" poluproizvod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
