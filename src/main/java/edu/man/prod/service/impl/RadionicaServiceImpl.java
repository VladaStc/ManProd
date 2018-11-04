package edu.man.prod.service.impl;

import edu.man.prod.service.RadionicaService;
import edu.man.prod.domain.Radionica;
import edu.man.prod.repository.RadionicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Radionica.
 */
@Service
@Transactional
public class RadionicaServiceImpl implements RadionicaService {

    private final Logger log = LoggerFactory.getLogger(RadionicaServiceImpl.class);

    private final RadionicaRepository radionicaRepository;

    public RadionicaServiceImpl(RadionicaRepository radionicaRepository) {
        this.radionicaRepository = radionicaRepository;
    }

    /**
     * Save a radionica.
     *
     * @param radionica the entity to save
     * @return the persisted entity
     */
    @Override
    public Radionica save(Radionica radionica) {
        log.debug("Request to save Radionica : {}", radionica);
        return radionicaRepository.save(radionica);
    }

    /**
     * Get all the radionicas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Radionica> findAll() {
        log.debug("Request to get all Radionicas");
        return radionicaRepository.findAll();
    }


    /**
     * Get one radionica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Radionica> findOne(Long id) {
        log.debug("Request to get Radionica : {}", id);
        return radionicaRepository.findById(id);
    }

    /**
     * Delete the radionica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Radionica : {}", id);
        radionicaRepository.deleteById(id);
    }
}
