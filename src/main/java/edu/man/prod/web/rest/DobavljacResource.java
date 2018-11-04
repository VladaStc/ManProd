package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Dobavljac;
import edu.man.prod.service.DobavljacService;
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
 * REST controller for managing Dobavljac.
 */
@RestController
@RequestMapping("/api")
public class DobavljacResource {

    private final Logger log = LoggerFactory.getLogger(DobavljacResource.class);

    private static final String ENTITY_NAME = "dobavljac";

    private final DobavljacService dobavljacService;

    public DobavljacResource(DobavljacService dobavljacService) {
        this.dobavljacService = dobavljacService;
    }

    /**
     * POST  /dobavljacs : Create a new dobavljac.
     *
     * @param dobavljac the dobavljac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dobavljac, or with status 400 (Bad Request) if the dobavljac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dobavljacs")
    @Timed
    public ResponseEntity<Dobavljac> createDobavljac(@RequestBody Dobavljac dobavljac) throws URISyntaxException {
        log.debug("REST request to save Dobavljac : {}", dobavljac);
        if (dobavljac.getId() != null) {
            throw new BadRequestAlertException("A new dobavljac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dobavljac result = dobavljacService.save(dobavljac);
        return ResponseEntity.created(new URI("/api/dobavljacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dobavljacs : Updates an existing dobavljac.
     *
     * @param dobavljac the dobavljac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dobavljac,
     * or with status 400 (Bad Request) if the dobavljac is not valid,
     * or with status 500 (Internal Server Error) if the dobavljac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dobavljacs")
    @Timed
    public ResponseEntity<Dobavljac> updateDobavljac(@RequestBody Dobavljac dobavljac) throws URISyntaxException {
        log.debug("REST request to update Dobavljac : {}", dobavljac);
        if (dobavljac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dobavljac result = dobavljacService.save(dobavljac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dobavljac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dobavljacs : get all the dobavljacs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dobavljacs in body
     */
    @GetMapping("/dobavljacs")
    @Timed
    public List<Dobavljac> getAllDobavljacs() {
        log.debug("REST request to get all Dobavljacs");
        return dobavljacService.findAll();
    }

    /**
     * GET  /dobavljacs/:id : get the "id" dobavljac.
     *
     * @param id the id of the dobavljac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dobavljac, or with status 404 (Not Found)
     */
    @GetMapping("/dobavljacs/{id}")
    @Timed
    public ResponseEntity<Dobavljac> getDobavljac(@PathVariable Long id) {
        log.debug("REST request to get Dobavljac : {}", id);
        Optional<Dobavljac> dobavljac = dobavljacService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dobavljac);
    }

    /**
     * DELETE  /dobavljacs/:id : delete the "id" dobavljac.
     *
     * @param id the id of the dobavljac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dobavljacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteDobavljac(@PathVariable Long id) {
        log.debug("REST request to delete Dobavljac : {}", id);
        dobavljacService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
