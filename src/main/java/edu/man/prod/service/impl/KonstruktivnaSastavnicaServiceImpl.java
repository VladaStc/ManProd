package edu.man.prod.service.impl;

import edu.man.prod.service.KonstruktivnaSastavnicaService;
import edu.man.prod.domain.KonstruktivnaSastavnica;
import edu.man.prod.repository.KonstruktivnaSastavnicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing KonstruktivnaSastavnica.
 */
@Service
@Transactional
public class KonstruktivnaSastavnicaServiceImpl implements KonstruktivnaSastavnicaService {

    private final Logger log = LoggerFactory.getLogger(KonstruktivnaSastavnicaServiceImpl.class);

    private final KonstruktivnaSastavnicaRepository konstruktivnaSastavnicaRepository;

    public KonstruktivnaSastavnicaServiceImpl(KonstruktivnaSastavnicaRepository konstruktivnaSastavnicaRepository) {
        this.konstruktivnaSastavnicaRepository = konstruktivnaSastavnicaRepository;
    }

    /**
     * Save a konstruktivnaSastavnica.
     *
     * @param konstruktivnaSastavnica the entity to save
     * @return the persisted entity
     */
    @Override
    public KonstruktivnaSastavnica save(KonstruktivnaSastavnica konstruktivnaSastavnica) {
        log.debug("Request to save KonstruktivnaSastavnica : {}", konstruktivnaSastavnica);
        return konstruktivnaSastavnicaRepository.save(konstruktivnaSastavnica);
    }

    /**
     * Get all the konstruktivnaSastavnicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KonstruktivnaSastavnica> findAll(Pageable pageable) {
        log.debug("Request to get all KonstruktivnaSastavnicas");
        return konstruktivnaSastavnicaRepository.findAll(pageable);
    }


    /**
     * Get one konstruktivnaSastavnica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KonstruktivnaSastavnica> findOne(Long id) {
        log.debug("Request to get KonstruktivnaSastavnica : {}", id);
        return konstruktivnaSastavnicaRepository.findById(id);
    }

    /**
     * Delete the konstruktivnaSastavnica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KonstruktivnaSastavnica : {}", id);
        konstruktivnaSastavnicaRepository.deleteById(id);
    }
}
