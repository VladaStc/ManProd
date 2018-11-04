package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Radnici;
import edu.man.prod.service.RadniciService;
import edu.man.prod.web.rest.errors.BadRequestAlertException;
import edu.man.prod.web.rest.util.HeaderUtil;
import edu.man.prod.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Radnici.
 */
@RestController
@RequestMapping("/api")
public class RadniciResource {

    private final Logger log = LoggerFactory.getLogger(RadniciResource.class);

    private static final String ENTITY_NAME = "radnici";

    private final RadniciService radniciService;

    public RadniciResource(RadniciService radniciService) {
        this.radniciService = radniciService;
    }

    /**
     * POST  /radnicis : Create a new radnici.
     *
     * @param radnici the radnici to create
     * @return the ResponseEntity with status 201 (Created) and with body the new radnici, or with status 400 (Bad Request) if the radnici has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/radnicis")
    @Timed
    public ResponseEntity<Radnici> createRadnici(@RequestBody Radnici radnici) throws URISyntaxException {
        log.debug("REST request to save Radnici : {}", radnici);
        if (radnici.getId() != null) {
            throw new BadRequestAlertException("A new radnici cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Radnici result = radniciService.save(radnici);
        return ResponseEntity.created(new URI("/api/radnicis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /radnicis : Updates an existing radnici.
     *
     * @param radnici the radnici to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated radnici,
     * or with status 400 (Bad Request) if the radnici is not valid,
     * or with status 500 (Internal Server Error) if the radnici couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/radnicis")
    @Timed
    public ResponseEntity<Radnici> updateRadnici(@RequestBody Radnici radnici) throws URISyntaxException {
        log.debug("REST request to update Radnici : {}", radnici);
        if (radnici.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Radnici result = radniciService.save(radnici);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, radnici.getId().toString()))
            .body(result);
    }

    /**
     * GET  /radnicis : get all the radnicis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of radnicis in body
     */
    @GetMapping("/radnicis")
    @Timed
    public ResponseEntity<List<Radnici>> getAllRadnicis(Pageable pageable) {
        log.debug("REST request to get a page of Radnicis");
        Page<Radnici> page = radniciService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/radnicis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /radnicis/:id : get the "id" radnici.
     *
     * @param id the id of the radnici to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the radnici, or with status 404 (Not Found)
     */
    @GetMapping("/radnicis/{id}")
    @Timed
    public ResponseEntity<Radnici> getRadnici(@PathVariable Long id) {
        log.debug("REST request to get Radnici : {}", id);
        Optional<Radnici> radnici = radniciService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radnici);
    }

    /**
     * DELETE  /radnicis/:id : delete the "id" radnici.
     *
     * @param id the id of the radnici to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/radnicis/{id}")
    @Timed
    public ResponseEntity<Void> deleteRadnici(@PathVariable Long id) {
        log.debug("REST request to delete Radnici : {}", id);
        radniciService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
