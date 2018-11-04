package edu.man.prod.service.impl;

import edu.man.prod.service.StavkeUMagacinuService;
import edu.man.prod.domain.StavkeUMagacinu;
import edu.man.prod.repository.StavkeUMagacinuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing StavkeUMagacinu.
 */
@Service
@Transactional
public class StavkeUMagacinuServiceImpl implements StavkeUMagacinuService {

    private final Logger log = LoggerFactory.getLogger(StavkeUMagacinuServiceImpl.class);

    private final StavkeUMagacinuRepository stavkeUMagacinuRepository;

    public StavkeUMagacinuServiceImpl(StavkeUMagacinuRepository stavkeUMagacinuRepository) {
        this.stavkeUMagacinuRepository = stavkeUMagacinuRepository;
    }

    /**
     * Save a stavkeUMagacinu.
     *
     * @param stavkeUMagacinu the entity to save
     * @return the persisted entity
     */
    @Override
    public StavkeUMagacinu save(StavkeUMagacinu stavkeUMagacinu) {
        log.debug("Request to save StavkeUMagacinu : {}", stavkeUMagacinu);
        return stavkeUMagacinuRepository.save(stavkeUMagacinu);
    }

    /**
     * Get all the stavkeUMagacinus.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StavkeUMagacinu> findAll() {
        log.debug("Request to get all StavkeUMagacinus");
        return stavkeUMagacinuRepository.findAll();
    }


    /**
     * Get one stavkeUMagacinu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StavkeUMagacinu> findOne(Long id) {
        log.debug("Request to get StavkeUMagacinu : {}", id);
        return stavkeUMagacinuRepository.findById(id);
    }

    /**
     * Delete the stavkeUMagacinu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StavkeUMagacinu : {}", id);
        stavkeUMagacinuRepository.deleteById(id);
    }
}
