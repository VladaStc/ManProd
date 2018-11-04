package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.OstaliMaterijali;
import edu.man.prod.service.OstaliMaterijaliService;
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
 * REST controller for managing OstaliMaterijali.
 */
@RestController
@RequestMapping("/api")
public class OstaliMaterijaliResource {

    private final Logger log = LoggerFactory.getLogger(OstaliMaterijaliResource.class);

    private static final String ENTITY_NAME = "ostaliMaterijali";

    private final OstaliMaterijaliService ostaliMaterijaliService;

    public OstaliMaterijaliResource(OstaliMaterijaliService ostaliMaterijaliService) {
        this.ostaliMaterijaliService = ostaliMaterijaliService;
    }

    /**
     * POST  /ostali-materijalis : Create a new ostaliMaterijali.
     *
     * @param ostaliMaterijali the ostaliMaterijali to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ostaliMaterijali, or with status 400 (Bad Request) if the ostaliMaterijali has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ostali-materijalis")
    @Timed
    public ResponseEntity<OstaliMaterijali> createOstaliMaterijali(@RequestBody OstaliMaterijali ostaliMaterijali) throws URISyntaxException {
        log.debug("REST request to save OstaliMaterijali : {}", ostaliMaterijali);
        if (ostaliMaterijali.getId() != null) {
            throw new BadRequestAlertException("A new ostaliMaterijali cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OstaliMaterijali result = ostaliMaterijaliService.save(ostaliMaterijali);
        return ResponseEntity.created(new URI("/api/ostali-materijalis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ostali-materijalis : Updates an existing ostaliMaterijali.
     *
     * @param ostaliMaterijali the ostaliMaterijali to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ostaliMaterijali,
     * or with status 400 (Bad Request) if the ostaliMaterijali is not valid,
     * or with status 500 (Internal Server Error) if the ostaliMaterijali couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ostali-materijalis")
    @Timed
    public ResponseEntity<OstaliMaterijali> updateOstaliMaterijali(@RequestBody OstaliMaterijali ostaliMaterijali) throws URISyntaxException {
        log.debug("REST request to update OstaliMaterijali : {}", ostaliMaterijali);
        if (ostaliMaterijali.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OstaliMaterijali result = ostaliMaterijaliService.save(ostaliMaterijali);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ostaliMaterijali.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ostali-materijalis : get all the ostaliMaterijalis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ostaliMaterijalis in body
     */
    @GetMapping("/ostali-materijalis")
    @Timed
    public List<OstaliMaterijali> getAllOstaliMaterijalis() {
        log.debug("REST request to get all OstaliMaterijalis");
        return ostaliMaterijaliService.findAll();
    }

    /**
     * GET  /ostali-materijalis/:id : get the "id" ostaliMaterijali.
     *
     * @param id the id of the ostaliMaterijali to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ostaliMaterijali, or with status 404 (Not Found)
     */
    @GetMapping("/ostali-materijalis/{id}")
    @Timed
    public ResponseEntity<OstaliMaterijali> getOstaliMaterijali(@PathVariable Long id) {
        log.debug("REST request to get OstaliMaterijali : {}", id);
        Optional<OstaliMaterijali> ostaliMaterijali = ostaliMaterijaliService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ostaliMaterijali);
    }

    /**
     * DELETE  /ostali-materijalis/:id : delete the "id" ostaliMaterijali.
     *
     * @param id the id of the ostaliMaterijali to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ostali-materijalis/{id}")
    @Timed
    public ResponseEntity<Void> deleteOstaliMaterijali(@PathVariable Long id) {
        log.debug("REST request to delete OstaliMaterijali : {}", id);
        ostaliMaterijaliService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
