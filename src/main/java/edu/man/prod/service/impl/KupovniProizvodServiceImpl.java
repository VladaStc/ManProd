package edu.man.prod.service.impl;

import edu.man.prod.service.KupovniProizvodService;
import edu.man.prod.domain.KupovniProizvod;
import edu.man.prod.repository.KupovniProizvodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing KupovniProizvod.
 */
@Service
@Transactional
public class KupovniProizvodServiceImpl implements KupovniProizvodService {

    private final Logger log = LoggerFactory.getLogger(KupovniProizvodServiceImpl.class);

    private final KupovniProizvodRepository kupovniProizvodRepository;

    public KupovniProizvodServiceImpl(KupovniProizvodRepository kupovniProizvodRepository) {
        this.kupovniProizvodRepository = kupovniProizvodRepository;
    }

    /**
     * Save a kupovniProizvod.
     *
     * @param kupovniProizvod the entity to save
     * @return the persisted entity
     */
    @Override
    public KupovniProizvod save(KupovniProizvod kupovniProizvod) {
        log.debug("Request to save KupovniProizvod : {}", kupovniProizvod);
        return kupovniProizvodRepository.save(kupovniProizvod);
    }

    /**
     * Get all the kupovniProizvods.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<KupovniProizvod> findAll() {
        log.debug("Request to get all KupovniProizvods");
        return kupovniProizvodRepository.findAll();
    }


    /**
     * Get one kupovniProizvod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KupovniProizvod> findOne(Long id) {
        log.debug("Request to get KupovniProizvod : {}", id);
        return kupovniProizvodRepository.findById(id);
    }

    /**
     * Delete the kupovniProizvod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KupovniProizvod : {}", id);
        kupovniProizvodRepository.deleteById(id);
    }
}
