package edu.man.prod.service.impl;

import edu.man.prod.service.PotrosniMaterijalService;
import edu.man.prod.domain.PotrosniMaterijal;
import edu.man.prod.repository.PotrosniMaterijalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PotrosniMaterijal.
 */
@Service
@Transactional
public class PotrosniMaterijalServiceImpl implements PotrosniMaterijalService {

    private final Logger log = LoggerFactory.getLogger(PotrosniMaterijalServiceImpl.class);

    private final PotrosniMaterijalRepository potrosniMaterijalRepository;

    public PotrosniMaterijalServiceImpl(PotrosniMaterijalRepository potrosniMaterijalRepository) {
        this.potrosniMaterijalRepository = potrosniMaterijalRepository;
    }

    /**
     * Save a potrosniMaterijal.
     *
     * @param potrosniMaterijal the entity to save
     * @return the persisted entity
     */
    @Override
    public PotrosniMaterijal save(PotrosniMaterijal potrosniMaterijal) {
        log.debug("Request to save PotrosniMaterijal : {}", potrosniMaterijal);
        return potrosniMaterijalRepository.save(potrosniMaterijal);
    }

    /**
     * Get all the potrosniMaterijals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PotrosniMaterijal> findAll() {
        log.debug("Request to get all PotrosniMaterijals");
        return potrosniMaterijalRepository.findAll();
    }


    /**
     * Get one potrosniMaterijal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PotrosniMaterijal> findOne(Long id) {
        log.debug("Request to get PotrosniMaterijal : {}", id);
        return potrosniMaterijalRepository.findById(id);
    }

    /**
     * Delete the potrosniMaterijal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PotrosniMaterijal : {}", id);
        potrosniMaterijalRepository.deleteById(id);
    }
}
