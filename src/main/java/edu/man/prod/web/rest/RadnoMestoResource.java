package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.RadnoMesto;
import edu.man.prod.service.RadnoMestoService;
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
 * REST controller for managing RadnoMesto.
 */
@RestController
@RequestMapping("/api")
public class RadnoMestoResource {

    private final Logger log = LoggerFactory.getLogger(RadnoMestoResource.class);

    private static final String ENTITY_NAME = "radnoMesto";

    private final RadnoMestoService radnoMestoService;

    public RadnoMestoResource(RadnoMestoService radnoMestoService) {
        this.radnoMestoService = radnoMestoService;
    }

    /**
     * POST  /radno-mestos : Create a new radnoMesto.
     *
     * @param radnoMesto the radnoMesto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new radnoMesto, or with status 400 (Bad Request) if the radnoMesto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/radno-mestos")
    @Timed
    public ResponseEntity<RadnoMesto> createRadnoMesto(@RequestBody RadnoMesto radnoMesto) throws URISyntaxException {
        log.debug("REST request to save RadnoMesto : {}", radnoMesto);
        if (radnoMesto.getId() != null) {
            throw new BadRequestAlertException("A new radnoMesto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RadnoMesto result = radnoMestoService.save(radnoMesto);
        return ResponseEntity.created(new URI("/api/radno-mestos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /radno-mestos : Updates an existing radnoMesto.
     *
     * @param radnoMesto the radnoMesto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated radnoMesto,
     * or with status 400 (Bad Request) if the radnoMesto is not valid,
     * or with status 500 (Internal Server Error) if the radnoMesto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/radno-mestos")
    @Timed
    public ResponseEntity<RadnoMesto> updateRadnoMesto(@RequestBody RadnoMesto radnoMesto) throws URISyntaxException {
        log.debug("REST request to update RadnoMesto : {}", radnoMesto);
        if (radnoMesto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RadnoMesto result = radnoMestoService.save(radnoMesto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, radnoMesto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /radno-mestos : get all the radnoMestos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of radnoMestos in body
     */
    @GetMapping("/radno-mestos")
    @Timed
    public List<RadnoMesto> getAllRadnoMestos() {
        log.debug("REST request to get all RadnoMestos");
        return radnoMestoService.findAll();
    }

    /**
     * GET  /radno-mestos/:id : get the "id" radnoMesto.
     *
     * @param id the id of the radnoMesto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the radnoMesto, or with status 404 (Not Found)
     */
    @GetMapping("/radno-mestos/{id}")
    @Timed
    public ResponseEntity<RadnoMesto> getRadnoMesto(@PathVariable Long id) {
        log.debug("REST request to get RadnoMesto : {}", id);
        Optional<RadnoMesto> radnoMesto = radnoMestoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radnoMesto);
    }

    /**
     * DELETE  /radno-mestos/:id : delete the "id" radnoMesto.
     *
     * @param id the id of the radnoMesto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/radno-mestos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRadnoMesto(@PathVariable Long id) {
        log.debug("REST request to delete RadnoMesto : {}", id);
        radnoMestoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
