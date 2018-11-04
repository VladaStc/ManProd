package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Magacini;
import edu.man.prod.service.MagaciniService;
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
 * REST controller for managing Magacini.
 */
@RestController
@RequestMapping("/api")
public class MagaciniResource {

    private final Logger log = LoggerFactory.getLogger(MagaciniResource.class);

    private static final String ENTITY_NAME = "magacini";

    private final MagaciniService magaciniService;

    public MagaciniResource(MagaciniService magaciniService) {
        this.magaciniService = magaciniService;
    }

    /**
     * POST  /magacinis : Create a new magacini.
     *
     * @param magacini the magacini to create
     * @return the ResponseEntity with status 201 (Created) and with body the new magacini, or with status 400 (Bad Request) if the magacini has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/magacinis")
    @Timed
    public ResponseEntity<Magacini> createMagacini(@RequestBody Magacini magacini) throws URISyntaxException {
        log.debug("REST request to save Magacini : {}", magacini);
        if (magacini.getId() != null) {
            throw new BadRequestAlertException("A new magacini cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Magacini result = magaciniService.save(magacini);
        return ResponseEntity.created(new URI("/api/magacinis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /magacinis : Updates an existing magacini.
     *
     * @param magacini the magacini to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated magacini,
     * or with status 400 (Bad Request) if the magacini is not valid,
     * or with status 500 (Internal Server Error) if the magacini couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/magacinis")
    @Timed
    public ResponseEntity<Magacini> updateMagacini(@RequestBody Magacini magacini) throws URISyntaxException {
        log.debug("REST request to update Magacini : {}", magacini);
        if (magacini.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Magacini result = magaciniService.save(magacini);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, magacini.getId().toString()))
            .body(result);
    }

    /**
     * GET  /magacinis : get all the magacinis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of magacinis in body
     */
    @GetMapping("/magacinis")
    @Timed
    public List<Magacini> getAllMagacinis() {
        log.debug("REST request to get all Magacinis");
        return magaciniService.findAll();
    }

    /**
     * GET  /magacinis/:id : get the "id" magacini.
     *
     * @param id the id of the magacini to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the magacini, or with status 404 (Not Found)
     */
    @GetMapping("/magacinis/{id}")
    @Timed
    public ResponseEntity<Magacini> getMagacini(@PathVariable Long id) {
        log.debug("REST request to get Magacini : {}", id);
        Optional<Magacini> magacini = magaciniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(magacini);
    }

    /**
     * DELETE  /magacinis/:id : delete the "id" magacini.
     *
     * @param id the id of the magacini to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/magacinis/{id}")
    @Timed
    public ResponseEntity<Void> deleteMagacini(@PathVariable Long id) {
        log.debug("REST request to delete Magacini : {}", id);
        magaciniService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
