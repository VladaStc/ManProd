package edu.man.prod.service;

import edu.man.prod.domain.RadnoMesto;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RadnoMesto.
 */
public interface RadnoMestoService {

    /**
     * Save a radnoMesto.
     *
     * @param radnoMesto the entity to save
     * @return the persisted entity
     */
    RadnoMesto save(RadnoMesto radnoMesto);

    /**
     * Get all the radnoMestos.
     *
     * @return the list of entities
     */
    List<RadnoMesto> findAll();


    /**
     * Get the "id" radnoMesto.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RadnoMesto> findOne(Long id);

    /**
     * Delete the "id" radnoMesto.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
