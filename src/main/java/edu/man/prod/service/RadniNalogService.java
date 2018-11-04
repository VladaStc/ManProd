package edu.man.prod.service;

import edu.man.prod.domain.RadniNalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RadniNalog.
 */
public interface RadniNalogService {

    /**
     * Save a radniNalog.
     *
     * @param radniNalog the entity to save
     * @return the persisted entity
     */
    RadniNalog save(RadniNalog radniNalog);

    /**
     * Get all the radniNalogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RadniNalog> findAll(Pageable pageable);


    /**
     * Get the "id" radniNalog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RadniNalog> findOne(Long id);

    /**
     * Delete the "id" radniNalog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
