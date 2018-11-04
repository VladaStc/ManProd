package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Sirovine;
import edu.man.prod.service.SirovineService;
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
 * REST controller for managing Sirovine.
 */
@RestController
@RequestMapping("/api")
public class SirovineResource {

    private final Logger log = LoggerFactory.getLogger(SirovineResource.class);

    private static final String ENTITY_NAME = "sirovine";

    private final SirovineService sirovineService;

    public SirovineResource(SirovineService sirovineService) {
        this.sirovineService = sirovineService;
    }

    /**
     * POST  /sirovines : Create a new sirovine.
     *
     * @param sirovine the sirovine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sirovine, or with status 400 (Bad Request) if the sirovine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sirovines")
    @Timed
    public ResponseEntity<Sirovine> createSirovine(@RequestBody Sirovine sirovine) throws URISyntaxException {
        log.debug("REST request to save Sirovine : {}", sirovine);
        if (sirovine.getId() != null) {
            throw new BadRequestAlertException("A new sirovine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sirovine result = sirovineService.save(sirovine);
        return ResponseEntity.created(new URI("/api/sirovines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sirovines : Updates an existing sirovine.
     *
     * @param sirovine the sirovine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sirovine,
     * or with status 400 (Bad Request) if the sirovine is not valid,
     * or with status 500 (Internal Server Error) if the sirovine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sirovines")
    @Timed
    public ResponseEntity<Sirovine> updateSirovine(@RequestBody Sirovine sirovine) throws URISyntaxException {
        log.debug("REST request to update Sirovine : {}", sirovine);
        if (sirovine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sirovine result = sirovineService.save(sirovine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sirovine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sirovines : get all the sirovines.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sirovines in body
     */
    @GetMapping("/sirovines")
    @Timed
    public List<Sirovine> getAllSirovines() {
        log.debug("REST request to get all Sirovines");
        return sirovineService.findAll();
    }

    /**
     * GET  /sirovines/:id : get the "id" sirovine.
     *
     * @param id the id of the sirovine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sirovine, or with status 404 (Not Found)
     */
    @GetMapping("/sirovines/{id}")
    @Timed
    public ResponseEntity<Sirovine> getSirovine(@PathVariable Long id) {
        log.debug("REST request to get Sirovine : {}", id);
        Optional<Sirovine> sirovine = sirovineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sirovine);
    }

    /**
     * DELETE  /sirovines/:id : delete the "id" sirovine.
     *
     * @param id the id of the sirovine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sirovines/{id}")
    @Timed
    public ResponseEntity<Void> deleteSirovine(@PathVariable Long id) {
        log.debug("REST request to delete Sirovine : {}", id);
        sirovineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
