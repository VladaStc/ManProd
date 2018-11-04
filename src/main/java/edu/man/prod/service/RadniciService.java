package edu.man.prod.service;

import edu.man.prod.domain.Radnici;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Radnici.
 */
public interface RadniciService {

    /**
     * Save a radnici.
     *
     * @param radnici the entity to save
     * @return the persisted entity
     */
    Radnici save(Radnici radnici);

    /**
     * Get all the radnicis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Radnici> findAll(Pageable pageable);


    /**
     * Get the "id" radnici.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Radnici> findOne(Long id);

    /**
     * Delete the "id" radnici.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
