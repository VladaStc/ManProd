package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.ProizvodneLinije;
import edu.man.prod.service.ProizvodneLinijeService;
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
 * REST controller for managing ProizvodneLinije.
 */
@RestController
@RequestMapping("/api")
public class ProizvodneLinijeResource {

    private final Logger log = LoggerFactory.getLogger(ProizvodneLinijeResource.class);

    private static final String ENTITY_NAME = "proizvodneLinije";

    private final ProizvodneLinijeService proizvodneLinijeService;

    public ProizvodneLinijeResource(ProizvodneLinijeService proizvodneLinijeService) {
        this.proizvodneLinijeService = proizvodneLinijeService;
    }

    /**
     * POST  /proizvodne-linijes : Create a new proizvodneLinije.
     *
     * @param proizvodneLinije the proizvodneLinije to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proizvodneLinije, or with status 400 (Bad Request) if the proizvodneLinije has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/proizvodne-linijes")
    @Timed
    public ResponseEntity<ProizvodneLinije> createProizvodneLinije(@RequestBody ProizvodneLinije proizvodneLinije) throws URISyntaxException {
        log.debug("REST request to save ProizvodneLinije : {}", proizvodneLinije);
        if (proizvodneLinije.getId() != null) {
            throw new BadRequestAlertException("A new proizvodneLinije cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProizvodneLinije result = proizvodneLinijeService.save(proizvodneLinije);
        return ResponseEntity.created(new URI("/api/proizvodne-linijes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proizvodne-linijes : Updates an existing proizvodneLinije.
     *
     * @param proizvodneLinije the proizvodneLinije to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proizvodneLinije,
     * or with status 400 (Bad Request) if the proizvodneLinije is not valid,
     * or with status 500 (Internal Server Error) if the proizvodneLinije couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/proizvodne-linijes")
    @Timed
    public ResponseEntity<ProizvodneLinije> updateProizvodneLinije(@RequestBody ProizvodneLinije proizvodneLinije) throws URISyntaxException {
        log.debug("REST request to update ProizvodneLinije : {}", proizvodneLinije);
        if (proizvodneLinije.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProizvodneLinije result = proizvodneLinijeService.save(proizvodneLinije);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proizvodneLinije.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proizvodne-linijes : get all the proizvodneLinijes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of proizvodneLinijes in body
     */
    @GetMapping("/proizvodne-linijes")
    @Timed
    public List<ProizvodneLinije> getAllProizvodneLinijes() {
        log.debug("REST request to get all ProizvodneLinijes");
        return proizvodneLinijeService.findAll();
    }

    /**
     * GET  /proizvodne-linijes/:id : get the "id" proizvodneLinije.
     *
     * @param id the id of the proizvodneLinije to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proizvodneLinije, or with status 404 (Not Found)
     */
    @GetMapping("/proizvodne-linijes/{id}")
    @Timed
    public ResponseEntity<ProizvodneLinije> getProizvodneLinije(@PathVariable Long id) {
        log.debug("REST request to get ProizvodneLinije : {}", id);
        Optional<ProizvodneLinije> proizvodneLinije = proizvodneLinijeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proizvodneLinije);
    }

    /**
     * DELETE  /proizvodne-linijes/:id : delete the "id" proizvodneLinije.
     *
     * @param id the id of the proizvodneLinije to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/proizvodne-linijes/{id}")
    @Timed
    public ResponseEntity<Void> deleteProizvodneLinije(@PathVariable Long id) {
        log.debug("REST request to delete ProizvodneLinije : {}", id);
        proizvodneLinijeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
