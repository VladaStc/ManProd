package edu.man.prod.service;

import edu.man.prod.domain.PomocniMaterijal;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PomocniMaterijal.
 */
public interface PomocniMaterijalService {

    /**
     * Save a pomocniMaterijal.
     *
     * @param pomocniMaterijal the entity to save
     * @return the persisted entity
     */
    PomocniMaterijal save(PomocniMaterijal pomocniMaterijal);

    /**
     * Get all the pomocniMaterijals.
     *
     * @return the list of entities
     */
    List<PomocniMaterijal> findAll();


    /**
     * Get the "id" pomocniMaterijal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PomocniMaterijal> findOne(Long id);

    /**
     * Delete the "id" pomocniMaterijal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
