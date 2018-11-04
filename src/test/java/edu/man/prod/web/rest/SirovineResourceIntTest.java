package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Sirovine;
import edu.man.prod.repository.SirovineRepository;
import edu.man.prod.service.SirovineService;
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
import java.util.List;


import static edu.man.prod.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.man.prod.domain.enumeration.JedMere;
/**
 * Test class for the SirovineResource REST controller.
 *
 * @see SirovineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class SirovineResourceIntTest {

    private static final String DEFAULT_SIFRA = "AAAAAAAAAA";
    private static final String UPDATED_SIFRA = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_VRSTA = "AAAAAAAAAA";
    private static final String UPDATED_VRSTA = "BBBBBBBBBB";

    private static final JedMere DEFAULT_JED_MERE = JedMere.KOM;
    private static final JedMere UPDATED_JED_MERE = JedMere.KG;

    private static final String DEFAULT_NAMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAMENA = "BBBBBBBBBB";

    private static final Double DEFAULT_NABAVNA_CENA = 1D;
    private static final Double UPDATED_NABAVNA_CENA = 2D;

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private SirovineRepository sirovineRepository;
    
    @Autowired
    private SirovineService sirovineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSirovineMockMvc;

    private Sirovine sirovine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SirovineResource sirovineResource = new SirovineResource(sirovineService);
        this.restSirovineMockMvc = MockMvcBuilders.standaloneSetup(sirovineResource)
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
    public static Sirovine createEntity(EntityManager em) {
        Sirovine sirovine = new Sirovine()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return sirovine;
    }

    @Before
    public void initTest() {
        sirovine = createEntity(em);
    }

    @Test
    @Transactional
    public void createSirovine() throws Exception {
        int databaseSizeBeforeCreate = sirovineRepository.findAll().size();

        // Create the Sirovine
        restSirovineMockMvc.perform(post("/api/sirovines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sirovine)))
            .andExpect(status().isCreated());

        // Validate the Sirovine in the database
        List<Sirovine> sirovineList = sirovineRepository.findAll();
        assertThat(sirovineList).hasSize(databaseSizeBeforeCreate + 1);
        Sirovine testSirovine = sirovineList.get(sirovineList.size() - 1);
        assertThat(testSirovine.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testSirovine.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testSirovine.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testSirovine.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testSirovine.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testSirovine.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testSirovine.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createSirovineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sirovineRepository.findAll().size();

        // Create the Sirovine with an existing ID
        sirovine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSirovineMockMvc.perform(post("/api/sirovines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sirovine)))
            .andExpect(status().isBadRequest());

        // Validate the Sirovine in the database
        List<Sirovine> sirovineList = sirovineRepository.findAll();
        assertThat(sirovineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSirovines() throws Exception {
        // Initialize the database
        sirovineRepository.saveAndFlush(sirovine);

        // Get all the sirovineList
        restSirovineMockMvc.perform(get("/api/sirovines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sirovine.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifra").value(hasItem(DEFAULT_SIFRA.toString())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].nabavnaCena").value(hasItem(DEFAULT_NABAVNA_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getSirovine() throws Exception {
        // Initialize the database
        sirovineRepository.saveAndFlush(sirovine);

        // Get the sirovine
        restSirovineMockMvc.perform(get("/api/sirovines/{id}", sirovine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sirovine.getId().intValue()))
            .andExpect(jsonPath("$.sifra").value(DEFAULT_SIFRA.toString()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.nabavnaCena").value(DEFAULT_NABAVNA_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSirovine() throws Exception {
        // Get the sirovine
        restSirovineMockMvc.perform(get("/api/sirovines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSirovine() throws Exception {
        // Initialize the database
        sirovineService.save(sirovine);

        int databaseSizeBeforeUpdate = sirovineRepository.findAll().size();

        // Update the sirovine
        Sirovine updatedSirovine = sirovineRepository.findById(sirovine.getId()).get();
        // Disconnect from session so that the updates on updatedSirovine are not directly saved in db
        em.detach(updatedSirovine);
        updatedSirovine
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restSirovineMockMvc.perform(put("/api/sirovines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSirovine)))
            .andExpect(status().isOk());

        // Validate the Sirovine in the database
        List<Sirovine> sirovineList = sirovineRepository.findAll();
        assertThat(sirovineList).hasSize(databaseSizeBeforeUpdate);
        Sirovine testSirovine = sirovineList.get(sirovineList.size() - 1);
        assertThat(testSirovine.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testSirovine.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testSirovine.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testSirovine.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testSirovine.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testSirovine.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testSirovine.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingSirovine() throws Exception {
        int databaseSizeBeforeUpdate = sirovineRepository.findAll().size();

        // Create the Sirovine

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSirovineMockMvc.perform(put("/api/sirovines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sirovine)))
            .andExpect(status().isBadRequest());

        // Validate the Sirovine in the database
        List<Sirovine> sirovineList = sirovineRepository.findAll();
        assertThat(sirovineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSirovine() throws Exception {
        // Initialize the database
        sirovineService.save(sirovine);

        int databaseSizeBeforeDelete = sirovineRepository.findAll().size();

        // Get the sirovine
        restSirovineMockMvc.perform(delete("/api/sirovines/{id}", sirovine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sirovine> sirovineList = sirovineRepository.findAll();
        assertThat(sirovineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sirovine.class);
        Sirovine sirovine1 = new Sirovine();
        sirovine1.setId(1L);
        Sirovine sirovine2 = new Sirovine();
        sirovine2.setId(sirovine1.getId());
        assertThat(sirovine1).isEqualTo(sirovine2);
        sirovine2.setId(2L);
        assertThat(sirovine1).isNotEqualTo(sirovine2);
        sirovine1.setId(null);
        assertThat(sirovine1).isNotEqualTo(sirovine2);
    }
}
