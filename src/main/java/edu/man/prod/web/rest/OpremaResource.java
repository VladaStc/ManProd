package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Oprema;
import edu.man.prod.service.OpremaService;
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
 * REST controller for managing Oprema.
 */
@RestController
@RequestMapping("/api")
public class OpremaResource {

    private final Logger log = LoggerFactory.getLogger(OpremaResource.class);

    private static final String ENTITY_NAME = "oprema";

    private final OpremaService opremaService;

    public OpremaResource(OpremaService opremaService) {
        this.opremaService = opremaService;
    }

    /**
     * POST  /opremas : Create a new oprema.
     *
     * @param oprema the oprema to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oprema, or with status 400 (Bad Request) if the oprema has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/opremas")
    @Timed
    public ResponseEntity<Oprema> createOprema(@RequestBody Oprema oprema) throws URISyntaxException {
        log.debug("REST request to save Oprema : {}", oprema);
        if (oprema.getId() != null) {
            throw new BadRequestAlertException("A new oprema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Oprema result = opremaService.save(oprema);
        return ResponseEntity.created(new URI("/api/opremas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /opremas : Updates an existing oprema.
     *
     * @param oprema the oprema to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oprema,
     * or with status 400 (Bad Request) if the oprema is not valid,
     * or with status 500 (Internal Server Error) if the oprema couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/opremas")
    @Timed
    public ResponseEntity<Oprema> updateOprema(@RequestBody Oprema oprema) throws URISyntaxException {
        log.debug("REST request to update Oprema : {}", oprema);
        if (oprema.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Oprema result = opremaService.save(oprema);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oprema.getId().toString()))
            .body(result);
    }

    /**
     * GET  /opremas : get all the opremas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of opremas in body
     */
    @GetMapping("/opremas")
    @Timed
    public List<Oprema> getAllOpremas() {
        log.debug("REST request to get all Opremas");
        return opremaService.findAll();
    }

    /**
     * GET  /opremas/:id : get the "id" oprema.
     *
     * @param id the id of the oprema to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oprema, or with status 404 (Not Found)
     */
    @GetMapping("/opremas/{id}")
    @Timed
    public ResponseEntity<Oprema> getOprema(@PathVariable Long id) {
        log.debug("REST request to get Oprema : {}", id);
        Optional<Oprema> oprema = opremaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oprema);
    }

    /**
     * DELETE  /opremas/:id : delete the "id" oprema.
     *
     * @param id the id of the oprema to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/opremas/{id}")
    @Timed
    public ResponseEntity<Void> deleteOprema(@PathVariable Long id) {
        log.debug("REST request to delete Oprema : {}", id);
        opremaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
