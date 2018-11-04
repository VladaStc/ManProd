package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.TransakcijeUMagacinu;
import edu.man.prod.repository.TransakcijeUMagacinuRepository;
import edu.man.prod.service.TransakcijeUMagacinuService;
import edu.man.prod.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static edu.man.prod.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.man.prod.domain.enumeration.TipMagacina;
/**
 * Test class for the TransakcijeUMagacinuResource REST controller.
 *
 * @see TransakcijeUMagacinuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class TransakcijeUMagacinuResourceIntTest {

    private static final Double DEFAULT_ULAZ = 1D;
    private static final Double UPDATED_ULAZ = 2D;

    private static final Double DEFAULT_IZLAZ = 1D;
    private static final Double UPDATED_IZLAZ = 2D;

    private static final Double DEFAULT_STANJE = 1D;
    private static final Double UPDATED_STANJE = 2D;

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATUM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATUM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TipMagacina DEFAULT_TIP_MAGACINA = TipMagacina.MAGACIN_SIROVINA;
    private static final TipMagacina UPDATED_TIP_MAGACINA = TipMagacina.MAGACIN_GOTOVIH_PROIZVODA;

    @Autowired
    private TransakcijeUMagacinuRepository transakcijeUMagacinuRepository;
    
    @Autowired
    private TransakcijeUMagacinuService transakcijeUMagacinuService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransakcijeUMagacinuMockMvc;

    private TransakcijeUMagacinu transakcijeUMagacinu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransakcijeUMagacinuResource transakcijeUMagacinuResource = new TransakcijeUMagacinuResource(transakcijeUMagacinuService);
        this.restTransakcijeUMagacinuMockMvc = MockMvcBuilders.standaloneSetup(transakcijeUMagacinuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransakcijeUMagacinu createEntity(EntityManager em) {
        TransakcijeUMagacinu transakcijeUMagacinu = new TransakcijeUMagacinu()
            .ulaz(DEFAULT_ULAZ)
            .izlaz(DEFAULT_IZLAZ)
            .stanje(DEFAULT_STANJE)
            .napomena(DEFAULT_NAPOMENA)
            .datum(DEFAULT_DATUM)
            .tipMagacina(DEFAULT_TIP_MAGACINA);
        return transakcijeUMagacinu;
    }

    @Before
    public void initTest() {
        transakcijeUMagacinu = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransakcijeUMagacinu() throws Exception {
        int databaseSizeBeforeCreate = transakcijeUMagacinuRepository.findAll().size();

        // Create the TransakcijeUMagacinu
        restTransakcijeUMagacinuMockMvc.perform(post("/api/transakcije-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transakcijeUMagacinu)))
            .andExpect(status().isCreated());

        // Validate the TransakcijeUMagacinu in the database
        List<TransakcijeUMagacinu> transakcijeUMagacinuList = transakcijeUMagacinuRepository.findAll();
        assertThat(transakcijeUMagacinuList).hasSize(databaseSizeBeforeCreate + 1);
        TransakcijeUMagacinu testTransakcijeUMagacinu = transakcijeUMagacinuList.get(transakcijeUMagacinuList.size() - 1);
        assertThat(testTransakcijeUMagacinu.getUlaz()).isEqualTo(DEFAULT_ULAZ);
        assertThat(testTransakcijeUMagacinu.getIzlaz()).isEqualTo(DEFAULT_IZLAZ);
        assertThat(testTransakcijeUMagacinu.getStanje()).isEqualTo(DEFAULT_STANJE);
        assertThat(testTransakcijeUMagacinu.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
        assertThat(testTransakcijeUMagacinu.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testTransakcijeUMagacinu.getTipMagacina()).isEqualTo(DEFAULT_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void createTransakcijeUMagacinuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transakcijeUMagacinuRepository.findAll().size();

        // Create the TransakcijeUMagacinu with an existing ID
        transakcijeUMagacinu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransakcijeUMagacinuMockMvc.perform(post("/api/transakcije-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transakcijeUMagacinu)))
            .andExpect(status().isBadRequest());

        // Validate the TransakcijeUMagacinu in the database
        List<TransakcijeUMagacinu> transakcijeUMagacinuList = transakcijeUMagacinuRepository.findAll();
        assertThat(transakcijeUMagacinuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransakcijeUMagacinus() throws Exception {
        // Initialize the database
        transakcijeUMagacinuRepository.saveAndFlush(transakcijeUMagacinu);

        // Get all the transakcijeUMagacinuList
        restTransakcijeUMagacinuMockMvc.perform(get("/api/transakcije-u-magacinus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transakcijeUMagacinu.getId().intValue())))
            .andExpect(jsonPath("$.[*].ulaz").value(hasItem(DEFAULT_ULAZ.doubleValue())))
            .andExpect(jsonPath("$.[*].izlaz").value(hasItem(DEFAULT_IZLAZ.doubleValue())))
            .andExpect(jsonPath("$.[*].stanje").value(hasItem(DEFAULT_STANJE.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())))
            .andExpect(jsonPath("$.[*].datum").value(hasItem(DEFAULT_DATUM.toString())))
            .andExpect(jsonPath("$.[*].tipMagacina").value(hasItem(DEFAULT_TIP_MAGACINA.toString())));
    }
    
    @Test
    @Transactional
    public void getTransakcijeUMagacinu() throws Exception {
        // Initialize the database
        transakcijeUMagacinuRepository.saveAndFlush(transakcijeUMagacinu);

        // Get the transakcijeUMagacinu
        restTransakcijeUMagacinuMockMvc.perform(get("/api/transakcije-u-magacinus/{id}", transakcijeUMagacinu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transakcijeUMagacinu.getId().intValue()))
            .andExpect(jsonPath("$.ulaz").value(DEFAULT_ULAZ.doubleValue()))
            .andExpect(jsonPath("$.izlaz").value(DEFAULT_IZLAZ.doubleValue()))
            .andExpect(jsonPath("$.stanje").value(DEFAULT_STANJE.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()))
            .andExpect(jsonPath("$.datum").value(DEFAULT_DATUM.toString()))
            .andExpect(jsonPath("$.tipMagacina").value(DEFAULT_TIP_MAGACINA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransakcijeUMagacinu() throws Exception {
        // Get the transakcijeUMagacinu
        restTransakcijeUMagacinuMockMvc.perform(get("/api/transakcije-u-magacinus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransakcijeUMagacinu() throws Exception {
        // Initialize the database
        transakcijeUMagacinuService.save(transakcijeUMagacinu);

        int databaseSizeBeforeUpdate = transakcijeUMagacinuRepository.findAll().size();

        // Update the transakcijeUMagacinu
        TransakcijeUMagacinu updatedTransakcijeUMagacinu = transakcijeUMagacinuRepository.findById(transakcijeUMagacinu.getId()).get();
        // Disconnect from session so that the updates on updatedTransakcijeUMagacinu are not directly saved in db
        em.detach(updatedTransakcijeUMagacinu);
        updatedTransakcijeUMagacinu
            .ulaz(UPDATED_ULAZ)
            .izlaz(UPDATED_IZLAZ)
            .stanje(UPDATED_STANJE)
            .napomena(UPDATED_NAPOMENA)
            .datum(UPDATED_DATUM)
            .tipMagacina(UPDATED_TIP_MAGACINA);

        restTransakcijeUMagacinuMockMvc.perform(put("/api/transakcije-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransakcijeUMagacinu)))
            .andExpect(status().isOk());

        // Validate the TransakcijeUMagacinu in the database
        List<TransakcijeUMagacinu> transakcijeUMagacinuList = transakcijeUMagacinuRepository.findAll();
        assertThat(transakcijeUMagacinuList).hasSize(databaseSizeBeforeUpdate);
        TransakcijeUMagacinu testTransakcijeUMagacinu = transakcijeUMagacinuList.get(transakcijeUMagacinuList.size() - 1);
        assertThat(testTransakcijeUMagacinu.getUlaz()).isEqualTo(UPDATED_ULAZ);
        assertThat(testTransakcijeUMagacinu.getIzlaz()).isEqualTo(UPDATED_IZLAZ);
        assertThat(testTransakcijeUMagacinu.getStanje()).isEqualTo(UPDATED_STANJE);
        assertThat(testTransakcijeUMagacinu.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
        assertThat(testTransakcijeUMagacinu.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testTransakcijeUMagacinu.getTipMagacina()).isEqualTo(UPDATED_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void updateNonExistingTransakcijeUMagacinu() throws Exception {
        int databaseSizeBeforeUpdate = transakcijeUMagacinuRepository.findAll().size();

        // Create the TransakcijeUMagacinu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransakcijeUMagacinuMockMvc.perform(put("/api/transakcije-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transakcijeUMagacinu)))
            .andExpect(status().isBadRequest());

        // Validate the TransakcijeUMagacinu in the database
        List<TransakcijeUMagacinu> transakcijeUMagacinuList = transakcijeUMagacinuRepository.findAll();
        assertThat(transakcijeUMagacinuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransakcijeUMagacinu() throws Exception {
        // Initialize the database
        transakcijeUMagacinuService.save(transakcijeUMagacinu);

        int databaseSizeBeforeDelete = transakcijeUMagacinuRepository.findAll().size();

        // Get the transakcijeUMagacinu
        restTransakcijeUMagacinuMockMvc.perform(delete("/api/transakcije-u-magacinus/{id}", transakcijeUMagacinu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransakcijeUMagacinu> transakcijeUMagacinuList = transakcijeUMagacinuRepository.findAll();
        assertThat(transakcijeUMagacinuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransakcijeUMagacinu.class);
        TransakcijeUMagacinu transakcijeUMagacinu1 = new TransakcijeUMagacinu();
        transakcijeUMagacinu1.setId(1L);
        TransakcijeUMagacinu transakcijeUMagacinu2 = new TransakcijeUMagacinu();
        transakcijeUMagacinu2.setId(transakcijeUMagacinu1.getId());
        assertThat(transakcijeUMagacinu1).isEqualTo(transakcijeUMagacinu2);
        transakcijeUMagacinu2.setId(2L);
        assertThat(transakcijeUMagacinu1).isNotEqualTo(transakcijeUMagacinu2);
        transakcijeUMagacinu1.setId(null);
        assertThat(transakcijeUMagacinu1).isNotEqualTo(transakcijeUMagacinu2);
    }
}
