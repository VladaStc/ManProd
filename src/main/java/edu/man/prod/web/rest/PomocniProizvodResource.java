package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.PomocniProizvod;
import edu.man.prod.service.PomocniProizvodService;
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
 * REST controller for managing PomocniProizvod.
 */
@RestController
@RequestMapping("/api")
public class PomocniProizvodResource {

    private final Logger log = LoggerFactory.getLogger(PomocniProizvodResource.class);

    private static final String ENTITY_NAME = "pomocniProizvod";

    private final PomocniProizvodService pomocniProizvodService;

    public PomocniProizvodResource(PomocniProizvodService pomocniProizvodService) {
        this.pomocniProizvodService = pomocniProizvodService;
    }

    /**
     * POST  /pomocni-proizvods : Create a new pomocniProizvod.
     *
     * @param pomocniProizvod the pomocniProizvod to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pomocniProizvod, or with status 400 (Bad Request) if the pomocniProizvod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pomocni-proizvods")
    @Timed
    public ResponseEntity<PomocniProizvod> createPomocniProizvod(@RequestBody PomocniProizvod pomocniProizvod) throws URISyntaxException {
        log.debug("REST request to save PomocniProizvod : {}", pomocniProizvod);
        if (pomocniProizvod.getId() != null) {
            throw new BadRequestAlertException("A new pomocniProizvod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PomocniProizvod result = pomocniProizvodService.save(pomocniProizvod);
        return ResponseEntity.created(new URI("/api/pomocni-proizvods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pomocni-proizvods : Updates an existing pomocniProizvod.
     *
     * @param pomocniProizvod the pomocniProizvod to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pomocniProizvod,
     * or with status 400 (Bad Request) if the pomocniProizvod is not valid,
     * or with status 500 (Internal Server Error) if the pomocniProizvod couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pomocni-proizvods")
    @Timed
    public ResponseEntity<PomocniProizvod> updatePomocniProizvod(@RequestBody PomocniProizvod pomocniProizvod) throws URISyntaxException {
        log.debug("REST request to update PomocniProizvod : {}", pomocniProizvod);
        if (pomocniProizvod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PomocniProizvod result = pomocniProizvodService.save(pomocniProizvod);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pomocniProizvod.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pomocni-proizvods : get all the pomocniProizvods.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pomocniProizvods in body
     */
    @GetMapping("/pomocni-proizvods")
    @Timed
    public List<PomocniProizvod> getAllPomocniProizvods() {
        log.debug("REST request to get all PomocniProizvods");
        return pomocniProizvodService.findAll();
    }

    /**
     * GET  /pomocni-proizvods/:id : get the "id" pomocniProizvod.
     *
     * @param id the id of the pomocniProizvod to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pomocniProizvod, or with status 404 (Not Found)
     */
    @GetMapping("/pomocni-proizvods/{id}")
    @Timed
    public ResponseEntity<PomocniProizvod> getPomocniProizvod(@PathVariable Long id) {
        log.debug("REST request to get PomocniProizvod : {}", id);
        Optional<PomocniProizvod> pomocniProizvod = pomocniProizvodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pomocniProizvod);
    }

    /**
     * DELETE  /pomocni-proizvods/:id : delete the "id" pomocniProizvod.
     *
     * @param id the id of the pomocniProizvod to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pomocni-proizvods/{id}")
    @Timed
    public ResponseEntity<Void> deletePomocniProizvod(@PathVariable Long id) {
        log.debug("REST request to delete PomocniProizvod : {}", id);
        pomocniProizvodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
