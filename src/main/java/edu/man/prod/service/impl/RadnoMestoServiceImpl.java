package edu.man.prod.service.impl;

import edu.man.prod.service.RadnoMestoService;
import edu.man.prod.domain.RadnoMesto;
import edu.man.prod.repository.RadnoMestoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RadnoMesto.
 */
@Service
@Transactional
public class RadnoMestoServiceImpl implements RadnoMestoService {

    private final Logger log = LoggerFactory.getLogger(RadnoMestoServiceImpl.class);

    private final RadnoMestoRepository radnoMestoRepository;

    public RadnoMestoServiceImpl(RadnoMestoRepository radnoMestoRepository) {
        this.radnoMestoRepository = radnoMestoRepository;
    }

    /**
     * Save a radnoMesto.
     *
     * @param radnoMesto the entity to save
     * @return the persisted entity
     */
    @Override
    public RadnoMesto save(RadnoMesto radnoMesto) {
        log.debug("Request to save RadnoMesto : {}", radnoMesto);
        return radnoMestoRepository.save(radnoMesto);
    }

    /**
     * Get all the radnoMestos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RadnoMesto> findAll() {
        log.debug("Request to get all RadnoMestos");
        return radnoMestoRepository.findAll();
    }


    /**
     * Get one radnoMesto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RadnoMesto> findOne(Long id) {
        log.debug("Request to get RadnoMesto : {}", id);
        return radnoMestoRepository.findById(id);
    }

    /**
     * Delete the radnoMesto by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RadnoMesto : {}", id);
        radnoMestoRepository.deleteById(id);
    }
}
