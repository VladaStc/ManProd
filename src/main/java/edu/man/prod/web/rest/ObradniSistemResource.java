package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.ObradniSistem;
import edu.man.prod.service.ObradniSistemService;
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
 * REST controller for managing ObradniSistem.
 */
@RestController
@RequestMapping("/api")
public class ObradniSistemResource {

    private final Logger log = LoggerFactory.getLogger(ObradniSistemResource.class);

    private static final String ENTITY_NAME = "obradniSistem";

    private final ObradniSistemService obradniSistemService;

    public ObradniSistemResource(ObradniSistemService obradniSistemService) {
        this.obradniSistemService = obradniSistemService;
    }

    /**
     * POST  /obradni-sistems : Create a new obradniSistem.
     *
     * @param obradniSistem the obradniSistem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new obradniSistem, or with status 400 (Bad Request) if the obradniSistem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/obradni-sistems")
    @Timed
    public ResponseEntity<ObradniSistem> createObradniSistem(@RequestBody ObradniSistem obradniSistem) throws URISyntaxException {
        log.debug("REST request to save ObradniSistem : {}", obradniSistem);
        if (obradniSistem.getId() != null) {
            throw new BadRequestAlertException("A new obradniSistem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObradniSistem result = obradniSistemService.save(obradniSistem);
        return ResponseEntity.created(new URI("/api/obradni-sistems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /obradni-sistems : Updates an existing obradniSistem.
     *
     * @param obradniSistem the obradniSistem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated obradniSistem,
     * or with status 400 (Bad Request) if the obradniSistem is not valid,
     * or with status 500 (Internal Server Error) if the obradniSistem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/obradni-sistems")
    @Timed
    public ResponseEntity<ObradniSistem> updateObradniSistem(@RequestBody ObradniSistem obradniSistem) throws URISyntaxException {
        log.debug("REST request to update ObradniSistem : {}", obradniSistem);
        if (obradniSistem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObradniSistem result = obradniSistemService.save(obradniSistem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, obradniSistem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /obradni-sistems : get all the obradniSistems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of obradniSistems in body
     */
    @GetMapping("/obradni-sistems")
    @Timed
    public List<ObradniSistem> getAllObradniSistems() {
        log.debug("REST request to get all ObradniSistems");
        return obradniSistemService.findAll();
    }

    /**
     * GET  /obradni-sistems/:id : get the "id" obradniSistem.
     *
     * @param id the id of the obradniSistem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the obradniSistem, or with status 404 (Not Found)
     */
    @GetMapping("/obradni-sistems/{id}")
    @Timed
    public ResponseEntity<ObradniSistem> getObradniSistem(@PathVariable Long id) {
        log.debug("REST request to get ObradniSistem : {}", id);
        Optional<ObradniSistem> obradniSistem = obradniSistemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(obradniSistem);
    }

    /**
     * DELETE  /obradni-sistems/:id : delete the "id" obradniSistem.
     *
     * @param id the id of the obradniSistem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/obradni-sistems/{id}")
    @Timed
    public ResponseEntity<Void> deleteObradniSistem(@PathVariable Long id) {
        log.debug("REST request to delete ObradniSistem : {}", id);
        obradniSistemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
