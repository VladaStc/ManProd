package edu.man.prod.service.impl;

import edu.man.prod.service.MasinaService;
import edu.man.prod.domain.Masina;
import edu.man.prod.repository.MasinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Masina.
 */
@Service
@Transactional
public class MasinaServiceImpl implements MasinaService {

    private final Logger log = LoggerFactory.getLogger(MasinaServiceImpl.class);

    private final MasinaRepository masinaRepository;

    public MasinaServiceImpl(MasinaRepository masinaRepository) {
        this.masinaRepository = masinaRepository;
    }

    /**
     * Save a masina.
     *
     * @param masina the entity to save
     * @return the persisted entity
     */
    @Override
    public Masina save(Masina masina) {
        log.debug("Request to save Masina : {}", masina);
        return masinaRepository.save(masina);
    }

    /**
     * Get all the masinas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Masina> findAll() {
        log.debug("Request to get all Masinas");
        return masinaRepository.findAll();
    }


    /**
     * Get one masina by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Masina> findOne(Long id) {
        log.debug("Request to get Masina : {}", id);
        return masinaRepository.findById(id);
    }

    /**
     * Delete the masina by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Masina : {}", id);
        masinaRepository.deleteById(id);
    }
}
