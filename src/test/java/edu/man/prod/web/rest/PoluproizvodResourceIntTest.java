package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Poluproizvod;
import edu.man.prod.repository.PoluproizvodRepository;
import edu.man.prod.service.PoluproizvodService;
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
 * Test class for the PoluproizvodResource REST controller.
 *
 * @see PoluproizvodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class PoluproizvodResourceIntTest {

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

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private PoluproizvodRepository poluproizvodRepository;
    
    @Autowired
    private PoluproizvodService poluproizvodService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPoluproizvodMockMvc;

    private Poluproizvod poluproizvod;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PoluproizvodResource poluproizvodResource = new PoluproizvodResource(poluproizvodService);
        this.restPoluproizvodMockMvc = MockMvcBuilders.standaloneSetup(poluproizvodResource)
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
    public static Poluproizvod createEntity(EntityManager em) {
        Poluproizvod poluproizvod = new Poluproizvod()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return poluproizvod;
    }

    @Before
    public void initTest() {
        poluproizvod = createEntity(em);
    }

    @Test
    @Transactional
    public void createPoluproizvod() throws Exception {
        int databaseSizeBeforeCreate = poluproizvodRepository.findAll().size();

        // Create the Poluproizvod
        restPoluproizvodMockMvc.perform(post("/api/poluproizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poluproizvod)))
            .andExpect(status().isCreated());

        // Validate the Poluproizvod in the database
        List<Poluproizvod> poluproizvodList = poluproizvodRepository.findAll();
        assertThat(poluproizvodList).hasSize(databaseSizeBeforeCreate + 1);
        Poluproizvod testPoluproizvod = poluproizvodList.get(poluproizvodList.size() - 1);
        assertThat(testPoluproizvod.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testPoluproizvod.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testPoluproizvod.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testPoluproizvod.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testPoluproizvod.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testPoluproizvod.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createPoluproizvodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = poluproizvodRepository.findAll().size();

        // Create the Poluproizvod with an existing ID
        poluproizvod.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPoluproizvodMockMvc.perform(post("/api/poluproizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poluproizvod)))
            .andExpect(status().isBadRequest());

        // Validate the Poluproizvod in the database
        List<Poluproizvod> poluproizvodList = poluproizvodRepository.findAll();
        assertThat(poluproizvodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPoluproizvods() throws Exception {
        // Initialize the database
        poluproizvodRepository.saveAndFlush(poluproizvod);

        // Get all the poluproizvodList
        restPoluproizvodMockMvc.perform(get("/api/poluproizvods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(poluproizvod.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getPoluproizvod() throws Exception {
        // Initialize the database
        poluproizvodRepository.saveAndFlush(poluproizvod);

        // Get the poluproizvod
        restPoluproizvodMockMvc.perform(get("/api/poluproizvods/{id}", poluproizvod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(poluproizvod.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPoluproizvod() throws Exception {
        // Get the poluproizvod
        restPoluproizvodMockMvc.perform(get("/api/poluproizvods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePoluproizvod() throws Exception {
        // Initialize the database
        poluproizvodService.save(poluproizvod);

        int databaseSizeBeforeUpdate = poluproizvodRepository.findAll().size();

        // Update the poluproizvod
        Poluproizvod updatedPoluproizvod = poluproizvodRepository.findById(poluproizvod.getId()).get();
        // Disconnect from session so that the updates on updatedPoluproizvod are not directly saved in db
        em.detach(updatedPoluproizvod);
        updatedPoluproizvod
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .napomena(UPDATED_NAPOMENA);

        restPoluproizvodMockMvc.perform(put("/api/poluproizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPoluproizvod)))
            .andExpect(status().isOk());

        // Validate the Poluproizvod in the database
        List<Poluproizvod> poluproizvodList = poluproizvodRepository.findAll();
        assertThat(poluproizvodList).hasSize(databaseSizeBeforeUpdate);
        Poluproizvod testPoluproizvod = poluproizvodList.get(poluproizvodList.size() - 1);
        assertThat(testPoluproizvod.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testPoluproizvod.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testPoluproizvod.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testPoluproizvod.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testPoluproizvod.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testPoluproizvod.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingPoluproizvod() throws Exception {
        int databaseSizeBeforeUpdate = poluproizvodRepository.findAll().size();

        // Create the Poluproizvod

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPoluproizvodMockMvc.perform(put("/api/poluproizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poluproizvod)))
            .andExpect(status().isBadRequest());

        // Validate the Poluproizvod in the database
        List<Poluproizvod> poluproizvodList = poluproizvodRepository.findAll();
        assertThat(poluproizvodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePoluproizvod() throws Exception {
        // Initialize the database
        poluproizvodService.save(poluproizvod);

        int databaseSizeBeforeDelete = poluproizvodRepository.findAll().size();

        // Get the poluproizvod
        restPoluproizvodMockMvc.perform(delete("/api/poluproizvods/{id}", poluproizvod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Poluproizvod> poluproizvodList = poluproizvodRepository.findAll();
        assertThat(poluproizvodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Poluproizvod.class);
        Poluproizvod poluproizvod1 = new Poluproizvod();
        poluproizvod1.setId(1L);
        Poluproizvod poluproizvod2 = new Poluproizvod();
        poluproizvod2.setId(poluproizvod1.getId());
        assertThat(poluproizvod1).isEqualTo(poluproizvod2);
        poluproizvod2.setId(2L);
        assertThat(poluproizvod1).isNotEqualTo(poluproizvod2);
        poluproizvod1.setId(null);
        assertThat(poluproizvod1).isNotEqualTo(poluproizvod2);
    }
}
