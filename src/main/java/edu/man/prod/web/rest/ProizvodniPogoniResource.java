package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.ProizvodniPogoni;
import edu.man.prod.service.ProizvodniPogoniService;
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
 * REST controller for managing ProizvodniPogoni.
 */
@RestController
@RequestMapping("/api")
public class ProizvodniPogoniResource {

    private final Logger log = LoggerFactory.getLogger(ProizvodniPogoniResource.class);

    private static final String ENTITY_NAME = "proizvodniPogoni";

    private final ProizvodniPogoniService proizvodniPogoniService;

    public ProizvodniPogoniResource(ProizvodniPogoniService proizvodniPogoniService) {
        this.proizvodniPogoniService = proizvodniPogoniService;
    }

    /**
     * POST  /proizvodni-pogonis : Create a new proizvodniPogoni.
     *
     * @param proizvodniPogoni the proizvodniPogoni to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proizvodniPogoni, or with status 400 (Bad Request) if the proizvodniPogoni has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/proizvodni-pogonis")
    @Timed
    public ResponseEntity<ProizvodniPogoni> createProizvodniPogoni(@RequestBody ProizvodniPogoni proizvodniPogoni) throws URISyntaxException {
        log.debug("REST request to save ProizvodniPogoni : {}", proizvodniPogoni);
        if (proizvodniPogoni.getId() != null) {
            throw new BadRequestAlertException("A new proizvodniPogoni cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProizvodniPogoni result = proizvodniPogoniService.save(proizvodniPogoni);
        return ResponseEntity.created(new URI("/api/proizvodni-pogonis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proizvodni-pogonis : Updates an existing proizvodniPogoni.
     *
     * @param proizvodniPogoni the proizvodniPogoni to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proizvodniPogoni,
     * or with status 400 (Bad Request) if the proizvodniPogoni is not valid,
     * or with status 500 (Internal Server Error) if the proizvodniPogoni couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/proizvodni-pogonis")
    @Timed
    public ResponseEntity<ProizvodniPogoni> updateProizvodniPogoni(@RequestBody ProizvodniPogoni proizvodniPogoni) throws URISyntaxException {
        log.debug("REST request to update ProizvodniPogoni : {}", proizvodniPogoni);
        if (proizvodniPogoni.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProizvodniPogoni result = proizvodniPogoniService.save(proizvodniPogoni);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proizvodniPogoni.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proizvodni-pogonis : get all the proizvodniPogonis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of proizvodniPogonis in body
     */
    @GetMapping("/proizvodni-pogonis")
    @Timed
    public List<ProizvodniPogoni> getAllProizvodniPogonis() {
        log.debug("REST request to get all ProizvodniPogonis");
        return proizvodniPogoniService.findAll();
    }

    /**
     * GET  /proizvodni-pogonis/:id : get the "id" proizvodniPogoni.
     *
     * @param id the id of the proizvodniPogoni to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proizvodniPogoni, or with status 404 (Not Found)
     */
    @GetMapping("/proizvodni-pogonis/{id}")
    @Timed
    public ResponseEntity<ProizvodniPogoni> getProizvodniPogoni(@PathVariable Long id) {
        log.debug("REST request to get ProizvodniPogoni : {}", id);
        Optional<ProizvodniPogoni> proizvodniPogoni = proizvodniPogoniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proizvodniPogoni);
    }

    /**
     * DELETE  /proizvodni-pogonis/:id : delete the "id" proizvodniPogoni.
     *
     * @param id the id of the proizvodniPogoni to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/proizvodni-pogonis/{id}")
    @Timed
    public ResponseEntity<Void> deleteProizvodniPogoni(@PathVariable Long id) {
        log.debug("REST request to delete ProizvodniPogoni : {}", id);
        proizvodniPogoniService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
