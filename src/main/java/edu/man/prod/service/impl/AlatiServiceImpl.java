package edu.man.prod.service.impl;

import edu.man.prod.service.AlatiService;
import edu.man.prod.domain.Alati;
import edu.man.prod.repository.AlatiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Alati.
 */
@Service
@Transactional
public class AlatiServiceImpl implements AlatiService {

    private final Logger log = LoggerFactory.getLogger(AlatiServiceImpl.class);

    private final AlatiRepository alatiRepository;

    public AlatiServiceImpl(AlatiRepository alatiRepository) {
        this.alatiRepository = alatiRepository;
    }

    /**
     * Save a alati.
     *
     * @param alati the entity to save
     * @return the persisted entity
     */
    @Override
    public Alati save(Alati alati) {
        log.debug("Request to save Alati : {}", alati);
        return alatiRepository.save(alati);
    }

    /**
     * Get all the alatis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alati> findAll() {
        log.debug("Request to get all Alatis");
        return alatiRepository.findAll();
    }


    /**
     * Get one alati by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Alati> findOne(Long id) {
        log.debug("Request to get Alati : {}", id);
        return alatiRepository.findById(id);
    }

    /**
     * Delete the alati by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Alati : {}", id);
        alatiRepository.deleteById(id);
    }
}
