package edu.man.prod.service;

import edu.man.prod.domain.KonstruktivnaSastavnica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing KonstruktivnaSastavnica.
 */
public interface KonstruktivnaSastavnicaService {

    /**
     * Save a konstruktivnaSastavnica.
     *
     * @param konstruktivnaSastavnica the entity to save
     * @return the persisted entity
     */
    KonstruktivnaSastavnica save(KonstruktivnaSastavnica konstruktivnaSastavnica);

    /**
     * Get all the konstruktivnaSastavnicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<KonstruktivnaSastavnica> findAll(Pageable pageable);


    /**
     * Get the "id" konstruktivnaSastavnica.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KonstruktivnaSastavnica> findOne(Long id);

    /**
     * Delete the "id" konstruktivnaSastavnica.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
