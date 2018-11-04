package edu.man.prod.service.impl;

import edu.man.prod.service.RadniNalogService;
import edu.man.prod.domain.RadniNalog;
import edu.man.prod.repository.RadniNalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RadniNalog.
 */
@Service
@Transactional
public class RadniNalogServiceImpl implements RadniNalogService {

    private final Logger log = LoggerFactory.getLogger(RadniNalogServiceImpl.class);

    private final RadniNalogRepository radniNalogRepository;

    public RadniNalogServiceImpl(RadniNalogRepository radniNalogRepository) {
        this.radniNalogRepository = radniNalogRepository;
    }

    /**
     * Save a radniNalog.
     *
     * @param radniNalog the entity to save
     * @return the persisted entity
     */
    @Override
    public RadniNalog save(RadniNalog radniNalog) {
        log.debug("Request to save RadniNalog : {}", radniNalog);
        return radniNalogRepository.save(radniNalog);
    }

    /**
     * Get all the radniNalogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RadniNalog> findAll(Pageable pageable) {
        log.debug("Request to get all RadniNalogs");
        return radniNalogRepository.findAll(pageable);
    }


    /**
     * Get one radniNalog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RadniNalog> findOne(Long id) {
        log.debug("Request to get RadniNalog : {}", id);
        return radniNalogRepository.findById(id);
    }

    /**
     * Delete the radniNalog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RadniNalog : {}", id);
        radniNalogRepository.deleteById(id);
    }
}
