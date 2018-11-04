package edu.man.prod.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.man.prod.domain.TransakcijeUMagacinu;
import edu.man.prod.service.TransakcijeUMagacinuService;
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
 * REST controller for managing TransakcijeUMagacinu.
 */
@RestController
@RequestMapping("/api")
public class TransakcijeUMagacinuResource {

    private final Logger log = LoggerFactory.getLogger(TransakcijeUMagacinuResource.class);

    private static final String ENTITY_NAME = "transakcijeUMagacinu";

    private final TransakcijeUMagacinuService transakcijeUMagacinuService;

    public TransakcijeUMagacinuResource(TransakcijeUMagacinuService transakcijeUMagacinuService) {
        this.transakcijeUMagacinuService = transakcijeUMagacinuService;
    }

    /**
     * POST  /transakcije-u-magacinus : Create a new transakcijeUMagacinu.
     *
     * @param transakcijeUMagacinu the transakcijeUMagacinu to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transakcijeUMagacinu, or with status 400 (Bad Request) if the transakcijeUMagacinu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transakcije-u-magacinus")
    @Timed
    public ResponseEntity<TransakcijeUMagacinu> createTransakcijeUMagacinu(@RequestBody TransakcijeUMagacinu transakcijeUMagacinu) throws URISyntaxException {
        log.debug("REST request to save TransakcijeUMagacinu : {}", transakcijeUMagacinu);
        if (transakcijeUMagacinu.getId() != null) {
            throw new BadRequestAlertException("A new transakcijeUMagacinu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransakcijeUMagacinu result = transakcijeUMagacinuService.save(transakcijeUMagacinu);
        return ResponseEntity.created(new URI("/api/transakcije-u-magacinus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transakcije-u-magacinus : Updates an existing transakcijeUMagacinu.
     *
     * @param transakcijeUMagacinu the transakcijeUMagacinu to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transakcijeUMagacinu,
     * or with status 400 (Bad Request) if the transakcijeUMagacinu is not valid,
     * or with status 500 (Internal Server Error) if the transakcijeUMagacinu couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transakcije-u-magacinus")
    @Timed
    public ResponseEntity<TransakcijeUMagacinu> updateTransakcijeUMagacinu(@RequestBody TransakcijeUMagacinu transakcijeUMagacinu) throws URISyntaxException {
        log.debug("REST request to update TransakcijeUMagacinu : {}", transakcijeUMagacinu);
        if (transakcijeUMagacinu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransakcijeUMagacinu result = transakcijeUMagacinuService.save(transakcijeUMagacinu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transakcijeUMagacinu.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transakcije-u-magacinus : get all the transakcijeUMagacinus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of transakcijeUMagacinus in body
     */
    @GetMapping("/transakcije-u-magacinus")
    @Timed
    public List<TransakcijeUMagacinu> getAllTransakcijeUMagacinus() {
        log.debug("REST request to get all TransakcijeUMagacinus");
        return transakcijeUMagacinuService.findAll();
    }

    /**
     * GET  /transakcije-u-magacinus/:id : get the "id" transakcijeUMagacinu.
     *
     * @param id the id of the transakcijeUMagacinu to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transakcijeUMagacinu, or with status 404 (Not Found)
     */
    @GetMapping("/transakcije-u-magacinus/{id}")
    @Timed
    public ResponseEntity<TransakcijeUMagacinu> getTransakcijeUMagacinu(@PathVariable Long id) {
        log.debug("REST request to get TransakcijeUMagacinu : {}", id);
        Optional<TransakcijeUMagacinu> transakcijeUMagacinu = transakcijeUMagacinuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transakcijeUMagacinu);
    }

    /**
     * DELETE  /transakcije-u-magacinus/:id : delete the "id" transakcijeUMagacinu.
     *
     * @param id the id of the transakcijeUMagacinu to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transakcije-u-magacinus/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransakcijeUMagacinu(@PathVariable Long id) {
        log.debug("REST request to delete TransakcijeUMagacinu : {}", id);
        transakcijeUMagacinuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
