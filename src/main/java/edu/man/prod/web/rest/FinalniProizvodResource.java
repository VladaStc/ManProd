package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.FinalniProizvod;
import edu.man.prod.service.FinalniProizvodService;
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
 * REST controller for managing FinalniProizvod.
 */
@RestController
@RequestMapping("/api")
public class FinalniProizvodResource {

    private final Logger log = LoggerFactory.getLogger(FinalniProizvodResource.class);

    private static final String ENTITY_NAME = "finalniProizvod";

    private final FinalniProizvodService finalniProizvodService;

    public FinalniProizvodResource(FinalniProizvodService finalniProizvodService) {
        this.finalniProizvodService = finalniProizvodService;
    }

    /**
     * POST  /finalni-proizvods : Create a new finalniProizvod.
     *
     * @param finalniProizvod the finalniProizvod to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalniProizvod, or with status 400 (Bad Request) if the finalniProizvod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalni-proizvods")
    @Timed
    public ResponseEntity<FinalniProizvod> createFinalniProizvod(@RequestBody FinalniProizvod finalniProizvod) throws URISyntaxException {
        log.debug("REST request to save FinalniProizvod : {}", finalniProizvod);
        if (finalniProizvod.getId() != null) {
            throw new BadRequestAlertException("A new finalniProizvod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinalniProizvod result = finalniProizvodService.save(finalniProizvod);
        return ResponseEntity.created(new URI("/api/finalni-proizvods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /finalni-proizvods : Updates an existing finalniProizvod.
     *
     * @param finalniProizvod the finalniProizvod to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalniProizvod,
     * or with status 400 (Bad Request) if the finalniProizvod is not valid,
     * or with status 500 (Internal Server Error) if the finalniProizvod couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/finalni-proizvods")
    @Timed
    public ResponseEntity<FinalniProizvod> updateFinalniProizvod(@RequestBody FinalniProizvod finalniProizvod) throws URISyntaxException {
        log.debug("REST request to update FinalniProizvod : {}", finalniProizvod);
        if (finalniProizvod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinalniProizvod result = finalniProizvodService.save(finalniProizvod);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalniProizvod.getId().toString()))
            .body(result);
    }

    /**
     * GET  /finalni-proizvods : get all the finalniProizvods.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of finalniProizvods in body
     */
    @GetMapping("/finalni-proizvods")
    @Timed
    public List<FinalniProizvod> getAllFinalniProizvods() {
        log.debug("REST request to get all FinalniProizvods");
        return finalniProizvodService.findAll();
    }

    /**
     * GET  /finalni-proizvods/:id : get the "id" finalniProizvod.
     *
     * @param id the id of the finalniProizvod to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalniProizvod, or with status 404 (Not Found)
     */
    @GetMapping("/finalni-proizvods/{id}")
    @Timed
    public ResponseEntity<FinalniProizvod> getFinalniProizvod(@PathVariable Long id) {
        log.debug("REST request to get FinalniProizvod : {}", id);
        Optional<FinalniProizvod> finalniProizvod = finalniProizvodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalniProizvod);
    }

    /**
     * DELETE  /finalni-proizvods/:id : delete the "id" finalniProizvod.
     *
     * @param id the id of the finalniProizvod to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/finalni-proizvods/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalniProizvod(@PathVariable Long id) {
        log.debug("REST request to delete FinalniProizvod : {}", id);
        finalniProizvodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
