package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.PotrosniMaterijal;
import edu.man.prod.service.PotrosniMaterijalService;
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
 * REST controller for managing PotrosniMaterijal.
 */
@RestController
@RequestMapping("/api")
public class PotrosniMaterijalResource {

    private final Logger log = LoggerFactory.getLogger(PotrosniMaterijalResource.class);

    private static final String ENTITY_NAME = "potrosniMaterijal";

    private final PotrosniMaterijalService potrosniMaterijalService;

    public PotrosniMaterijalResource(PotrosniMaterijalService potrosniMaterijalService) {
        this.potrosniMaterijalService = potrosniMaterijalService;
    }

    /**
     * POST  /potrosni-materijals : Create a new potrosniMaterijal.
     *
     * @param potrosniMaterijal the potrosniMaterijal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new potrosniMaterijal, or with status 400 (Bad Request) if the potrosniMaterijal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/potrosni-materijals")
    @Timed
    public ResponseEntity<PotrosniMaterijal> createPotrosniMaterijal(@RequestBody PotrosniMaterijal potrosniMaterijal) throws URISyntaxException {
        log.debug("REST request to save PotrosniMaterijal : {}", potrosniMaterijal);
        if (potrosniMaterijal.getId() != null) {
            throw new BadRequestAlertException("A new potrosniMaterijal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PotrosniMaterijal result = potrosniMaterijalService.save(potrosniMaterijal);
        return ResponseEntity.created(new URI("/api/potrosni-materijals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /potrosni-materijals : Updates an existing potrosniMaterijal.
     *
     * @param potrosniMaterijal the potrosniMaterijal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated potrosniMaterijal,
     * or with status 400 (Bad Request) if the potrosniMaterijal is not valid,
     * or with status 500 (Internal Server Error) if the potrosniMaterijal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/potrosni-materijals")
    @Timed
    public ResponseEntity<PotrosniMaterijal> updatePotrosniMaterijal(@RequestBody PotrosniMaterijal potrosniMaterijal) throws URISyntaxException {
        log.debug("REST request to update PotrosniMaterijal : {}", potrosniMaterijal);
        if (potrosniMaterijal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PotrosniMaterijal result = potrosniMaterijalService.save(potrosniMaterijal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, potrosniMaterijal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /potrosni-materijals : get all the potrosniMaterijals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of potrosniMaterijals in body
     */
    @GetMapping("/potrosni-materijals")
    @Timed
    public List<PotrosniMaterijal> getAllPotrosniMaterijals() {
        log.debug("REST request to get all PotrosniMaterijals");
        return potrosniMaterijalService.findAll();
    }

    /**
     * GET  /potrosni-materijals/:id : get the "id" potrosniMaterijal.
     *
     * @param id the id of the potrosniMaterijal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the potrosniMaterijal, or with status 404 (Not Found)
     */
    @GetMapping("/potrosni-materijals/{id}")
    @Timed
    public ResponseEntity<PotrosniMaterijal> getPotrosniMaterijal(@PathVariable Long id) {
        log.debug("REST request to get PotrosniMaterijal : {}", id);
        Optional<PotrosniMaterijal> potrosniMaterijal = potrosniMaterijalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(potrosniMaterijal);
    }

    /**
     * DELETE  /potrosni-materijals/:id : delete the "id" potrosniMaterijal.
     *
     * @param id the id of the potrosniMaterijal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/potrosni-materijals/{id}")
    @Timed
    public ResponseEntity<Void> deletePotrosniMaterijal(@PathVariable Long id) {
        log.debug("REST request to delete PotrosniMaterijal : {}", id);
        potrosniMaterijalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
