package edu.man.prod.service.impl;

import edu.man.prod.service.ZahvatiService;
import edu.man.prod.domain.Zahvati;
import edu.man.prod.repository.ZahvatiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Zahvati.
 */
@Service
@Transactional
public class ZahvatiServiceImpl implements ZahvatiService {

    private final Logger log = LoggerFactory.getLogger(ZahvatiServiceImpl.class);

    private final ZahvatiRepository zahvatiRepository;

    public ZahvatiServiceImpl(ZahvatiRepository zahvatiRepository) {
        this.zahvatiRepository = zahvatiRepository;
    }

    /**
     * Save a zahvati.
     *
     * @param zahvati the entity to save
     * @return the persisted entity
     */
    @Override
    public Zahvati save(Zahvati zahvati) {
        log.debug("Request to save Zahvati : {}", zahvati);
        return zahvatiRepository.save(zahvati);
    }

    /**
     * Get all the zahvatis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Zahvati> findAll() {
        log.debug("Request to get all Zahvatis");
        return zahvatiRepository.findAll();
    }


    /**
     * Get one zahvati by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Zahvati> findOne(Long id) {
        log.debug("Request to get Zahvati : {}", id);
        return zahvatiRepository.findById(id);
    }

    /**
     * Delete the zahvati by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Zahvati : {}", id);
        zahvatiRepository.deleteById(id);
    }
}
