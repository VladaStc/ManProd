package edu.man.prod.service.impl;

import edu.man.prod.service.KupovniMaterijalService;
import edu.man.prod.domain.KupovniMaterijal;
import edu.man.prod.repository.KupovniMaterijalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing KupovniMaterijal.
 */
@Service
@Transactional
public class KupovniMaterijalServiceImpl implements KupovniMaterijalService {

    private final Logger log = LoggerFactory.getLogger(KupovniMaterijalServiceImpl.class);

    private final KupovniMaterijalRepository kupovniMaterijalRepository;

    public KupovniMaterijalServiceImpl(KupovniMaterijalRepository kupovniMaterijalRepository) {
        this.kupovniMaterijalRepository = kupovniMaterijalRepository;
    }

    /**
     * Save a kupovniMaterijal.
     *
     * @param kupovniMaterijal the entity to save
     * @return the persisted entity
     */
    @Override
    public KupovniMaterijal save(KupovniMaterijal kupovniMaterijal) {
        log.debug("Request to save KupovniMaterijal : {}", kupovniMaterijal);
        return kupovniMaterijalRepository.save(kupovniMaterijal);
    }

    /**
     * Get all the kupovniMaterijals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<KupovniMaterijal> findAll() {
        log.debug("Request to get all KupovniMaterijals");
        return kupovniMaterijalRepository.findAll();
    }


    /**
     * Get one kupovniMaterijal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KupovniMaterijal> findOne(Long id) {
        log.debug("Request to get KupovniMaterijal : {}", id);
        return kupovniMaterijalRepository.findById(id);
    }

    /**
     * Delete the kupovniMaterijal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KupovniMaterijal : {}", id);
        kupovniMaterijalRepository.deleteById(id);
    }
}
