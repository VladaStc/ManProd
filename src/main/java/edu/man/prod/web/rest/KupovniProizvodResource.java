package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.KupovniProizvod;
import edu.man.prod.service.KupovniProizvodService;
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
 * REST controller for managing KupovniProizvod.
 */
@RestController
@RequestMapping("/api")
public class KupovniProizvodResource {

    private final Logger log = LoggerFactory.getLogger(KupovniProizvodResource.class);

    private static final String ENTITY_NAME = "kupovniProizvod";

    private final KupovniProizvodService kupovniProizvodService;

    public KupovniProizvodResource(KupovniProizvodService kupovniProizvodService) {
        this.kupovniProizvodService = kupovniProizvodService;
    }

    /**
     * POST  /kupovni-proizvods : Create a new kupovniProizvod.
     *
     * @param kupovniProizvod the kupovniProizvod to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kupovniProizvod, or with status 400 (Bad Request) if the kupovniProizvod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kupovni-proizvods")
    @Timed
    public ResponseEntity<KupovniProizvod> createKupovniProizvod(@RequestBody KupovniProizvod kupovniProizvod) throws URISyntaxException {
        log.debug("REST request to save KupovniProizvod : {}", kupovniProizvod);
        if (kupovniProizvod.getId() != null) {
            throw new BadRequestAlertException("A new kupovniProizvod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KupovniProizvod result = kupovniProizvodService.save(kupovniProizvod);
        return ResponseEntity.created(new URI("/api/kupovni-proizvods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kupovni-proizvods : Updates an existing kupovniProizvod.
     *
     * @param kupovniProizvod the kupovniProizvod to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kupovniProizvod,
     * or with status 400 (Bad Request) if the kupovniProizvod is not valid,
     * or with status 500 (Internal Server Error) if the kupovniProizvod couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kupovni-proizvods")
    @Timed
    public ResponseEntity<KupovniProizvod> updateKupovniProizvod(@RequestBody KupovniProizvod kupovniProizvod) throws URISyntaxException {
        log.debug("REST request to update KupovniProizvod : {}", kupovniProizvod);
        if (kupovniProizvod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KupovniProizvod result = kupovniProizvodService.save(kupovniProizvod);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kupovniProizvod.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kupovni-proizvods : get all the kupovniProizvods.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of kupovniProizvods in body
     */
    @GetMapping("/kupovni-proizvods")
    @Timed
    public List<KupovniProizvod> getAllKupovniProizvods() {
        log.debug("REST request to get all KupovniProizvods");
        return kupovniProizvodService.findAll();
    }

    /**
     * GET  /kupovni-proizvods/:id : get the "id" kupovniProizvod.
     *
     * @param id the id of the kupovniProizvod to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kupovniProizvod, or with status 404 (Not Found)
     */
    @GetMapping("/kupovni-proizvods/{id}")
    @Timed
    public ResponseEntity<KupovniProizvod> getKupovniProizvod(@PathVariable Long id) {
        log.debug("REST request to get KupovniProizvod : {}", id);
        Optional<KupovniProizvod> kupovniProizvod = kupovniProizvodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kupovniProizvod);
    }

    /**
     * DELETE  /kupovni-proizvods/:id : delete the "id" kupovniProizvod.
     *
     * @param id the id of the kupovniProizvod to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kupovni-proizvods/{id}")
    @Timed
    public ResponseEntity<Void> deleteKupovniProizvod(@PathVariable Long id) {
        log.debug("REST request to delete KupovniProizvod : {}", id);
        kupovniProizvodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
