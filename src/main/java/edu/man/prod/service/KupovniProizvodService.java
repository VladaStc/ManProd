package edu.man.prod.service;

import edu.man.prod.domain.KupovniProizvod;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing KupovniProizvod.
 */
public interface KupovniProizvodService {

    /**
     * Save a kupovniProizvod.
     *
     * @param kupovniProizvod the entity to save
     * @return the persisted entity
     */
    KupovniProizvod save(KupovniProizvod kupovniProizvod);

    /**
     * Get all the kupovniProizvods.
     *
     * @return the list of entities
     */
    List<KupovniProizvod> findAll();


    /**
     * Get the "id" kupovniProizvod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KupovniProizvod> findOne(Long id);

    /**
     * Delete the "id" kupovniProizvod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
