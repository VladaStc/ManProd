package edu.man.prod.service.impl;

import edu.man.prod.service.PriboriService;
import edu.man.prod.domain.Pribori;
import edu.man.prod.repository.PriboriRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Pribori.
 */
@Service
@Transactional
public class PriboriServiceImpl implements PriboriService {

    private final Logger log = LoggerFactory.getLogger(PriboriServiceImpl.class);

    private final PriboriRepository priboriRepository;

    public PriboriServiceImpl(PriboriRepository priboriRepository) {
        this.priboriRepository = priboriRepository;
    }

    /**
     * Save a pribori.
     *
     * @param pribori the entity to save
     * @return the persisted entity
     */
    @Override
    public Pribori save(Pribori pribori) {
        log.debug("Request to save Pribori : {}", pribori);
        return priboriRepository.save(pribori);
    }

    /**
     * Get all the priboris.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Pribori> findAll() {
        log.debug("Request to get all Priboris");
        return priboriRepository.findAll();
    }


    /**
     * Get one pribori by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Pribori> findOne(Long id) {
        log.debug("Request to get Pribori : {}", id);
        return priboriRepository.findById(id);
    }

    /**
     * Delete the pribori by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pribori : {}", id);
        priboriRepository.deleteById(id);
    }
}
