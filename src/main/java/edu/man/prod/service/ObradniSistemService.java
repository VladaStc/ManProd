package edu.man.prod.service;

import edu.man.prod.domain.ObradniSistem;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ObradniSistem.
 */
public interface ObradniSistemService {

    /**
     * Save a obradniSistem.
     *
     * @param obradniSistem the entity to save
     * @return the persisted entity
     */
    ObradniSistem save(ObradniSistem obradniSistem);

    /**
     * Get all the obradniSistems.
     *
     * @return the list of entities
     */
    List<ObradniSistem> findAll();


    /**
     * Get the "id" obradniSistem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ObradniSistem> findOne(Long id);

    /**
     * Delete the "id" obradniSistem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
