package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.PomocniMaterijal;
import edu.man.prod.service.PomocniMaterijalService;
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
 * REST controller for managing PomocniMaterijal.
 */
@RestController
@RequestMapping("/api")
public class PomocniMaterijalResource {

    private final Logger log = LoggerFactory.getLogger(PomocniMaterijalResource.class);

    private static final String ENTITY_NAME = "pomocniMaterijal";

    private final PomocniMaterijalService pomocniMaterijalService;

    public PomocniMaterijalResource(PomocniMaterijalService pomocniMaterijalService) {
        this.pomocniMaterijalService = pomocniMaterijalService;
    }

    /**
     * POST  /pomocni-materijals : Create a new pomocniMaterijal.
     *
     * @param pomocniMaterijal the pomocniMaterijal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pomocniMaterijal, or with status 400 (Bad Request) if the pomocniMaterijal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pomocni-materijals")
    @Timed
    public ResponseEntity<PomocniMaterijal> createPomocniMaterijal(@RequestBody PomocniMaterijal pomocniMaterijal) throws URISyntaxException {
        log.debug("REST request to save PomocniMaterijal : {}", pomocniMaterijal);
        if (pomocniMaterijal.getId() != null) {
            throw new BadRequestAlertException("A new pomocniMaterijal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PomocniMaterijal result = pomocniMaterijalService.save(pomocniMaterijal);
        return ResponseEntity.created(new URI("/api/pomocni-materijals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pomocni-materijals : Updates an existing pomocniMaterijal.
     *
     * @param pomocniMaterijal the pomocniMaterijal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pomocniMaterijal,
     * or with status 400 (Bad Request) if the pomocniMaterijal is not valid,
     * or with status 500 (Internal Server Error) if the pomocniMaterijal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pomocni-materijals")
    @Timed
    public ResponseEntity<PomocniMaterijal> updatePomocniMaterijal(@RequestBody PomocniMaterijal pomocniMaterijal) throws URISyntaxException {
        log.debug("REST request to update PomocniMaterijal : {}", pomocniMaterijal);
        if (pomocniMaterijal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PomocniMaterijal result = pomocniMaterijalService.save(pomocniMaterijal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pomocniMaterijal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pomocni-materijals : get all the pomocniMaterijals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pomocniMaterijals in body
     */
    @GetMapping("/pomocni-materijals")
    @Timed
    public List<PomocniMaterijal> getAllPomocniMaterijals() {
        log.debug("REST request to get all PomocniMaterijals");
        return pomocniMaterijalService.findAll();
    }

    /**
     * GET  /pomocni-materijals/:id : get the "id" pomocniMaterijal.
     *
     * @param id the id of the pomocniMaterijal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pomocniMaterijal, or with status 404 (Not Found)
     */
    @GetMapping("/pomocni-materijals/{id}")
    @Timed
    public ResponseEntity<PomocniMaterijal> getPomocniMaterijal(@PathVariable Long id) {
        log.debug("REST request to get PomocniMaterijal : {}", id);
        Optional<PomocniMaterijal> pomocniMaterijal = pomocniMaterijalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pomocniMaterijal);
    }

    /**
     * DELETE  /pomocni-materijals/:id : delete the "id" pomocniMaterijal.
     *
     * @param id the id of the pomocniMaterijal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pomocni-materijals/{id}")
    @Timed
    public ResponseEntity<Void> deletePomocniMaterijal(@PathVariable Long id) {
        log.debug("REST request to delete PomocniMaterijal : {}", id);
        pomocniMaterijalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
