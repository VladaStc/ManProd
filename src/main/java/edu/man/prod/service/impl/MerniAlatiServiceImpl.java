package edu.man.prod.service.impl;

import edu.man.prod.service.MerniAlatiService;
import edu.man.prod.domain.MerniAlati;
import edu.man.prod.repository.MerniAlatiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MerniAlati.
 */
@Service
@Transactional
public class MerniAlatiServiceImpl implements MerniAlatiService {

    private final Logger log = LoggerFactory.getLogger(MerniAlatiServiceImpl.class);

    private final MerniAlatiRepository merniAlatiRepository;

    public MerniAlatiServiceImpl(MerniAlatiRepository merniAlatiRepository) {
        this.merniAlatiRepository = merniAlatiRepository;
    }

    /**
     * Save a merniAlati.
     *
     * @param merniAlati the entity to save
     * @return the persisted entity
     */
    @Override
    public MerniAlati save(MerniAlati merniAlati) {
        log.debug("Request to save MerniAlati : {}", merniAlati);
        return merniAlatiRepository.save(merniAlati);
    }

    /**
     * Get all the merniAlatis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MerniAlati> findAll() {
        log.debug("Request to get all MerniAlatis");
        return merniAlatiRepository.findAll();
    }


    /**
     * Get one merniAlati by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MerniAlati> findOne(Long id) {
        log.debug("Request to get MerniAlati : {}", id);
        return merniAlatiRepository.findById(id);
    }

    /**
     * Delete the merniAlati by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MerniAlati : {}", id);
        merniAlatiRepository.deleteById(id);
    }
}
