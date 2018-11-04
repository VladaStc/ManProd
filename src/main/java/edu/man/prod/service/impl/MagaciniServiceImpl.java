package edu.man.prod.service.impl;

import edu.man.prod.service.MagaciniService;
import edu.man.prod.domain.Magacini;
import edu.man.prod.repository.MagaciniRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Magacini.
 */
@Service
@Transactional
public class MagaciniServiceImpl implements MagaciniService {

    private final Logger log = LoggerFactory.getLogger(MagaciniServiceImpl.class);

    private final MagaciniRepository magaciniRepository;

    public MagaciniServiceImpl(MagaciniRepository magaciniRepository) {
        this.magaciniRepository = magaciniRepository;
    }

    /**
     * Save a magacini.
     *
     * @param magacini the entity to save
     * @return the persisted entity
     */
    @Override
    public Magacini save(Magacini magacini) {
        log.debug("Request to save Magacini : {}", magacini);
        return magaciniRepository.save(magacini);
    }

    /**
     * Get all the magacinis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Magacini> findAll() {
        log.debug("Request to get all Magacinis");
        return magaciniRepository.findAll();
    }


    /**
     * Get one magacini by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Magacini> findOne(Long id) {
        log.debug("Request to get Magacini : {}", id);
        return magaciniRepository.findById(id);
    }

    /**
     * Delete the magacini by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Magacini : {}", id);
        magaciniRepository.deleteById(id);
    }
}
