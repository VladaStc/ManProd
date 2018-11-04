package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.MerniAlati;
import edu.man.prod.repository.MerniAlatiRepository;
import edu.man.prod.service.MerniAlatiService;
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
 * Test class for the MerniAlatiResource REST controller.
 *
 * @see MerniAlatiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class MerniAlatiResourceIntTest {

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

    private static final Instant DEFAULT_BAZDARENJE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BAZDARENJE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MerniAlatiRepository merniAlatiRepository;
    
    @Autowired
    private MerniAlatiService merniAlatiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMerniAlatiMockMvc;

    private MerniAlati merniAlati;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MerniAlatiResource merniAlatiResource = new MerniAlatiResource(merniAlatiService);
        this.restMerniAlatiMockMvc = MockMvcBuilders.standaloneSetup(merniAlatiResource)
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
    public static MerniAlati createEntity(EntityManager em) {
        MerniAlati merniAlati = new MerniAlati()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .trajanje(DEFAULT_TRAJANJE)
            .bazdarenje(DEFAULT_BAZDARENJE);
        return merniAlati;
    }

    @Before
    public void initTest() {
        merniAlati = createEntity(em);
    }

    @Test
    @Transactional
    public void createMerniAlati() throws Exception {
        int databaseSizeBeforeCreate = merniAlatiRepository.findAll().size();

        // Create the MerniAlati
        restMerniAlatiMockMvc.perform(post("/api/merni-alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merniAlati)))
            .andExpect(status().isCreated());

        // Validate the MerniAlati in the database
        List<MerniAlati> merniAlatiList = merniAlatiRepository.findAll();
        assertThat(merniAlatiList).hasSize(databaseSizeBeforeCreate + 1);
        MerniAlati testMerniAlati = merniAlatiList.get(merniAlatiList.size() - 1);
        assertThat(testMerniAlati.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testMerniAlati.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testMerniAlati.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testMerniAlati.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testMerniAlati.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testMerniAlati.getTrajanje()).isEqualTo(DEFAULT_TRAJANJE);
        assertThat(testMerniAlati.getBazdarenje()).isEqualTo(DEFAULT_BAZDARENJE);
    }

    @Test
    @Transactional
    public void createMerniAlatiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = merniAlatiRepository.findAll().size();

        // Create the MerniAlati with an existing ID
        merniAlati.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMerniAlatiMockMvc.perform(post("/api/merni-alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merniAlati)))
            .andExpect(status().isBadRequest());

        // Validate the MerniAlati in the database
        List<MerniAlati> merniAlatiList = merniAlatiRepository.findAll();
        assertThat(merniAlatiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMerniAlatis() throws Exception {
        // Initialize the database
        merniAlatiRepository.saveAndFlush(merniAlati);

        // Get all the merniAlatiList
        restMerniAlatiMockMvc.perform(get("/api/merni-alatis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(merniAlati.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].trajanje").value(hasItem(DEFAULT_TRAJANJE.toString())))
            .andExpect(jsonPath("$.[*].bazdarenje").value(hasItem(DEFAULT_BAZDARENJE.toString())));
    }
    
    @Test
    @Transactional
    public void getMerniAlati() throws Exception {
        // Initialize the database
        merniAlatiRepository.saveAndFlush(merniAlati);

        // Get the merniAlati
        restMerniAlatiMockMvc.perform(get("/api/merni-alatis/{id}", merniAlati.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(merniAlati.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.trajanje").value(DEFAULT_TRAJANJE.toString()))
            .andExpect(jsonPath("$.bazdarenje").value(DEFAULT_BAZDARENJE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMerniAlati() throws Exception {
        // Get the merniAlati
        restMerniAlatiMockMvc.perform(get("/api/merni-alatis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMerniAlati() throws Exception {
        // Initialize the database
        merniAlatiService.save(merniAlati);

        int databaseSizeBeforeUpdate = merniAlatiRepository.findAll().size();

        // Update the merniAlati
        MerniAlati updatedMerniAlati = merniAlatiRepository.findById(merniAlati.getId()).get();
        // Disconnect from session so that the updates on updatedMerniAlati are not directly saved in db
        em.detach(updatedMerniAlati);
        updatedMerniAlati
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .trajanje(UPDATED_TRAJANJE)
            .bazdarenje(UPDATED_BAZDARENJE);

        restMerniAlatiMockMvc.perform(put("/api/merni-alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMerniAlati)))
            .andExpect(status().isOk());

        // Validate the MerniAlati in the database
        List<MerniAlati> merniAlatiList = merniAlatiRepository.findAll();
        assertThat(merniAlatiList).hasSize(databaseSizeBeforeUpdate);
        MerniAlati testMerniAlati = merniAlatiList.get(merniAlatiList.size() - 1);
        assertThat(testMerniAlati.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testMerniAlati.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testMerniAlati.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testMerniAlati.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testMerniAlati.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testMerniAlati.getTrajanje()).isEqualTo(UPDATED_TRAJANJE);
        assertThat(testMerniAlati.getBazdarenje()).isEqualTo(UPDATED_BAZDARENJE);
    }

    @Test
    @Transactional
    public void updateNonExistingMerniAlati() throws Exception {
        int databaseSizeBeforeUpdate = merniAlatiRepository.findAll().size();

        // Create the MerniAlati

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMerniAlatiMockMvc.perform(put("/api/merni-alatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(merniAlati)))
            .andExpect(status().isBadRequest());

        // Validate the MerniAlati in the database
        List<MerniAlati> merniAlatiList = merniAlatiRepository.findAll();
        assertThat(merniAlatiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMerniAlati() throws Exception {
        // Initialize the database
        merniAlatiService.save(merniAlati);

        int databaseSizeBeforeDelete = merniAlatiRepository.findAll().size();

        // Get the merniAlati
        restMerniAlatiMockMvc.perform(delete("/api/merni-alatis/{id}", merniAlati.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MerniAlati> merniAlatiList = merniAlatiRepository.findAll();
        assertThat(merniAlatiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MerniAlati.class);
        MerniAlati merniAlati1 = new MerniAlati();
        merniAlati1.setId(1L);
        MerniAlati merniAlati2 = new MerniAlati();
        merniAlati2.setId(merniAlati1.getId());
        assertThat(merniAlati1).isEqualTo(merniAlati2);
        merniAlati2.setId(2L);
        assertThat(merniAlati1).isNotEqualTo(merniAlati2);
        merniAlati1.setId(null);
        assertThat(merniAlati1).isNotEqualTo(merniAlati2);
    }
}
