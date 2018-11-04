package edu.man.prod.service.impl;

import edu.man.prod.service.ObradniSistemService;
import edu.man.prod.domain.ObradniSistem;
import edu.man.prod.repository.ObradniSistemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ObradniSistem.
 */
@Service
@Transactional
public class ObradniSistemServiceImpl implements ObradniSistemService {

    private final Logger log = LoggerFactory.getLogger(ObradniSistemServiceImpl.class);

    private final ObradniSistemRepository obradniSistemRepository;

    public ObradniSistemServiceImpl(ObradniSistemRepository obradniSistemRepository) {
        this.obradniSistemRepository = obradniSistemRepository;
    }

    /**
     * Save a obradniSistem.
     *
     * @param obradniSistem the entity to save
     * @return the persisted entity
     */
    @Override
    public ObradniSistem save(ObradniSistem obradniSistem) {
        log.debug("Request to save ObradniSistem : {}", obradniSistem);
        return obradniSistemRepository.save(obradniSistem);
    }

    /**
     * Get all the obradniSistems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ObradniSistem> findAll() {
        log.debug("Request to get all ObradniSistems");
        return obradniSistemRepository.findAll();
    }


    /**
     * Get one obradniSistem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ObradniSistem> findOne(Long id) {
        log.debug("Request to get ObradniSistem : {}", id);
        return obradniSistemRepository.findById(id);
    }

    /**
     * Delete the obradniSistem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObradniSistem : {}", id);
        obradniSistemRepository.deleteById(id);
    }
}
