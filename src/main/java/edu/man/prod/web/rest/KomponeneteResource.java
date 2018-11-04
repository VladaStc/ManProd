package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Komponenete;
import edu.man.prod.service.KomponeneteService;
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
 * REST controller for managing Komponenete.
 */
@RestController
@RequestMapping("/api")
public class KomponeneteResource {

    private final Logger log = LoggerFactory.getLogger(KomponeneteResource.class);

    private static final String ENTITY_NAME = "komponenete";

    private final KomponeneteService komponeneteService;

    public KomponeneteResource(KomponeneteService komponeneteService) {
        this.komponeneteService = komponeneteService;
    }

    /**
     * POST  /komponenetes : Create a new komponenete.
     *
     * @param komponenete the komponenete to create
     * @return the ResponseEntity with status 201 (Created) and with body the new komponenete, or with status 400 (Bad Request) if the komponenete has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/komponenetes")
    @Timed
    public ResponseEntity<Komponenete> createKomponenete(@RequestBody Komponenete komponenete) throws URISyntaxException {
        log.debug("REST request to save Komponenete : {}", komponenete);
        if (komponenete.getId() != null) {
            throw new BadRequestAlertException("A new komponenete cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Komponenete result = komponeneteService.save(komponenete);
        return ResponseEntity.created(new URI("/api/komponenetes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /komponenetes : Updates an existing komponenete.
     *
     * @param komponenete the komponenete to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated komponenete,
     * or with status 400 (Bad Request) if the komponenete is not valid,
     * or with status 500 (Internal Server Error) if the komponenete couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/komponenetes")
    @Timed
    public ResponseEntity<Komponenete> updateKomponenete(@RequestBody Komponenete komponenete) throws URISyntaxException {
        log.debug("REST request to update Komponenete : {}", komponenete);
        if (komponenete.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Komponenete result = komponeneteService.save(komponenete);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, komponenete.getId().toString()))
            .body(result);
    }

    /**
     * GET  /komponenetes : get all the komponenetes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of komponenetes in body
     */
    @GetMapping("/komponenetes")
    @Timed
    public List<Komponenete> getAllKomponenetes() {
        log.debug("REST request to get all Komponenetes");
        return komponeneteService.findAll();
    }

    /**
     * GET  /komponenetes/:id : get the "id" komponenete.
     *
     * @param id the id of the komponenete to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the komponenete, or with status 404 (Not Found)
     */
    @GetMapping("/komponenetes/{id}")
    @Timed
    public ResponseEntity<Komponenete> getKomponenete(@PathVariable Long id) {
        log.debug("REST request to get Komponenete : {}", id);
        Optional<Komponenete> komponenete = komponeneteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(komponenete);
    }

    /**
     * DELETE  /komponenetes/:id : delete the "id" komponenete.
     *
     * @param id the id of the komponenete to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/komponenetes/{id}")
    @Timed
    public ResponseEntity<Void> deleteKomponenete(@PathVariable Long id) {
        log.debug("REST request to delete Komponenete : {}", id);
        komponeneteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
