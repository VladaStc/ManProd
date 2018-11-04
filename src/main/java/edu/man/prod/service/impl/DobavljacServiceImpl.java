package edu.man.prod.service.impl;

import edu.man.prod.service.DobavljacService;
import edu.man.prod.domain.Dobavljac;
import edu.man.prod.repository.DobavljacRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Dobavljac.
 */
@Service
@Transactional
public class DobavljacServiceImpl implements DobavljacService {

    private final Logger log = LoggerFactory.getLogger(DobavljacServiceImpl.class);

    private final DobavljacRepository dobavljacRepository;

    public DobavljacServiceImpl(DobavljacRepository dobavljacRepository) {
        this.dobavljacRepository = dobavljacRepository;
    }

    /**
     * Save a dobavljac.
     *
     * @param dobavljac the entity to save
     * @return the persisted entity
     */
    @Override
    public Dobavljac save(Dobavljac dobavljac) {
        log.debug("Request to save Dobavljac : {}", dobavljac);
        return dobavljacRepository.save(dobavljac);
    }

    /**
     * Get all the dobavljacs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dobavljac> findAll() {
        log.debug("Request to get all Dobavljacs");
        return dobavljacRepository.findAll();
    }


    /**
     * Get one dobavljac by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Dobavljac> findOne(Long id) {
        log.debug("Request to get Dobavljac : {}", id);
        return dobavljacRepository.findById(id);
    }

    /**
     * Delete the dobavljac by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dobavljac : {}", id);
        dobavljacRepository.deleteById(id);
    }
}
