package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.OperacijeURadnomNalogu;
import edu.man.prod.service.OperacijeURadnomNaloguService;
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
 * REST controller for managing OperacijeURadnomNalogu.
 */
@RestController
@RequestMapping("/api")
public class OperacijeURadnomNaloguResource {

    private final Logger log = LoggerFactory.getLogger(OperacijeURadnomNaloguResource.class);

    private static final String ENTITY_NAME = "operacijeURadnomNalogu";

    private final OperacijeURadnomNaloguService operacijeURadnomNaloguService;

    public OperacijeURadnomNaloguResource(OperacijeURadnomNaloguService operacijeURadnomNaloguService) {
        this.operacijeURadnomNaloguService = operacijeURadnomNaloguService;
    }

    /**
     * POST  /operacije-u-radnom-nalogus : Create a new operacijeURadnomNalogu.
     *
     * @param operacijeURadnomNalogu the operacijeURadnomNalogu to create
     * @return the ResponseEntity with status 201 (Created) and with body the new operacijeURadnomNalogu, or with status 400 (Bad Request) if the operacijeURadnomNalogu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/operacije-u-radnom-nalogus")
    @Timed
    public ResponseEntity<OperacijeURadnomNalogu> createOperacijeURadnomNalogu(@RequestBody OperacijeURadnomNalogu operacijeURadnomNalogu) throws URISyntaxException {
        log.debug("REST request to save OperacijeURadnomNalogu : {}", operacijeURadnomNalogu);
        if (operacijeURadnomNalogu.getId() != null) {
            throw new BadRequestAlertException("A new operacijeURadnomNalogu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperacijeURadnomNalogu result = operacijeURadnomNaloguService.save(operacijeURadnomNalogu);
        return ResponseEntity.created(new URI("/api/operacije-u-radnom-nalogus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /operacije-u-radnom-nalogus : Updates an existing operacijeURadnomNalogu.
     *
     * @param operacijeURadnomNalogu the operacijeURadnomNalogu to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated operacijeURadnomNalogu,
     * or with status 400 (Bad Request) if the operacijeURadnomNalogu is not valid,
     * or with status 500 (Internal Server Error) if the operacijeURadnomNalogu couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/operacije-u-radnom-nalogus")
    @Timed
    public ResponseEntity<OperacijeURadnomNalogu> updateOperacijeURadnomNalogu(@RequestBody OperacijeURadnomNalogu operacijeURadnomNalogu) throws URISyntaxException {
        log.debug("REST request to update OperacijeURadnomNalogu : {}", operacijeURadnomNalogu);
        if (operacijeURadnomNalogu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperacijeURadnomNalogu result = operacijeURadnomNaloguService.save(operacijeURadnomNalogu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, operacijeURadnomNalogu.getId().toString()))
            .body(result);
    }

    /**
     * GET  /operacije-u-radnom-nalogus : get all the operacijeURadnomNalogus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of operacijeURadnomNalogus in body
     */
    @GetMapping("/operacije-u-radnom-nalogus")
    @Timed
    public List<OperacijeURadnomNalogu> getAllOperacijeURadnomNalogus() {
        log.debug("REST request to get all OperacijeURadnomNalogus");
        return operacijeURadnomNaloguService.findAll();
    }

    /**
     * GET  /operacije-u-radnom-nalogus/:id : get the "id" operacijeURadnomNalogu.
     *
     * @param id the id of the operacijeURadnomNalogu to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the operacijeURadnomNalogu, or with status 404 (Not Found)
     */
    @GetMapping("/operacije-u-radnom-nalogus/{id}")
    @Timed
    public ResponseEntity<OperacijeURadnomNalogu> getOperacijeURadnomNalogu(@PathVariable Long id) {
        log.debug("REST request to get OperacijeURadnomNalogu : {}", id);
        Optional<OperacijeURadnomNalogu> operacijeURadnomNalogu = operacijeURadnomNaloguService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operacijeURadnomNalogu);
    }

    /**
     * DELETE  /operacije-u-radnom-nalogus/:id : delete the "id" operacijeURadnomNalogu.
     *
     * @param id the id of the operacijeURadnomNalogu to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/operacije-u-radnom-nalogus/{id}")
    @Timed
    public ResponseEntity<Void> deleteOperacijeURadnomNalogu(@PathVariable Long id) {
        log.debug("REST request to delete OperacijeURadnomNalogu : {}", id);
        operacijeURadnomNaloguService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
