package edu.man.prod.service.impl;

import edu.man.prod.service.RadniciService;
import edu.man.prod.domain.Radnici;
import edu.man.prod.repository.RadniciRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Radnici.
 */
@Service
@Transactional
public class RadniciServiceImpl implements RadniciService {

    private final Logger log = LoggerFactory.getLogger(RadniciServiceImpl.class);

    private final RadniciRepository radniciRepository;

    public RadniciServiceImpl(RadniciRepository radniciRepository) {
        this.radniciRepository = radniciRepository;
    }

    /**
     * Save a radnici.
     *
     * @param radnici the entity to save
     * @return the persisted entity
     */
    @Override
    public Radnici save(Radnici radnici) {
        log.debug("Request to save Radnici : {}", radnici);
        return radniciRepository.save(radnici);
    }

    /**
     * Get all the radnicis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Radnici> findAll(Pageable pageable) {
        log.debug("Request to get all Radnicis");
        return radniciRepository.findAll(pageable);
    }


    /**
     * Get one radnici by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Radnici> findOne(Long id) {
        log.debug("Request to get Radnici : {}", id);
        return radniciRepository.findById(id);
    }

    /**
     * Delete the radnici by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Radnici : {}", id);
        radniciRepository.deleteById(id);
    }
}
