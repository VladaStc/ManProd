package edu.man.prod.service;

import edu.man.prod.domain.Masina;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Masina.
 */
public interface MasinaService {

    /**
     * Save a masina.
     *
     * @param masina the entity to save
     * @return the persisted entity
     */
    Masina save(Masina masina);

    /**
     * Get all the masinas.
     *
     * @return the list of entities
     */
    List<Masina> findAll();


    /**
     * Get the "id" masina.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Masina> findOne(Long id);

    /**
     * Delete the "id" masina.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
