package edu.man.prod.service;

import edu.man.prod.domain.KupovniMaterijal;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing KupovniMaterijal.
 */
public interface KupovniMaterijalService {

    /**
     * Save a kupovniMaterijal.
     *
     * @param kupovniMaterijal the entity to save
     * @return the persisted entity
     */
    KupovniMaterijal save(KupovniMaterijal kupovniMaterijal);

    /**
     * Get all the kupovniMaterijals.
     *
     * @return the list of entities
     */
    List<KupovniMaterijal> findAll();


    /**
     * Get the "id" kupovniMaterijal.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KupovniMaterijal> findOne(Long id);

    /**
     * Delete the "id" kupovniMaterijal.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
