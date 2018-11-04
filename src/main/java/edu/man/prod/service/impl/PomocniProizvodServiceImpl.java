package edu.man.prod.service.impl;

import edu.man.prod.service.PomocniProizvodService;
import edu.man.prod.domain.PomocniProizvod;
import edu.man.prod.repository.PomocniProizvodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PomocniProizvod.
 */
@Service
@Transactional
public class PomocniProizvodServiceImpl implements PomocniProizvodService {

    private final Logger log = LoggerFactory.getLogger(PomocniProizvodServiceImpl.class);

    private final PomocniProizvodRepository pomocniProizvodRepository;

    public PomocniProizvodServiceImpl(PomocniProizvodRepository pomocniProizvodRepository) {
        this.pomocniProizvodRepository = pomocniProizvodRepository;
    }

    /**
     * Save a pomocniProizvod.
     *
     * @param pomocniProizvod the entity to save
     * @return the persisted entity
     */
    @Override
    public PomocniProizvod save(PomocniProizvod pomocniProizvod) {
        log.debug("Request to save PomocniProizvod : {}", pomocniProizvod);
        return pomocniProizvodRepository.save(pomocniProizvod);
    }

    /**
     * Get all the pomocniProizvods.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PomocniProizvod> findAll() {
        log.debug("Request to get all PomocniProizvods");
        return pomocniProizvodRepository.findAll();
    }


    /**
     * Get one pomocniProizvod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PomocniProizvod> findOne(Long id) {
        log.debug("Request to get PomocniProizvod : {}", id);
        return pomocniProizvodRepository.findById(id);
    }

    /**
     * Delete the pomocniProizvod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PomocniProizvod : {}", id);
        pomocniProizvodRepository.deleteById(id);
    }
}
