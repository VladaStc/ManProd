package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Zahvati;
import edu.man.prod.service.ZahvatiService;
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
 * REST controller for managing Zahvati.
 */
@RestController
@RequestMapping("/api")
public class ZahvatiResource {

    private final Logger log = LoggerFactory.getLogger(ZahvatiResource.class);

    private static final String ENTITY_NAME = "zahvati";

    private final ZahvatiService zahvatiService;

    public ZahvatiResource(ZahvatiService zahvatiService) {
        this.zahvatiService = zahvatiService;
    }

    /**
     * POST  /zahvatis : Create a new zahvati.
     *
     * @param zahvati the zahvati to create
     * @return the ResponseEntity with status 201 (Created) and with body the new zahvati, or with status 400 (Bad Request) if the zahvati has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/zahvatis")
    @Timed
    public ResponseEntity<Zahvati> createZahvati(@RequestBody Zahvati zahvati) throws URISyntaxException {
        log.debug("REST request to save Zahvati : {}", zahvati);
        if (zahvati.getId() != null) {
            throw new BadRequestAlertException("A new zahvati cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Zahvati result = zahvatiService.save(zahvati);
        return ResponseEntity.created(new URI("/api/zahvatis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /zahvatis : Updates an existing zahvati.
     *
     * @param zahvati the zahvati to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated zahvati,
     * or with status 400 (Bad Request) if the zahvati is not valid,
     * or with status 500 (Internal Server Error) if the zahvati couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/zahvatis")
    @Timed
    public ResponseEntity<Zahvati> updateZahvati(@RequestBody Zahvati zahvati) throws URISyntaxException {
        log.debug("REST request to update Zahvati : {}", zahvati);
        if (zahvati.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Zahvati result = zahvatiService.save(zahvati);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, zahvati.getId().toString()))
            .body(result);
    }

    /**
     * GET  /zahvatis : get all the zahvatis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of zahvatis in body
     */
    @GetMapping("/zahvatis")
    @Timed
    public List<Zahvati> getAllZahvatis() {
        log.debug("REST request to get all Zahvatis");
        return zahvatiService.findAll();
    }

    /**
     * GET  /zahvatis/:id : get the "id" zahvati.
     *
     * @param id the id of the zahvati to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the zahvati, or with status 404 (Not Found)
     */
    @GetMapping("/zahvatis/{id}")
    @Timed
    public ResponseEntity<Zahvati> getZahvati(@PathVariable Long id) {
        log.debug("REST request to get Zahvati : {}", id);
        Optional<Zahvati> zahvati = zahvatiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zahvati);
    }

    /**
     * DELETE  /zahvatis/:id : delete the "id" zahvati.
     *
     * @param id the id of the zahvati to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/zahvatis/{id}")
    @Timed
    public ResponseEntity<Void> deleteZahvati(@PathVariable Long id) {
        log.debug("REST request to delete Zahvati : {}", id);
        zahvatiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
