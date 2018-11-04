package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.KupovniMaterijal;
import edu.man.prod.service.KupovniMaterijalService;
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
 * REST controller for managing KupovniMaterijal.
 */
@RestController
@RequestMapping("/api")
public class KupovniMaterijalResource {

    private final Logger log = LoggerFactory.getLogger(KupovniMaterijalResource.class);

    private static final String ENTITY_NAME = "kupovniMaterijal";

    private final KupovniMaterijalService kupovniMaterijalService;

    public KupovniMaterijalResource(KupovniMaterijalService kupovniMaterijalService) {
        this.kupovniMaterijalService = kupovniMaterijalService;
    }

    /**
     * POST  /kupovni-materijals : Create a new kupovniMaterijal.
     *
     * @param kupovniMaterijal the kupovniMaterijal to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kupovniMaterijal, or with status 400 (Bad Request) if the kupovniMaterijal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kupovni-materijals")
    @Timed
    public ResponseEntity<KupovniMaterijal> createKupovniMaterijal(@RequestBody KupovniMaterijal kupovniMaterijal) throws URISyntaxException {
        log.debug("REST request to save KupovniMaterijal : {}", kupovniMaterijal);
        if (kupovniMaterijal.getId() != null) {
            throw new BadRequestAlertException("A new kupovniMaterijal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KupovniMaterijal result = kupovniMaterijalService.save(kupovniMaterijal);
        return ResponseEntity.created(new URI("/api/kupovni-materijals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kupovni-materijals : Updates an existing kupovniMaterijal.
     *
     * @param kupovniMaterijal the kupovniMaterijal to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kupovniMaterijal,
     * or with status 400 (Bad Request) if the kupovniMaterijal is not valid,
     * or with status 500 (Internal Server Error) if the kupovniMaterijal couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kupovni-materijals")
    @Timed
    public ResponseEntity<KupovniMaterijal> updateKupovniMaterijal(@RequestBody KupovniMaterijal kupovniMaterijal) throws URISyntaxException {
        log.debug("REST request to update KupovniMaterijal : {}", kupovniMaterijal);
        if (kupovniMaterijal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KupovniMaterijal result = kupovniMaterijalService.save(kupovniMaterijal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kupovniMaterijal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kupovni-materijals : get all the kupovniMaterijals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of kupovniMaterijals in body
     */
    @GetMapping("/kupovni-materijals")
    @Timed
    public List<KupovniMaterijal> getAllKupovniMaterijals() {
        log.debug("REST request to get all KupovniMaterijals");
        return kupovniMaterijalService.findAll();
    }

    /**
     * GET  /kupovni-materijals/:id : get the "id" kupovniMaterijal.
     *
     * @param id the id of the kupovniMaterijal to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kupovniMaterijal, or with status 404 (Not Found)
     */
    @GetMapping("/kupovni-materijals/{id}")
    @Timed
    public ResponseEntity<KupovniMaterijal> getKupovniMaterijal(@PathVariable Long id) {
        log.debug("REST request to get KupovniMaterijal : {}", id);
        Optional<KupovniMaterijal> kupovniMaterijal = kupovniMaterijalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kupovniMaterijal);
    }

    /**
     * DELETE  /kupovni-materijals/:id : delete the "id" kupovniMaterijal.
     *
     * @param id the id of the kupovniMaterijal to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kupovni-materijals/{id}")
    @Timed
    public ResponseEntity<Void> deleteKupovniMaterijal(@PathVariable Long id) {
        log.debug("REST request to delete KupovniMaterijal : {}", id);
        kupovniMaterijalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
