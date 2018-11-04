package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Radionica;
import edu.man.prod.service.RadionicaService;
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
 * REST controller for managing Radionica.
 */
@RestController
@RequestMapping("/api")
public class RadionicaResource {

    private final Logger log = LoggerFactory.getLogger(RadionicaResource.class);

    private static final String ENTITY_NAME = "radionica";

    private final RadionicaService radionicaService;

    public RadionicaResource(RadionicaService radionicaService) {
        this.radionicaService = radionicaService;
    }

    /**
     * POST  /radionicas : Create a new radionica.
     *
     * @param radionica the radionica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new radionica, or with status 400 (Bad Request) if the radionica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/radionicas")
    @Timed
    public ResponseEntity<Radionica> createRadionica(@RequestBody Radionica radionica) throws URISyntaxException {
        log.debug("REST request to save Radionica : {}", radionica);
        if (radionica.getId() != null) {
            throw new BadRequestAlertException("A new radionica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Radionica result = radionicaService.save(radionica);
        return ResponseEntity.created(new URI("/api/radionicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /radionicas : Updates an existing radionica.
     *
     * @param radionica the radionica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated radionica,
     * or with status 400 (Bad Request) if the radionica is not valid,
     * or with status 500 (Internal Server Error) if the radionica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/radionicas")
    @Timed
    public ResponseEntity<Radionica> updateRadionica(@RequestBody Radionica radionica) throws URISyntaxException {
        log.debug("REST request to update Radionica : {}", radionica);
        if (radionica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Radionica result = radionicaService.save(radionica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, radionica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /radionicas : get all the radionicas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of radionicas in body
     */
    @GetMapping("/radionicas")
    @Timed
    public List<Radionica> getAllRadionicas() {
        log.debug("REST request to get all Radionicas");
        return radionicaService.findAll();
    }

    /**
     * GET  /radionicas/:id : get the "id" radionica.
     *
     * @param id the id of the radionica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the radionica, or with status 404 (Not Found)
     */
    @GetMapping("/radionicas/{id}")
    @Timed
    public ResponseEntity<Radionica> getRadionica(@PathVariable Long id) {
        log.debug("REST request to get Radionica : {}", id);
        Optional<Radionica> radionica = radionicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radionica);
    }

    /**
     * DELETE  /radionicas/:id : delete the "id" radionica.
     *
     * @param id the id of the radionica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/radionicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteRadionica(@PathVariable Long id) {
        log.debug("REST request to delete Radionica : {}", id);
        radionicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
