package edu.man.prod.service.impl;

import edu.man.prod.service.FinalniProizvodService;
import edu.man.prod.domain.FinalniProizvod;
import edu.man.prod.repository.FinalniProizvodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing FinalniProizvod.
 */
@Service
@Transactional
public class FinalniProizvodServiceImpl implements FinalniProizvodService {

    private final Logger log = LoggerFactory.getLogger(FinalniProizvodServiceImpl.class);

    private final FinalniProizvodRepository finalniProizvodRepository;

    public FinalniProizvodServiceImpl(FinalniProizvodRepository finalniProizvodRepository) {
        this.finalniProizvodRepository = finalniProizvodRepository;
    }

    /**
     * Save a finalniProizvod.
     *
     * @param finalniProizvod the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalniProizvod save(FinalniProizvod finalniProizvod) {
        log.debug("Request to save FinalniProizvod : {}", finalniProizvod);
        return finalniProizvodRepository.save(finalniProizvod);
    }

    /**
     * Get all the finalniProizvods.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FinalniProizvod> findAll() {
        log.debug("Request to get all FinalniProizvods");
        return finalniProizvodRepository.findAll();
    }


    /**
     * Get one finalniProizvod by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinalniProizvod> findOne(Long id) {
        log.debug("Request to get FinalniProizvod : {}", id);
        return finalniProizvodRepository.findById(id);
    }

    /**
     * Delete the finalniProizvod by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinalniProizvod : {}", id);
        finalniProizvodRepository.deleteById(id);
    }
}
