package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.Masina;
import edu.man.prod.service.MasinaService;
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
 * REST controller for managing Masina.
 */
@RestController
@RequestMapping("/api")
public class MasinaResource {

    private final Logger log = LoggerFactory.getLogger(MasinaResource.class);

    private static final String ENTITY_NAME = "masina";

    private final MasinaService masinaService;

    public MasinaResource(MasinaService masinaService) {
        this.masinaService = masinaService;
    }

    /**
     * POST  /masinas : Create a new masina.
     *
     * @param masina the masina to create
     * @return the ResponseEntity with status 201 (Created) and with body the new masina, or with status 400 (Bad Request) if the masina has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/masinas")
    @Timed
    public ResponseEntity<Masina> createMasina(@RequestBody Masina masina) throws URISyntaxException {
        log.debug("REST request to save Masina : {}", masina);
        if (masina.getId() != null) {
            throw new BadRequestAlertException("A new masina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Masina result = masinaService.save(masina);
        return ResponseEntity.created(new URI("/api/masinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /masinas : Updates an existing masina.
     *
     * @param masina the masina to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated masina,
     * or with status 400 (Bad Request) if the masina is not valid,
     * or with status 500 (Internal Server Error) if the masina couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/masinas")
    @Timed
    public ResponseEntity<Masina> updateMasina(@RequestBody Masina masina) throws URISyntaxException {
        log.debug("REST request to update Masina : {}", masina);
        if (masina.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Masina result = masinaService.save(masina);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, masina.getId().toString()))
            .body(result);
    }

    /**
     * GET  /masinas : get all the masinas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of masinas in body
     */
    @GetMapping("/masinas")
    @Timed
    public List<Masina> getAllMasinas() {
        log.debug("REST request to get all Masinas");
        return masinaService.findAll();
    }

    /**
     * GET  /masinas/:id : get the "id" masina.
     *
     * @param id the id of the masina to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the masina, or with status 404 (Not Found)
     */
    @GetMapping("/masinas/{id}")
    @Timed
    public ResponseEntity<Masina> getMasina(@PathVariable Long id) {
        log.debug("REST request to get Masina : {}", id);
        Optional<Masina> masina = masinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(masina);
    }

    /**
     * DELETE  /masinas/:id : delete the "id" masina.
     *
     * @param id the id of the masina to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/masinas/{id}")
    @Timed
    public ResponseEntity<Void> deleteMasina(@PathVariable Long id) {
        log.debug("REST request to delete Masina : {}", id);
        masinaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
