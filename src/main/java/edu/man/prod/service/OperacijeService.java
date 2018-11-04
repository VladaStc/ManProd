package edu.man.prod.service;

import edu.man.prod.domain.Operacije;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Operacije.
 */
public interface OperacijeService {

    /**
     * Save a operacije.
     *
     * @param operacije the entity to save
     * @return the persisted entity
     */
    Operacije save(Operacije operacije);

    /**
     * Get all the operacijes.
     *
     * @return the list of entities
     */
    List<Operacije> findAll();


    /**
     * Get the "id" operacije.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Operacije> findOne(Long id);

    /**
     * Delete the "id" operacije.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
