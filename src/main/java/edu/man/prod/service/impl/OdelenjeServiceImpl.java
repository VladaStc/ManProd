package edu.man.prod.service.impl;

import edu.man.prod.service.OdelenjeService;
import edu.man.prod.domain.Odelenje;
import edu.man.prod.repository.OdelenjeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Odelenje.
 */
@Service
@Transactional
public class OdelenjeServiceImpl implements OdelenjeService {

    private final Logger log = LoggerFactory.getLogger(OdelenjeServiceImpl.class);

    private final OdelenjeRepository odelenjeRepository;

    public OdelenjeServiceImpl(OdelenjeRepository odelenjeRepository) {
        this.odelenjeRepository = odelenjeRepository;
    }

    /**
     * Save a odelenje.
     *
     * @param odelenje the entity to save
     * @return the persisted entity
     */
    @Override
    public Odelenje save(Odelenje odelenje) {
        log.debug("Request to save Odelenje : {}", odelenje);
        return odelenjeRepository.save(odelenje);
    }

    /**
     * Get all the odelenjes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Odelenje> findAll() {
        log.debug("Request to get all Odelenjes");
        return odelenjeRepository.findAll();
    }


    /**
     * Get one odelenje by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Odelenje> findOne(Long id) {
        log.debug("Request to get Odelenje : {}", id);
        return odelenjeRepository.findById(id);
    }

    /**
     * Delete the odelenje by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Odelenje : {}", id);
        odelenjeRepository.deleteById(id);
    }
}
