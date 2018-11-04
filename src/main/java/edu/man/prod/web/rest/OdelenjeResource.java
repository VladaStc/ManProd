package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Odelenje;
import edu.man.prod.service.OdelenjeService;
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
 * REST controller for managing Odelenje.
 */
@RestController
@RequestMapping("/api")
public class OdelenjeResource {

    private final Logger log = LoggerFactory.getLogger(OdelenjeResource.class);

    private static final String ENTITY_NAME = "odelenje";

    private final OdelenjeService odelenjeService;

    public OdelenjeResource(OdelenjeService odelenjeService) {
        this.odelenjeService = odelenjeService;
    }

    /**
     * POST  /odelenjes : Create a new odelenje.
     *
     * @param odelenje the odelenje to create
     * @return the ResponseEntity with status 201 (Created) and with body the new odelenje, or with status 400 (Bad Request) if the odelenje has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/odelenjes")
    @Timed
    public ResponseEntity<Odelenje> createOdelenje(@RequestBody Odelenje odelenje) throws URISyntaxException {
        log.debug("REST request to save Odelenje : {}", odelenje);
        if (odelenje.getId() != null) {
            throw new BadRequestAlertException("A new odelenje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Odelenje result = odelenjeService.save(odelenje);
        return ResponseEntity.created(new URI("/api/odelenjes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /odelenjes : Updates an existing odelenje.
     *
     * @param odelenje the odelenje to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated odelenje,
     * or with status 400 (Bad Request) if the odelenje is not valid,
     * or with status 500 (Internal Server Error) if the odelenje couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/odelenjes")
    @Timed
    public ResponseEntity<Odelenje> updateOdelenje(@RequestBody Odelenje odelenje) throws URISyntaxException {
        log.debug("REST request to update Odelenje : {}", odelenje);
        if (odelenje.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Odelenje result = odelenjeService.save(odelenje);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, odelenje.getId().toString()))
            .body(result);
    }

    /**
     * GET  /odelenjes : get all the odelenjes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of odelenjes in body
     */
    @GetMapping("/odelenjes")
    @Timed
    public List<Odelenje> getAllOdelenjes() {
        log.debug("REST request to get all Odelenjes");
        return odelenjeService.findAll();
    }

    /**
     * GET  /odelenjes/:id : get the "id" odelenje.
     *
     * @param id the id of the odelenje to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the odelenje, or with status 404 (Not Found)
     */
    @GetMapping("/odelenjes/{id}")
    @Timed
    public ResponseEntity<Odelenje> getOdelenje(@PathVariable Long id) {
        log.debug("REST request to get Odelenje : {}", id);
        Optional<Odelenje> odelenje = odelenjeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(odelenje);
    }

    /**
     * DELETE  /odelenjes/:id : delete the "id" odelenje.
     *
     * @param id the id of the odelenje to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/odelenjes/{id}")
    @Timed
    public ResponseEntity<Void> deleteOdelenje(@PathVariable Long id) {
        log.debug("REST request to delete Odelenje : {}", id);
        odelenjeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
