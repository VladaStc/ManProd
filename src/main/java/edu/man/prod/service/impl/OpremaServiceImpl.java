package edu.man.prod.service.impl;

import edu.man.prod.service.OpremaService;
import edu.man.prod.domain.Oprema;
import edu.man.prod.repository.OpremaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Oprema.
 */
@Service
@Transactional
public class OpremaServiceImpl implements OpremaService {

    private final Logger log = LoggerFactory.getLogger(OpremaServiceImpl.class);

    private final OpremaRepository opremaRepository;

    public OpremaServiceImpl(OpremaRepository opremaRepository) {
        this.opremaRepository = opremaRepository;
    }

    /**
     * Save a oprema.
     *
     * @param oprema the entity to save
     * @return the persisted entity
     */
    @Override
    public Oprema save(Oprema oprema) {
        log.debug("Request to save Oprema : {}", oprema);
        return opremaRepository.save(oprema);
    }

    /**
     * Get all the opremas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Oprema> findAll() {
        log.debug("Request to get all Opremas");
        return opremaRepository.findAll();
    }


    /**
     * Get one oprema by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Oprema> findOne(Long id) {
        log.debug("Request to get Oprema : {}", id);
        return opremaRepository.findById(id);
    }

    /**
     * Delete the oprema by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Oprema : {}", id);
        opremaRepository.deleteById(id);
    }
}
