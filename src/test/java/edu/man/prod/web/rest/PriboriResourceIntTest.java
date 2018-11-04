package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Pribori;
import edu.man.prod.repository.PriboriRepository;
import edu.man.prod.service.PriboriService;
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

import edu.man.prod.domain.enumeration.JedMere;
/**
 * Test class for the PriboriResource REST controller.
 *
 * @see PriboriResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class PriboriResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_VRSTA = "AAAAAAAAAA";
    private static final String UPDATED_VRSTA = "BBBBBBBBBB";

    private static final JedMere DEFAULT_JED_MERE = JedMere.KOM;
    private static final JedMere UPDATED_JED_MERE = JedMere.KG;

    private static final String DEFAULT_NAMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAMENA = "BBBBBBBBBB";

    private static final Double DEFAULT_CENA = 1D;
    private static final Double UPDATED_CENA = 2D;

    private static final Instant DEFAULT_TRAJANJE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRAJANJE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PriboriRepository priboriRepository;
    
    @Autowired
    private PriboriService priboriService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPriboriMockMvc;

    private Pribori pribori;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PriboriResource priboriResource = new PriboriResource(priboriService);
        this.restPriboriMockMvc = MockMvcBuilders.standaloneSetup(priboriResource)
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
    public static Pribori createEntity(EntityManager em) {
        Pribori pribori = new Pribori()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .trajanje(DEFAULT_TRAJANJE);
        return pribori;
    }

    @Before
    public void initTest() {
        pribori = createEntity(em);
    }

    @Test
    @Transactional
    public void createPribori() throws Exception {
        int databaseSizeBeforeCreate = priboriRepository.findAll().size();

        // Create the Pribori
        restPriboriMockMvc.perform(post("/api/priboris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pribori)))
            .andExpect(status().isCreated());

        // Validate the Pribori in the database
        List<Pribori> priboriList = priboriRepository.findAll();
        assertThat(priboriList).hasSize(databaseSizeBeforeCreate + 1);
        Pribori testPribori = priboriList.get(priboriList.size() - 1);
        assertThat(testPribori.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testPribori.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testPribori.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testPribori.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testPribori.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testPribori.getTrajanje()).isEqualTo(DEFAULT_TRAJANJE);
    }

    @Test
    @Transactional
    public void createPriboriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = priboriRepository.findAll().size();

        // Create the Pribori with an existing ID
        pribori.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriboriMockMvc.perform(post("/api/priboris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pribori)))
            .andExpect(status().isBadRequest());

        // Validate the Pribori in the database
        List<Pribori> priboriList = priboriRepository.findAll();
        assertThat(priboriList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPriboris() throws Exception {
        // Initialize the database
        priboriRepository.saveAndFlush(pribori);

        // Get all the priboriList
        restPriboriMockMvc.perform(get("/api/priboris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pribori.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].trajanje").value(hasItem(DEFAULT_TRAJANJE.toString())));
    }
    
    @Test
    @Transactional
    public void getPribori() throws Exception {
        // Initialize the database
        priboriRepository.saveAndFlush(pribori);

        // Get the pribori
        restPriboriMockMvc.perform(get("/api/priboris/{id}", pribori.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pribori.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.trajanje").value(DEFAULT_TRAJANJE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPribori() throws Exception {
        // Get the pribori
        restPriboriMockMvc.perform(get("/api/priboris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePribori() throws Exception {
        // Initialize the database
        priboriService.save(pribori);

        int databaseSizeBeforeUpdate = priboriRepository.findAll().size();

        // Update the pribori
        Pribori updatedPribori = priboriRepository.findById(pribori.getId()).get();
        // Disconnect from session so that the updates on updatedPribori are not directly saved in db
        em.detach(updatedPribori);
        updatedPribori
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .trajanje(UPDATED_TRAJANJE);

        restPriboriMockMvc.perform(put("/api/priboris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPribori)))
            .andExpect(status().isOk());

        // Validate the Pribori in the database
        List<Pribori> priboriList = priboriRepository.findAll();
        assertThat(priboriList).hasSize(databaseSizeBeforeUpdate);
        Pribori testPribori = priboriList.get(priboriList.size() - 1);
        assertThat(testPribori.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testPribori.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testPribori.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testPribori.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testPribori.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testPribori.getTrajanje()).isEqualTo(UPDATED_TRAJANJE);
    }

    @Test
    @Transactional
    public void updateNonExistingPribori() throws Exception {
        int databaseSizeBeforeUpdate = priboriRepository.findAll().size();

        // Create the Pribori

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriboriMockMvc.perform(put("/api/priboris")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pribori)))
            .andExpect(status().isBadRequest());

        // Validate the Pribori in the database
        List<Pribori> priboriList = priboriRepository.findAll();
        assertThat(priboriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePribori() throws Exception {
        // Initialize the database
        priboriService.save(pribori);

        int databaseSizeBeforeDelete = priboriRepository.findAll().size();

        // Get the pribori
        restPriboriMockMvc.perform(delete("/api/priboris/{id}", pribori.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pribori> priboriList = priboriRepository.findAll();
        assertThat(priboriList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pribori.class);
        Pribori pribori1 = new Pribori();
        pribori1.setId(1L);
        Pribori pribori2 = new Pribori();
        pribori2.setId(pribori1.getId());
        assertThat(pribori1).isEqualTo(pribori2);
        pribori2.setId(2L);
        assertThat(pribori1).isNotEqualTo(pribori2);
        pribori1.setId(null);
        assertThat(pribori1).isNotEqualTo(pribori2);
    }
}
