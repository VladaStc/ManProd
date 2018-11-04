package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.KonstruktivnaSastavnica;
import edu.man.prod.service.KonstruktivnaSastavnicaService;
import edu.man.prod.web.rest.errors.BadRequestAlertException;
import edu.man.prod.web.rest.util.HeaderUtil;
import edu.man.prod.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing KonstruktivnaSastavnica.
 */
@RestController
@RequestMapping("/api")
public class KonstruktivnaSastavnicaResource {

    private final Logger log = LoggerFactory.getLogger(KonstruktivnaSastavnicaResource.class);

    private static final String ENTITY_NAME = "konstruktivnaSastavnica";

    private final KonstruktivnaSastavnicaService konstruktivnaSastavnicaService;

    public KonstruktivnaSastavnicaResource(KonstruktivnaSastavnicaService konstruktivnaSastavnicaService) {
        this.konstruktivnaSastavnicaService = konstruktivnaSastavnicaService;
    }

    /**
     * POST  /konstruktivna-sastavnicas : Create a new konstruktivnaSastavnica.
     *
     * @param konstruktivnaSastavnica the konstruktivnaSastavnica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new konstruktivnaSastavnica, or with status 400 (Bad Request) if the konstruktivnaSastavnica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/konstruktivna-sastavnicas")
    @Timed
    public ResponseEntity<KonstruktivnaSastavnica> createKonstruktivnaSastavnica(@RequestBody KonstruktivnaSastavnica konstruktivnaSastavnica) throws URISyntaxException {
        log.debug("REST request to save KonstruktivnaSastavnica : {}", konstruktivnaSastavnica);
        if (konstruktivnaSastavnica.getId() != null) {
            throw new BadRequestAlertException("A new konstruktivnaSastavnica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KonstruktivnaSastavnica result = konstruktivnaSastavnicaService.save(konstruktivnaSastavnica);
        return ResponseEntity.created(new URI("/api/konstruktivna-sastavnicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /konstruktivna-sastavnicas : Updates an existing konstruktivnaSastavnica.
     *
     * @param konstruktivnaSastavnica the konstruktivnaSastavnica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated konstruktivnaSastavnica,
     * or with status 400 (Bad Request) if the konstruktivnaSastavnica is not valid,
     * or with status 500 (Internal Server Error) if the konstruktivnaSastavnica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/konstruktivna-sastavnicas")
    @Timed
    public ResponseEntity<KonstruktivnaSastavnica> updateKonstruktivnaSastavnica(@RequestBody KonstruktivnaSastavnica konstruktivnaSastavnica) throws URISyntaxException {
        log.debug("REST request to update KonstruktivnaSastavnica : {}", konstruktivnaSastavnica);
        if (konstruktivnaSastavnica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KonstruktivnaSastavnica result = konstruktivnaSastavnicaService.save(konstruktivnaSastavnica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, konstruktivnaSastavnica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /konstruktivna-sastavnicas : get all the konstruktivnaSastavnicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of konstruktivnaSastavnicas in body
     */
    @GetMapping("/konstruktivna-sastavnicas")
    @Timed
    public ResponseEntity<List<KonstruktivnaSastavnica>> getAllKonstruktivnaSastavnicas(Pageable pageable) {
        log.debug("REST request to get a page of KonstruktivnaSastavnicas");
        Page<KonstruktivnaSastavnica> page = konstruktivnaSastavnicaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/konstruktivna-sastavnicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /konstruktivna-sastavnicas/:id : get the "id" konstruktivnaSastavnica.
     *
     * @param id the id of the konstruktivnaSastavnica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the konstruktivnaSastavnica, or with status 404 (Not Found)
     */
    @GetMapping("/konstruktivna-sastavnicas/{id}")
    @Timed
    public ResponseEntity<KonstruktivnaSastavnica> getKonstruktivnaSastavnica(@PathVariable Long id) {
        log.debug("REST request to get KonstruktivnaSastavnica : {}", id);
        Optional<KonstruktivnaSastavnica> konstruktivnaSastavnica = konstruktivnaSastavnicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(konstruktivnaSastavnica);
    }

    /**
     * DELETE  /konstruktivna-sastavnicas/:id : delete the "id" konstruktivnaSastavnica.
     *
     * @param id the id of the konstruktivnaSastavnica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/konstruktivna-sastavnicas/{id}")
    @Timed
    public ResponseEntity<Void> deleteKonstruktivnaSastavnica(@PathVariable Long id) {
        log.debug("REST request to delete KonstruktivnaSastavnica : {}", id);
        konstruktivnaSastavnicaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
