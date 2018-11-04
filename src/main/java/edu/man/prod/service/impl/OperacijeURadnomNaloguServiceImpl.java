package edu.man.prod.service.impl;

import edu.man.prod.service.OperacijeURadnomNaloguService;
import edu.man.prod.domain.OperacijeURadnomNalogu;
import edu.man.prod.repository.OperacijeURadnomNaloguRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing OperacijeURadnomNalogu.
 */
@Service
@Transactional
public class OperacijeURadnomNaloguServiceImpl implements OperacijeURadnomNaloguService {

    private final Logger log = LoggerFactory.getLogger(OperacijeURadnomNaloguServiceImpl.class);

    private final OperacijeURadnomNaloguRepository operacijeURadnomNaloguRepository;

    public OperacijeURadnomNaloguServiceImpl(OperacijeURadnomNaloguRepository operacijeURadnomNaloguRepository) {
        this.operacijeURadnomNaloguRepository = operacijeURadnomNaloguRepository;
    }

    /**
     * Save a operacijeURadnomNalogu.
     *
     * @param operacijeURadnomNalogu the entity to save
     * @return the persisted entity
     */
    @Override
    public OperacijeURadnomNalogu save(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        log.debug("Request to save OperacijeURadnomNalogu : {}", operacijeURadnomNalogu);
        return operacijeURadnomNaloguRepository.save(operacijeURadnomNalogu);
    }

    /**
     * Get all the operacijeURadnomNalogus.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OperacijeURadnomNalogu> findAll() {
        log.debug("Request to get all OperacijeURadnomNalogus");
        return operacijeURadnomNaloguRepository.findAll();
    }


    /**
     * Get one operacijeURadnomNalogu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OperacijeURadnomNalogu> findOne(Long id) {
        log.debug("Request to get OperacijeURadnomNalogu : {}", id);
        return operacijeURadnomNaloguRepository.findById(id);
    }

    /**
     * Delete the operacijeURadnomNalogu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperacijeURadnomNalogu : {}", id);
        operacijeURadnomNaloguRepository.deleteById(id);
    }
}
