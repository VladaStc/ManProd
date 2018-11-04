package edu.man.prod.service.impl;

import edu.man.prod.service.ProizvodniPogoniService;
import edu.man.prod.domain.ProizvodniPogoni;
import edu.man.prod.repository.ProizvodniPogoniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ProizvodniPogoni.
 */
@Service
@Transactional
public class ProizvodniPogoniServiceImpl implements ProizvodniPogoniService {

    private final Logger log = LoggerFactory.getLogger(ProizvodniPogoniServiceImpl.class);

    private final ProizvodniPogoniRepository proizvodniPogoniRepository;

    public ProizvodniPogoniServiceImpl(ProizvodniPogoniRepository proizvodniPogoniRepository) {
        this.proizvodniPogoniRepository = proizvodniPogoniRepository;
    }

    /**
     * Save a proizvodniPogoni.
     *
     * @param proizvodniPogoni the entity to save
     * @return the persisted entity
     */
    @Override
    public ProizvodniPogoni save(ProizvodniPogoni proizvodniPogoni) {
        log.debug("Request to save ProizvodniPogoni : {}", proizvodniPogoni);
        return proizvodniPogoniRepository.save(proizvodniPogoni);
    }

    /**
     * Get all the proizvodniPogonis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProizvodniPogoni> findAll() {
        log.debug("Request to get all ProizvodniPogonis");
        return proizvodniPogoniRepository.findAll();
    }


    /**
     * Get one proizvodniPogoni by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProizvodniPogoni> findOne(Long id) {
        log.debug("Request to get ProizvodniPogoni : {}", id);
        return proizvodniPogoniRepository.findById(id);
    }

    /**
     * Delete the proizvodniPogoni by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProizvodniPogoni : {}", id);
        proizvodniPogoniRepository.deleteById(id);
    }
}
