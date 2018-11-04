package edu.man.prod.service.impl;

import edu.man.prod.service.OperacijeService;
import edu.man.prod.domain.Operacije;
import edu.man.prod.repository.OperacijeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Operacije.
 */
@Service
@Transactional
public class OperacijeServiceImpl implements OperacijeService {

    private final Logger log = LoggerFactory.getLogger(OperacijeServiceImpl.class);

    private final OperacijeRepository operacijeRepository;

    public OperacijeServiceImpl(OperacijeRepository operacijeRepository) {
        this.operacijeRepository = operacijeRepository;
    }

    /**
     * Save a operacije.
     *
     * @param operacije the entity to save
     * @return the persisted entity
     */
    @Override
    public Operacije save(Operacije operacije) {
        log.debug("Request to save Operacije : {}", operacije);
        return operacijeRepository.save(operacije);
    }

    /**
     * Get all the operacijes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Operacije> findAll() {
        log.debug("Request to get all Operacijes");
        return operacijeRepository.findAll();
    }


    /**
     * Get one operacije by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Operacije> findOne(Long id) {
        log.debug("Request to get Operacije : {}", id);
        return operacijeRepository.findById(id);
    }

    /**
     * Delete the operacije by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Operacije : {}", id);
        operacijeRepository.deleteById(id);
    }
}
