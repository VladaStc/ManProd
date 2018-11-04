package edu.man.prod.service.impl;

import edu.man.prod.service.SirovineService;
import edu.man.prod.domain.Sirovine;
import edu.man.prod.repository.SirovineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Sirovine.
 */
@Service
@Transactional
public class SirovineServiceImpl implements SirovineService {

    private final Logger log = LoggerFactory.getLogger(SirovineServiceImpl.class);

    private final SirovineRepository sirovineRepository;

    public SirovineServiceImpl(SirovineRepository sirovineRepository) {
        this.sirovineRepository = sirovineRepository;
    }

    /**
     * Save a sirovine.
     *
     * @param sirovine the entity to save
     * @return the persisted entity
     */
    @Override
    public Sirovine save(Sirovine sirovine) {
        log.debug("Request to save Sirovine : {}", sirovine);
        return sirovineRepository.save(sirovine);
    }

    /**
     * Get all the sirovines.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Sirovine> findAll() {
        log.debug("Request to get all Sirovines");
        return sirovineRepository.findAll();
    }


    /**
     * Get one sirovine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Sirovine> findOne(Long id) {
        log.debug("Request to get Sirovine : {}", id);
        return sirovineRepository.findById(id);
    }

    /**
     * Delete the sirovine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sirovine : {}", id);
        sirovineRepository.deleteById(id);
    }
}
