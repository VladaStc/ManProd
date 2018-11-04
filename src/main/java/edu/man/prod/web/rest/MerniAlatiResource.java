package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.MerniAlati;
import edu.man.prod.service.MerniAlatiService;
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
 * REST controller for managing MerniAlati.
 */
@RestController
@RequestMapping("/api")
public class MerniAlatiResource {

    private final Logger log = LoggerFactory.getLogger(MerniAlatiResource.class);

    private static final String ENTITY_NAME = "merniAlati";

    private final MerniAlatiService merniAlatiService;

    public MerniAlatiResource(MerniAlatiService merniAlatiService) {
        this.merniAlatiService = merniAlatiService;
    }

    /**
     * POST  /merni-alatis : Create a new merniAlati.
     *
     * @param merniAlati the merniAlati to create
     * @return the ResponseEntity with status 201 (Created) and with body the new merniAlati, or with status 400 (Bad Request) if the merniAlati has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/merni-alatis")
    @Timed
    public ResponseEntity<MerniAlati> createMerniAlati(@RequestBody MerniAlati merniAlati) throws URISyntaxException {
        log.debug("REST request to save MerniAlati : {}", merniAlati);
        if (merniAlati.getId() != null) {
            throw new BadRequestAlertException("A new merniAlati cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MerniAlati result = merniAlatiService.save(merniAlati);
        return ResponseEntity.created(new URI("/api/merni-alatis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /merni-alatis : Updates an existing merniAlati.
     *
     * @param merniAlati the merniAlati to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated merniAlati,
     * or with status 400 (Bad Request) if the merniAlati is not valid,
     * or with status 500 (Internal Server Error) if the merniAlati couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/merni-alatis")
    @Timed
    public ResponseEntity<MerniAlati> updateMerniAlati(@RequestBody MerniAlati merniAlati) throws URISyntaxException {
        log.debug("REST request to update MerniAlati : {}", merniAlati);
        if (merniAlati.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MerniAlati result = merniAlatiService.save(merniAlati);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, merniAlati.getId().toString()))
            .body(result);
    }

    /**
     * GET  /merni-alatis : get all the merniAlatis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of merniAlatis in body
     */
    @GetMapping("/merni-alatis")
    @Timed
    public List<MerniAlati> getAllMerniAlatis() {
        log.debug("REST request to get all MerniAlatis");
        return merniAlatiService.findAll();
    }

    /**
     * GET  /merni-alatis/:id : get the "id" merniAlati.
     *
     * @param id the id of the merniAlati to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the merniAlati, or with status 404 (Not Found)
     */
    @GetMapping("/merni-alatis/{id}")
    @Timed
    public ResponseEntity<MerniAlati> getMerniAlati(@PathVariable Long id) {
        log.debug("REST request to get MerniAlati : {}", id);
        Optional<MerniAlati> merniAlati = merniAlatiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(merniAlati);
    }

    /**
     * DELETE  /merni-alatis/:id : delete the "id" merniAlati.
     *
     * @param id the id of the merniAlati to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/merni-alatis/{id}")
    @Timed
    public ResponseEntity<Void> deleteMerniAlati(@PathVariable Long id) {
        log.debug("REST request to delete MerniAlati : {}", id);
        merniAlatiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
