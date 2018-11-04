package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.StavkeUMagacinu;
import edu.man.prod.service.StavkeUMagacinuService;
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
 * REST controller for managing StavkeUMagacinu.
 */
@RestController
@RequestMapping("/api")
public class StavkeUMagacinuResource {

    private final Logger log = LoggerFactory.getLogger(StavkeUMagacinuResource.class);

    private static final String ENTITY_NAME = "stavkeUMagacinu";

    private final StavkeUMagacinuService stavkeUMagacinuService;

    public StavkeUMagacinuResource(StavkeUMagacinuService stavkeUMagacinuService) {
        this.stavkeUMagacinuService = stavkeUMagacinuService;
    }

    /**
     * POST  /stavke-u-magacinus : Create a new stavkeUMagacinu.
     *
     * @param stavkeUMagacinu the stavkeUMagacinu to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stavkeUMagacinu, or with status 400 (Bad Request) if the stavkeUMagacinu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stavke-u-magacinus")
    @Timed
    public ResponseEntity<StavkeUMagacinu> createStavkeUMagacinu(@RequestBody StavkeUMagacinu stavkeUMagacinu) throws URISyntaxException {
        log.debug("REST request to save StavkeUMagacinu : {}", stavkeUMagacinu);
        if (stavkeUMagacinu.getId() != null) {
            throw new BadRequestAlertException("A new stavkeUMagacinu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StavkeUMagacinu result = stavkeUMagacinuService.save(stavkeUMagacinu);
        return ResponseEntity.created(new URI("/api/stavke-u-magacinus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stavke-u-magacinus : Updates an existing stavkeUMagacinu.
     *
     * @param stavkeUMagacinu the stavkeUMagacinu to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stavkeUMagacinu,
     * or with status 400 (Bad Request) if the stavkeUMagacinu is not valid,
     * or with status 500 (Internal Server Error) if the stavkeUMagacinu couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stavke-u-magacinus")
    @Timed
    public ResponseEntity<StavkeUMagacinu> updateStavkeUMagacinu(@RequestBody StavkeUMagacinu stavkeUMagacinu) throws URISyntaxException {
        log.debug("REST request to update StavkeUMagacinu : {}", stavkeUMagacinu);
        if (stavkeUMagacinu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StavkeUMagacinu result = stavkeUMagacinuService.save(stavkeUMagacinu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stavkeUMagacinu.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stavke-u-magacinus : get all the stavkeUMagacinus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of stavkeUMagacinus in body
     */
    @GetMapping("/stavke-u-magacinus")
    @Timed
    public List<StavkeUMagacinu> getAllStavkeUMagacinus() {
        log.debug("REST request to get all StavkeUMagacinus");
        return stavkeUMagacinuService.findAll();
    }

    /**
     * GET  /stavke-u-magacinus/:id : get the "id" stavkeUMagacinu.
     *
     * @param id the id of the stavkeUMagacinu to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stavkeUMagacinu, or with status 404 (Not Found)
     */
    @GetMapping("/stavke-u-magacinus/{id}")
    @Timed
    public ResponseEntity<StavkeUMagacinu> getStavkeUMagacinu(@PathVariable Long id) {
        log.debug("REST request to get StavkeUMagacinu : {}", id);
        Optional<StavkeUMagacinu> stavkeUMagacinu = stavkeUMagacinuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stavkeUMagacinu);
    }

    /**
     * DELETE  /stavke-u-magacinus/:id : delete the "id" stavkeUMagacinu.
     *
     * @param id the id of the stavkeUMagacinu to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stavke-u-magacinus/{id}")
    @Timed
    public ResponseEntity<Void> deleteStavkeUMagacinu(@PathVariable Long id) {
        log.debug("REST request to delete StavkeUMagacinu : {}", id);
        stavkeUMagacinuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
