package edu.man.prod.service;

import edu.man.prod.domain.ProizvodneLinije;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProizvodneLinije.
 */
public interface ProizvodneLinijeService {

    /**
     * Save a proizvodneLinije.
     *
     * @param proizvodneLinije the entity to save
     * @return the persisted entity
     */
    ProizvodneLinije save(ProizvodneLinije proizvodneLinije);

    /**
     * Get all the proizvodneLinijes.
     *
     * @return the list of entities
     */
    List<ProizvodneLinije> findAll();


    /**
     * Get the "id" proizvodneLinije.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProizvodneLinije> findOne(Long id);

    /**
     * Delete the "id" proizvodneLinije.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
