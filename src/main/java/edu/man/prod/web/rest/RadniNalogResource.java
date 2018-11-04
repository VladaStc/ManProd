package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.RadniNalog;
import edu.man.prod.service.RadniNalogService;
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
 * REST controller for managing RadniNalog.
 */
@RestController
@RequestMapping("/api")
public class RadniNalogResource {

    private final Logger log = LoggerFactory.getLogger(RadniNalogResource.class);

    private static final String ENTITY_NAME = "radniNalog";

    private final RadniNalogService radniNalogService;

    public RadniNalogResource(RadniNalogService radniNalogService) {
        this.radniNalogService = radniNalogService;
    }

    /**
     * POST  /radni-nalogs : Create a new radniNalog.
     *
     * @param radniNalog the radniNalog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new radniNalog, or with status 400 (Bad Request) if the radniNalog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/radni-nalogs")
    @Timed
    public ResponseEntity<RadniNalog> createRadniNalog(@RequestBody RadniNalog radniNalog) throws URISyntaxException {
        log.debug("REST request to save RadniNalog : {}", radniNalog);
        if (radniNalog.getId() != null) {
            throw new BadRequestAlertException("A new radniNalog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RadniNalog result = radniNalogService.save(radniNalog);
        return ResponseEntity.created(new URI("/api/radni-nalogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /radni-nalogs : Updates an existing radniNalog.
     *
     * @param radniNalog the radniNalog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated radniNalog,
     * or with status 400 (Bad Request) if the radniNalog is not valid,
     * or with status 500 (Internal Server Error) if the radniNalog couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/radni-nalogs")
    @Timed
    public ResponseEntity<RadniNalog> updateRadniNalog(@RequestBody RadniNalog radniNalog) throws URISyntaxException {
        log.debug("REST request to update RadniNalog : {}", radniNalog);
        if (radniNalog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RadniNalog result = radniNalogService.save(radniNalog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, radniNalog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /radni-nalogs : get all the radniNalogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of radniNalogs in body
     */
    @GetMapping("/radni-nalogs")
    @Timed
    public ResponseEntity<List<RadniNalog>> getAllRadniNalogs(Pageable pageable) {
        log.debug("REST request to get a page of RadniNalogs");
        Page<RadniNalog> page = radniNalogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/radni-nalogs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /radni-nalogs/:id : get the "id" radniNalog.
     *
     * @param id the id of the radniNalog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the radniNalog, or with status 404 (Not Found)
     */
    @GetMapping("/radni-nalogs/{id}")
    @Timed
    public ResponseEntity<RadniNalog> getRadniNalog(@PathVariable Long id) {
        log.debug("REST request to get RadniNalog : {}", id);
        Optional<RadniNalog> radniNalog = radniNalogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radniNalog);
    }

    /**
     * DELETE  /radni-nalogs/:id : delete the "id" radniNalog.
     *
     * @param id the id of the radniNalog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/radni-nalogs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRadniNalog(@PathVariable Long id) {
        log.debug("REST request to delete RadniNalog : {}", id);
        radniNalogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
