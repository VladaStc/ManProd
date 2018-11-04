package edu.man.prod.service;

import edu.man.prod.domain.TransakcijeUMagacinu;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TransakcijeUMagacinu.
 */
public interface TransakcijeUMagacinuService {

    /**
     * Save a transakcijeUMagacinu.
     *
     * @param transakcijeUMagacinu the entity to save
     * @return the persisted entity
     */
    TransakcijeUMagacinu save(TransakcijeUMagacinu transakcijeUMagacinu);

    /**
     * Get all the transakcijeUMagacinus.
     *
     * @return the list of entities
     */
    List<TransakcijeUMagacinu> findAll();


    /**
     * Get the "id" transakcijeUMagacinu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransakcijeUMagacinu> findOne(Long id);

    /**
     * Delete the "id" transakcijeUMagacinu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
