package edu.man.prod.service;

import edu.man.prod.domain.PomocniProizvod;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PomocniProizvod.
 */
public interface PomocniProizvodService {

    /**
     * Save a pomocniProizvod.
     *
     * @param pomocniProizvod the entity to save
     * @return the persisted entity
     */
    PomocniProizvod save(PomocniProizvod pomocniProizvod);

    /**
     * Get all the pomocniProizvods.
     *
     * @return the list of entities
     */
    List<PomocniProizvod> findAll();


    /**
     * Get the "id" pomocniProizvod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PomocniProizvod> findOne(Long id);

    /**
     * Delete the "id" pomocniProizvod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
