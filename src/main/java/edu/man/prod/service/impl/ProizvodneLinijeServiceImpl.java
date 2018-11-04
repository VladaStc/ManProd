package edu.man.prod.service.impl;

import edu.man.prod.service.ProizvodneLinijeService;
import edu.man.prod.domain.ProizvodneLinije;
import edu.man.prod.repository.ProizvodneLinijeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ProizvodneLinije.
 */
@Service
@Transactional
public class ProizvodneLinijeServiceImpl implements ProizvodneLinijeService {

    private final Logger log = LoggerFactory.getLogger(ProizvodneLinijeServiceImpl.class);

    private final ProizvodneLinijeRepository proizvodneLinijeRepository;

    public ProizvodneLinijeServiceImpl(ProizvodneLinijeRepository proizvodneLinijeRepository) {
        this.proizvodneLinijeRepository = proizvodneLinijeRepository;
    }

    /**
     * Save a proizvodneLinije.
     *
     * @param proizvodneLinije the entity to save
     * @return the persisted entity
     */
    @Override
    public ProizvodneLinije save(ProizvodneLinije proizvodneLinije) {
        log.debug("Request to save ProizvodneLinije : {}", proizvodneLinije);
        return proizvodneLinijeRepository.save(proizvodneLinije);
    }

    /**
     * Get all the proizvodneLinijes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProizvodneLinije> findAll() {
        log.debug("Request to get all ProizvodneLinijes");
        return proizvodneLinijeRepository.findAll();
    }


    /**
     * Get one proizvodneLinije by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProizvodneLinije> findOne(Long id) {
        log.debug("Request to get ProizvodneLinije : {}", id);
        return proizvodneLinijeRepository.findById(id);
    }

    /**
     * Delete the proizvodneLinije by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProizvodneLinije : {}", id);
        proizvodneLinijeRepository.deleteById(id);
    }
}
