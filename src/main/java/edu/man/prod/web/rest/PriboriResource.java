package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Pribori;
import edu.man.prod.service.PriboriService;
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
 * REST controller for managing Pribori.
 */
@RestController
@RequestMapping("/api")
public class PriboriResource {

    private final Logger log = LoggerFactory.getLogger(PriboriResource.class);

    private static final String ENTITY_NAME = "pribori";

    private final PriboriService priboriService;

    public PriboriResource(PriboriService priboriService) {
        this.priboriService = priboriService;
    }

    /**
     * POST  /priboris : Create a new pribori.
     *
     * @param pribori the pribori to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pribori, or with status 400 (Bad Request) if the pribori has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/priboris")
    @Timed
    public ResponseEntity<Pribori> createPribori(@RequestBody Pribori pribori) throws URISyntaxException {
        log.debug("REST request to save Pribori : {}", pribori);
        if (pribori.getId() != null) {
            throw new BadRequestAlertException("A new pribori cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pribori result = priboriService.save(pribori);
        return ResponseEntity.created(new URI("/api/priboris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /priboris : Updates an existing pribori.
     *
     * @param pribori the pribori to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pribori,
     * or with status 400 (Bad Request) if the pribori is not valid,
     * or with status 500 (Internal Server Error) if the pribori couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/priboris")
    @Timed
    public ResponseEntity<Pribori> updatePribori(@RequestBody Pribori pribori) throws URISyntaxException {
        log.debug("REST request to update Pribori : {}", pribori);
        if (pribori.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Pribori result = priboriService.save(pribori);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pribori.getId().toString()))
            .body(result);
    }

    /**
     * GET  /priboris : get all the priboris.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of priboris in body
     */
    @GetMapping("/priboris")
    @Timed
    public List<Pribori> getAllPriboris() {
        log.debug("REST request to get all Priboris");
        return priboriService.findAll();
    }

    /**
     * GET  /priboris/:id : get the "id" pribori.
     *
     * @param id the id of the pribori to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pribori, or with status 404 (Not Found)
     */
    @GetMapping("/priboris/{id}")
    @Timed
    public ResponseEntity<Pribori> getPribori(@PathVariable Long id) {
        log.debug("REST request to get Pribori : {}", id);
        Optional<Pribori> pribori = priboriService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pribori);
    }

    /**
     * DELETE  /priboris/:id : delete the "id" pribori.
     *
     * @param id the id of the pribori to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/priboris/{id}")
    @Timed
    public ResponseEntity<Void> deletePribori(@PathVariable Long id) {
        log.debug("REST request to delete Pribori : {}", id);
        priboriService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
