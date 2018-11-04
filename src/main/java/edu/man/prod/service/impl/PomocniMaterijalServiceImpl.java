package edu.man.prod.service.impl;

import edu.man.prod.service.PomocniMaterijalService;
import edu.man.prod.domain.PomocniMaterijal;
import edu.man.prod.repository.PomocniMaterijalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PomocniMaterijal.
 */
@Service
@Transactional
public class PomocniMaterijalServiceImpl implements PomocniMaterijalService {

    private final Logger log = LoggerFactory.getLogger(PomocniMaterijalServiceImpl.class);

    private final PomocniMaterijalRepository pomocniMaterijalRepository;

    public PomocniMaterijalServiceImpl(PomocniMaterijalRepository pomocniMaterijalRepository) {
        this.pomocniMaterijalRepository = pomocniMaterijalRepository;
    }

    /**
     * Save a pomocniMaterijal.
     *
     * @param pomocniMaterijal the entity to save
     * @return the persisted entity
     */
    @Override
    public PomocniMaterijal save(PomocniMaterijal pomocniMaterijal) {
        log.debug("Request to save PomocniMaterijal : {}", pomocniMaterijal);
        return pomocniMaterijalRepository.save(pomocniMaterijal);
    }

    /**
     * Get all the pomocniMaterijals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PomocniMaterijal> findAll() {
        log.debug("Request to get all PomocniMaterijals");
        return pomocniMaterijalRepository.findAll();
    }


    /**
     * Get one pomocniMaterijal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PomocniMaterijal> findOne(Long id) {
        log.debug("Request to get PomocniMaterijal : {}", id);
        return pomocniMaterijalRepository.findById(id);
    }

    /**
     * Delete the pomocniMaterijal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PomocniMaterijal : {}", id);
        pomocniMaterijalRepository.deleteById(id);
    }
}
