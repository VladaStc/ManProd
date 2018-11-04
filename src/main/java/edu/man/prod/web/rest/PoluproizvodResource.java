package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Poluproizvod;
import edu.man.prod.service.PoluproizvodService;
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
 * REST controller for managing Poluproizvod.
 */
@RestController
@RequestMapping("/api")
public class PoluproizvodResource {

    private final Logger log = LoggerFactory.getLogger(PoluproizvodResource.class);

    private static final String ENTITY_NAME = "poluproizvod";

    private final PoluproizvodService poluproizvodService;

    public PoluproizvodResource(PoluproizvodService poluproizvodService) {
        this.poluproizvodService = poluproizvodService;
    }

    /**
     * POST  /poluproizvods : Create a new poluproizvod.
     *
     * @param poluproizvod the poluproizvod to create
     * @return the ResponseEntity with status 201 (Created) and with body the new poluproizvod, or with status 400 (Bad Request) if the poluproizvod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poluproizvods")
    @Timed
    public ResponseEntity<Poluproizvod> createPoluproizvod(@RequestBody Poluproizvod poluproizvod) throws URISyntaxException {
        log.debug("REST request to save Poluproizvod : {}", poluproizvod);
        if (poluproizvod.getId() != null) {
            throw new BadRequestAlertException("A new poluproizvod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Poluproizvod result = poluproizvodService.save(poluproizvod);
        return ResponseEntity.created(new URI("/api/poluproizvods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poluproizvods : Updates an existing poluproizvod.
     *
     * @param poluproizvod the poluproizvod to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated poluproizvod,
     * or with status 400 (Bad Request) if the poluproizvod is not valid,
     * or with status 500 (Internal Server Error) if the poluproizvod couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poluproizvods")
    @Timed
    public ResponseEntity<Poluproizvod> updatePoluproizvod(@RequestBody Poluproizvod poluproizvod) throws URISyntaxException {
        log.debug("REST request to update Poluproizvod : {}", poluproizvod);
        if (poluproizvod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Poluproizvod result = poluproizvodService.save(poluproizvod);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, poluproizvod.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poluproizvods : get all the poluproizvods.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of poluproizvods in body
     */
    @GetMapping("/poluproizvods")
    @Timed
    public List<Poluproizvod> getAllPoluproizvods() {
        log.debug("REST request to get all Poluproizvods");
        return poluproizvodService.findAll();
    }

    /**
     * GET  /poluproizvods/:id : get the "id" poluproizvod.
     *
     * @param id the id of the poluproizvod to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the poluproizvod, or with status 404 (Not Found)
     */
    @GetMapping("/poluproizvods/{id}")
    @Timed
    public ResponseEntity<Poluproizvod> getPoluproizvod(@PathVariable Long id) {
        log.debug("REST request to get Poluproizvod : {}", id);
        Optional<Poluproizvod> poluproizvod = poluproizvodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(poluproizvod);
    }

    /**
     * DELETE  /poluproizvods/:id : delete the "id" poluproizvod.
     *
     * @param id the id of the poluproizvod to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poluproizvods/{id}")
    @Timed
    public ResponseEntity<Void> deletePoluproizvod(@PathVariable Long id) {
        log.debug("REST request to delete Poluproizvod : {}", id);
        poluproizvodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
