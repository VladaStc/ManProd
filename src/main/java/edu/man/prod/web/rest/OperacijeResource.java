package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Operacije;
import edu.man.prod.service.OperacijeService;
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
 * REST controller for managing Operacije.
 */
@RestController
@RequestMapping("/api")
public class OperacijeResource {

    private final Logger log = LoggerFactory.getLogger(OperacijeResource.class);

    private static final String ENTITY_NAME = "operacije";

    private final OperacijeService operacijeService;

    public OperacijeResource(OperacijeService operacijeService) {
        this.operacijeService = operacijeService;
    }

    /**
     * POST  /operacijes : Create a new operacije.
     *
     * @param operacije the operacije to create
     * @return the ResponseEntity with status 201 (Created) and with body the new operacije, or with status 400 (Bad Request) if the operacije has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/operacijes")
    @Timed
    public ResponseEntity<Operacije> createOperacije(@RequestBody Operacije operacije) throws URISyntaxException {
        log.debug("REST request to save Operacije : {}", operacije);
        if (operacije.getId() != null) {
            throw new BadRequestAlertException("A new operacije cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Operacije result = operacijeService.save(operacije);
        return ResponseEntity.created(new URI("/api/operacijes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /operacijes : Updates an existing operacije.
     *
     * @param operacije the operacije to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated operacije,
     * or with status 400 (Bad Request) if the operacije is not valid,
     * or with status 500 (Internal Server Error) if the operacije couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/operacijes")
    @Timed
    public ResponseEntity<Operacije> updateOperacije(@RequestBody Operacije operacije) throws URISyntaxException {
        log.debug("REST request to update Operacije : {}", operacije);
        if (operacije.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Operacije result = operacijeService.save(operacije);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, operacije.getId().toString()))
            .body(result);
    }

    /**
     * GET  /operacijes : get all the operacijes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of operacijes in body
     */
    @GetMapping("/operacijes")
    @Timed
    public List<Operacije> getAllOperacijes() {
        log.debug("REST request to get all Operacijes");
        return operacijeService.findAll();
    }

    /**
     * GET  /operacijes/:id : get the "id" operacije.
     *
     * @param id the id of the operacije to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the operacije, or with status 404 (Not Found)
     */
    @GetMapping("/operacijes/{id}")
    @Timed
    public ResponseEntity<Operacije> getOperacije(@PathVariable Long id) {
        log.debug("REST request to get Operacije : {}", id);
        Optional<Operacije> operacije = operacijeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operacije);
    }

    /**
     * DELETE  /operacijes/:id : delete the "id" operacije.
     *
     * @param id the id of the operacije to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/operacijes/{id}")
    @Timed
    public ResponseEntity<Void> deleteOperacije(@PathVariable Long id) {
        log.debug("REST request to delete Operacije : {}", id);
        operacijeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
