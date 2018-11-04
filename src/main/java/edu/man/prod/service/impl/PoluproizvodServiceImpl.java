package edu.man.prod.service.impl;

import edu.man.prod.service.PoluproizvodService;
import edu.man.prod.domain.Poluproizvod;
import edu.man.prod.repository.PoluproizvodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Poluproizvod.
 */
@Service
@Transactional
public class PoluproizvodServiceImpl implements PoluproizvodService {

    private final Logger log = LoggerFactory.getLogger(PoluproizvodServiceImpl.class);

    private final PoluproizvodRepository poluproizvodRepository;

    public PoluproizvodServiceImpl(PoluproizvodRepository poluproizvodRepository) {
        this.poluproizvodRepository = poluproizvodRepository;
    }

    /**
     * Save a poluproizvod.
     *
     * @param poluproizvod the entity to save
     * @return the persisted entity
     */
    @Override
    public Poluproizvod save(Poluproizvod poluproizvod) {
        log.debug("Request to save Poluproizvod : {}", poluproizvod);
        return poluproizvodRepository.save(poluproizvod);
    }

    /**
     * Get all the poluproizvods.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Poluproizvod> findAll() {
        log.debug("Request to get all Poluproizvods");
        return poluproizvodRepository.findAll();
    }


    /**
     * Get one poluproizvod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Poluproizvod> findOne(Long id) {
        log.debug("Request to get Poluproizvod : {}", id);
        return poluproizvodRepository.findById(id);
    }

    /**
     * Delete the poluproizvod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Poluproizvod : {}", id);
        poluproizvodRepository.deleteById(id);
    }
}
