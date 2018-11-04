package edu.man.prod.service;

import edu.man.prod.domain.Komponenete;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Komponenete.
 */
public interface KomponeneteService {

    /**
     * Save a komponenete.
     *
     * @param komponenete the entity to save
     * @return the persisted entity
     */
    Komponenete save(Komponenete komponenete);

    /**
     * Get all the komponenetes.
     *
     * @return the list of entities
     */
    List<Komponenete> findAll();


    /**
     * Get the "id" komponenete.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Komponenete> findOne(Long id);

    /**
     * Delete the "id" komponenete.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
