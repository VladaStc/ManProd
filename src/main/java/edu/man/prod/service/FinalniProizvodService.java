package edu.man.prod.service;

import edu.man.prod.domain.FinalniProizvod;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FinalniProizvod.
 */
public interface FinalniProizvodService {

    /**
     * Save a finalniProizvod.
     *
     * @param finalniProizvod the entity to save
     * @return the persisted entity
     */
    FinalniProizvod save(FinalniProizvod finalniProizvod);

    /**
     * Get all the finalniProizvods.
     *
     * @return the list of entities
     */
    List<FinalniProizvod> findAll();


    /**
     * Get the "id" finalniProizvod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinalniProizvod> findOne(Long id);

    /**
     * Delete the "id" finalniProizvod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
