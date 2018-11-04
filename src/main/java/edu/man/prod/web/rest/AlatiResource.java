package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Alati;
import edu.man.prod.service.AlatiService;
import edu.man.prod.web.rest.errors.BadRequestAlertException;
import edu.man.prod.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Alati.
 */
@RestController
@RequestMapping("/api")
public class AlatiResource {

    private final Logger log = LoggerFactory.getLogger(AlatiResource.class);

    private static final String ENTITY_NAME = "alati";

    private final AlatiService alatiService;

    public AlatiResource(AlatiService alatiService) {
        this.alatiService = alatiService;
    }

    /**
     * POST  /alatis : Create a new alati.
     *
     * @param alati the alati to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alati, or with status 400 (Bad Request) if the alati has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alatis")
    @Timed
    public ResponseEntity<Alati> createAlati(@RequestBody Alati alati) throws URISyntaxException {
        log.debug("REST request to save Alati : {}", alati);
        if (alati.getId() != null) {
            throw new BadRequestAlertException("A new alati cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Alati result = alatiService.save(alati);
        return ResponseEntity.created(new URI("/api/alatis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /alatis : Updates an existing alati.
     *
     * @param alati the alati to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alati,
     * or with status 400 (Bad Request) if the alati is not valid,
     * or with status 500 (Internal Server Error) if the alati couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alatis")
    @Timed
    public ResponseEntity<Alati> updateAlati(@RequestBody Alati alati) throws URISyntaxException {
        log.debug("REST request to update Alati : {}", alati);
        if (alati.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Alati result = alatiService.save(alati);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alati.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alatis : get all the alatis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of alatis in body
     */
    @GetMapping("/alatis")
    @Timed
    public List<Alati> getAllAlatis() {
        log.debug("REST request to get all Alatis");
        return alatiService.findAll();
    }

    /**
     * GET  /alatis/:id : get the "id" alati.
     *
     * @param id the id of the alati to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alati, or with status 404 (Not Found)
     */
    @GetMapping("/alatis/{id}")
    @Timed
    public ResponseEntity<Alati> getAlati(@PathVariable Long id) {
        log.debug("REST request to get Alati : {}", id);
        Optional<Alati> alati = alatiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alati);
    }

    /**
     * DELETE  /alatis/:id : delete the "id" alati.
     *
     * @param id the id of the alati to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alatis/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlati(@PathVariable Long id) {
        log.debug("REST request to delete Alati : {}", id);
        alatiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
