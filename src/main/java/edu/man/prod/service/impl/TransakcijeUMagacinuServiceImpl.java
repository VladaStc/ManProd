package edu.man.prod.service.impl;

import edu.man.prod.service.TransakcijeUMagacinuService;
import edu.man.prod.domain.TransakcijeUMagacinu;
import edu.man.prod.repository.TransakcijeUMagacinuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing TransakcijeUMagacinu.
 */
@Service
@Transactional
public class TransakcijeUMagacinuServiceImpl implements TransakcijeUMagacinuService {

    private final Logger log = LoggerFactory.getLogger(TransakcijeUMagacinuServiceImpl.class);

    private final TransakcijeUMagacinuRepository transakcijeUMagacinuRepository;

    public TransakcijeUMagacinuServiceImpl(TransakcijeUMagacinuRepository transakcijeUMagacinuRepository) {
        this.transakcijeUMagacinuRepository = transakcijeUMagacinuRepository;
    }

    /**
     * Save a transakcijeUMagacinu.
     *
     * @param transakcijeUMagacinu the entity to save
     * @return the persisted entity
     */
    @Override
    public TransakcijeUMagacinu save(TransakcijeUMagacinu transakcijeUMagacinu) {
        log.debug("Request to save TransakcijeUMagacinu : {}", transakcijeUMagacinu);
        return transakcijeUMagacinuRepository.save(transakcijeUMagacinu);
    }

    /**
     * Get all the transakcijeUMagacinus.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransakcijeUMagacinu> findAll() {
        log.debug("Request to get all TransakcijeUMagacinus");
        return transakcijeUMagacinuRepository.findAll();
    }


    /**
     * Get one transakcijeUMagacinu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransakcijeUMagacinu> findOne(Long id) {
        log.debug("Request to get TransakcijeUMagacinu : {}", id);
        return transakcijeUMagacinuRepository.findById(id);
    }

    /**
     * Delete the transakcijeUMagacinu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransakcijeUMagacinu : {}", id);
        transakcijeUMagacinuRepository.deleteById(id);
    }
}
