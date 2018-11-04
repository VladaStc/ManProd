package edu.man.prod.service;

import edu.man.prod.domain.PotrosniMaterijal;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PotrosniMaterijal.
 */
public interface PotrosniMaterijalService {

    /**
     * Save a potrosniMaterijal.
     *
     * @param potrosniMaterijal the entity to save
     * @return the persisted entity
     */
    PotrosniMaterijal save(PotrosniMaterijal potrosniMaterijal);

    /**
     * Get all the potrosniMaterijals.
     *
     * @return the list of entities
     */
    List<PotrosniMaterijal> findAll();


    /**
     * Get the "id" potrosniMaterijal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PotrosniMaterijal> findOne(Long id);

    /**
     * Delete the "id" potrosniMaterijal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
