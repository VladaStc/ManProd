package edu.man.prod.service.impl;

import edu.man.prod.service.KomponeneteService;
import edu.man.prod.domain.Komponenete;
import edu.man.prod.repository.KomponeneteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Komponenete.
 */
@Service
@Transactional
public class KomponeneteServiceImpl implements KomponeneteService {

    private final Logger log = LoggerFactory.getLogger(KomponeneteServiceImpl.class);

    private final KomponeneteRepository komponeneteRepository;

    public KomponeneteServiceImpl(KomponeneteRepository komponeneteRepository) {
        this.komponeneteRepository = komponeneteRepository;
    }

    /**
     * Save a komponenete.
     *
     * @param komponenete the entity to save
     * @return the persisted entity
     */
    @Override
    public Komponenete save(Komponenete komponenete) {
        log.debug("Request to save Komponenete : {}", komponenete);
        return komponeneteRepository.save(komponenete);
    }

    /**
     * Get all the komponenetes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Komponenete> findAll() {
        log.debug("Request to get all Komponenetes");
        return komponeneteRepository.findAll();
    }


    /**
     * Get one komponenete by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Komponenete> findOne(Long id) {
        log.debug("Request to get Komponenete : {}", id);
        return komponeneteRepository.findById(id);
    }

    /**
     * Delete the komponenete by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Komponenete : {}", id);
        komponeneteRepository.deleteById(id);
    }
}
