package edu.man.prod.service;

import edu.man.prod.domain.ProizvodniPogoni;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProizvodniPogoni.
 */
public interface ProizvodniPogoniService {

    /**
     * Save a proizvodniPogoni.
     *
     * @param proizvodniPogoni the entity to save
     * @return the persisted entity
     */
    ProizvodniPogoni save(ProizvodniPogoni proizvodniPogoni);

    /**
     * Get all the proizvodniPogonis.
     *
     * @return the list of entities
     */
    List<ProizvodniPogoni> findAll();


    /**
     * Get the "id" proizvodniPogoni.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProizvodniPogoni> findOne(Long id);

    /**
     * Delete the "id" proizvodniPogoni.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
