package edu.man.prod.service.impl;

import edu.man.prod.service.OstaliMaterijaliService;
import edu.man.prod.domain.OstaliMaterijali;
import edu.man.prod.repository.OstaliMaterijaliRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing OstaliMaterijali.
 */
@Service
@Transactional
public class OstaliMaterijaliServiceImpl implements OstaliMaterijaliService {

    private final Logger log = LoggerFactory.getLogger(OstaliMaterijaliServiceImpl.class);

    private final OstaliMaterijaliRepository ostaliMaterijaliRepository;

    public OstaliMaterijaliServiceImpl(OstaliMaterijaliRepository ostaliMaterijaliRepository) {
        this.ostaliMaterijaliRepository = ostaliMaterijaliRepository;
    }

    /**
     * Save a ostaliMaterijali.
     *
     * @param ostaliMaterijali the entity to save
     * @return the persisted entity
     */
    @Override
    public OstaliMaterijali save(OstaliMaterijali ostaliMaterijali) {
        log.debug("Request to save OstaliMaterijali : {}", ostaliMaterijali);
        return ostaliMaterijaliRepository.save(ostaliMaterijali);
    }

    /**
     * Get all the ostaliMaterijalis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OstaliMaterijali> findAll() {
        log.debug("Request to get all OstaliMaterijalis");
        return ostaliMaterijaliRepository.findAll();
    }


    /**
     * Get one ostaliMaterijali by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OstaliMaterijali> findOne(Long id) {
        log.debug("Request to get OstaliMaterijali : {}", id);
        return ostaliMaterijaliRepository.findById(id);
    }

    /**
     * Delete the ostaliMaterijali by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OstaliMaterijali : {}", id);
        ostaliMaterijaliRepository.deleteById(id);
    }
}
