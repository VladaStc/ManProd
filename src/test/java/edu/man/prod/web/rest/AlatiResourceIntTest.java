package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Alati;
import edu.man.prod.repository.AlatiRepository;
import edu.man.prod.service.AlatiService;
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
 * Test class for the AlatiResource REST controller.
 *
 * @see AlatiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class AlatiResourceIntTest {

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
    private AlatiRepository alatiRepository;
    
    @Autowired
    private AlatiService alatiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlatiMockMvc;

    private Alati alati;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlatiResource alatiResource = new AlatiResource(alatiService);
        this.restAlatiMockMvc = MockMvcBuilders.standaloneSetup(alatiResource)
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
    public static Alati createEntity(EntityManager em) {
        Alati alati = new Alati()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .trajanje(DEFAULT_TRAJANJE);
        return alati;
    }

    @Before
    public void initTest() {
        alati = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlati() throws Exception {
        int databaseSizeBeforeCreate = alatiRepository.findAll().size();

        // Create the Alati
        restAlatiMockMvc.perform(post("/api/alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alati)))
            .andExpect(status().isCreated());

        // Validate the Alati in the database
        List<Alati> alatiList = alatiRepository.findAll();
        assertThat(alatiList).hasSize(databaseSizeBeforeCreate + 1);
        Alati testAlati = alatiList.get(alatiList.size() - 1);
        assertThat(testAlati.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testAlati.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testAlati.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testAlati.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testAlati.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testAlati.getTrajanje()).isEqualTo(DEFAULT_TRAJANJE);
    }

    @Test
    @Transactional
    public void createAlatiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alatiRepository.findAll().size();

        // Create the Alati with an existing ID
        alati.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlatiMockMvc.perform(post("/api/alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alati)))
            .andExpect(status().isBadRequest());

        // Validate the Alati in the database
        List<Alati> alatiList = alatiRepository.findAll();
        assertThat(alatiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAlatis() throws Exception {
        // Initialize the database
        alatiRepository.saveAndFlush(alati);

        // Get all the alatiList
        restAlatiMockMvc.perform(get("/api/alatis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alati.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].trajanje").value(hasItem(DEFAULT_TRAJANJE.toString())));
    }
    
    @Test
    @Transactional
    public void getAlati() throws Exception {
        // Initialize the database
        alatiRepository.saveAndFlush(alati);

        // Get the alati
        restAlatiMockMvc.perform(get("/api/alatis/{id}", alati.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alati.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.trajanje").value(DEFAULT_TRAJANJE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlati() throws Exception {
        // Get the alati
        restAlatiMockMvc.perform(get("/api/alatis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlati() throws Exception {
        // Initialize the database
        alatiService.save(alati);

        int databaseSizeBeforeUpdate = alatiRepository.findAll().size();

        // Update the alati
        Alati updatedAlati = alatiRepository.findById(alati.getId()).get();
        // Disconnect from session so that the updates on updatedAlati are not directly saved in db
        em.detach(updatedAlati);
        updatedAlati
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .trajanje(UPDATED_TRAJANJE);

        restAlatiMockMvc.perform(put("/api/alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlati)))
            .andExpect(status().isOk());

        // Validate the Alati in the database
        List<Alati> alatiList = alatiRepository.findAll();
        assertThat(alatiList).hasSize(databaseSizeBeforeUpdate);
        Alati testAlati = alatiList.get(alatiList.size() - 1);
        assertThat(testAlati.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testAlati.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testAlati.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testAlati.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testAlati.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testAlati.getTrajanje()).isEqualTo(UPDATED_TRAJANJE);
    }

    @Test
    @Transactional
    public void updateNonExistingAlati() throws Exception {
        int databaseSizeBeforeUpdate = alatiRepository.findAll().size();

        // Create the Alati

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlatiMockMvc.perform(put("/api/alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alati)))
            .andExpect(status().isBadRequest());

        // Validate the Alati in the database
        List<Alati> alatiList = alatiRepository.findAll();
        assertThat(alatiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAlati() throws Exception {
        // Initialize the database
        alatiService.save(alati);

        int databaseSizeBeforeDelete = alatiRepository.findAll().size();

        // Get the alati
        restAlatiMockMvc.perform(delete("/api/alatis/{id}", alati.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Alati> alatiList = alatiRepository.findAll();
        assertThat(alatiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alati.class);
        Alati alati1 = new Alati();
        alati1.setId(1L);
        Alati alati2 = new Alati();
        alati2.setId(alati1.getId());
        assertThat(alati1).isEqualTo(alati2);
        alati2.setId(2L);
        assertThat(alati1).isNotEqualTo(alati2);
        alati1.setId(null);
        assertThat(alati1).isNotEqualTo(alati2);
    }
}
