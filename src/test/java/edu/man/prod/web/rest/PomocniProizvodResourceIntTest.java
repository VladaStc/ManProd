package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.PomocniProizvod;
import edu.man.prod.repository.PomocniProizvodRepository;
import edu.man.prod.service.PomocniProizvodService;
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
 * Test class for the PomocniProizvodResource REST controller.
 *
 * @see PomocniProizvodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class PomocniProizvodResourceIntTest {

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
    private PomocniProizvodRepository pomocniProizvodRepository;
    
    @Autowired
    private PomocniProizvodService pomocniProizvodService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPomocniProizvodMockMvc;

    private PomocniProizvod pomocniProizvod;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PomocniProizvodResource pomocniProizvodResource = new PomocniProizvodResource(pomocniProizvodService);
        this.restPomocniProizvodMockMvc = MockMvcBuilders.standaloneSetup(pomocniProizvodResource)
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
    public static PomocniProizvod createEntity(EntityManager em) {
        PomocniProizvod pomocniProizvod = new PomocniProizvod()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return pomocniProizvod;
    }

    @Before
    public void initTest() {
        pomocniProizvod = createEntity(em);
    }

    @Test
    @Transactional
    public void createPomocniProizvod() throws Exception {
        int databaseSizeBeforeCreate = pomocniProizvodRepository.findAll().size();

        // Create the PomocniProizvod
        restPomocniProizvodMockMvc.perform(post("/api/pomocni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniProizvod)))
            .andExpect(status().isCreated());

        // Validate the PomocniProizvod in the database
        List<PomocniProizvod> pomocniProizvodList = pomocniProizvodRepository.findAll();
        assertThat(pomocniProizvodList).hasSize(databaseSizeBeforeCreate + 1);
        PomocniProizvod testPomocniProizvod = pomocniProizvodList.get(pomocniProizvodList.size() - 1);
        assertThat(testPomocniProizvod.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testPomocniProizvod.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testPomocniProizvod.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testPomocniProizvod.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testPomocniProizvod.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testPomocniProizvod.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createPomocniProizvodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pomocniProizvodRepository.findAll().size();

        // Create the PomocniProizvod with an existing ID
        pomocniProizvod.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPomocniProizvodMockMvc.perform(post("/api/pomocni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the PomocniProizvod in the database
        List<PomocniProizvod> pomocniProizvodList = pomocniProizvodRepository.findAll();
        assertThat(pomocniProizvodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPomocniProizvods() throws Exception {
        // Initialize the database
        pomocniProizvodRepository.saveAndFlush(pomocniProizvod);

        // Get all the pomocniProizvodList
        restPomocniProizvodMockMvc.perform(get("/api/pomocni-proizvods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pomocniProizvod.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getPomocniProizvod() throws Exception {
        // Initialize the database
        pomocniProizvodRepository.saveAndFlush(pomocniProizvod);

        // Get the pomocniProizvod
        restPomocniProizvodMockMvc.perform(get("/api/pomocni-proizvods/{id}", pomocniProizvod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pomocniProizvod.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPomocniProizvod() throws Exception {
        // Get the pomocniProizvod
        restPomocniProizvodMockMvc.perform(get("/api/pomocni-proizvods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePomocniProizvod() throws Exception {
        // Initialize the database
        pomocniProizvodService.save(pomocniProizvod);

        int databaseSizeBeforeUpdate = pomocniProizvodRepository.findAll().size();

        // Update the pomocniProizvod
        PomocniProizvod updatedPomocniProizvod = pomocniProizvodRepository.findById(pomocniProizvod.getId()).get();
        // Disconnect from session so that the updates on updatedPomocniProizvod are not directly saved in db
        em.detach(updatedPomocniProizvod);
        updatedPomocniProizvod
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .napomena(UPDATED_NAPOMENA);

        restPomocniProizvodMockMvc.perform(put("/api/pomocni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPomocniProizvod)))
            .andExpect(status().isOk());

        // Validate the PomocniProizvod in the database
        List<PomocniProizvod> pomocniProizvodList = pomocniProizvodRepository.findAll();
        assertThat(pomocniProizvodList).hasSize(databaseSizeBeforeUpdate);
        PomocniProizvod testPomocniProizvod = pomocniProizvodList.get(pomocniProizvodList.size() - 1);
        assertThat(testPomocniProizvod.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testPomocniProizvod.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testPomocniProizvod.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testPomocniProizvod.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testPomocniProizvod.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testPomocniProizvod.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingPomocniProizvod() throws Exception {
        int databaseSizeBeforeUpdate = pomocniProizvodRepository.findAll().size();

        // Create the PomocniProizvod

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPomocniProizvodMockMvc.perform(put("/api/pomocni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pomocniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the PomocniProizvod in the database
        List<PomocniProizvod> pomocniProizvodList = pomocniProizvodRepository.findAll();
        assertThat(pomocniProizvodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePomocniProizvod() throws Exception {
        // Initialize the database
        pomocniProizvodService.save(pomocniProizvod);

        int databaseSizeBeforeDelete = pomocniProizvodRepository.findAll().size();

        // Get the pomocniProizvod
        restPomocniProizvodMockMvc.perform(delete("/api/pomocni-proizvods/{id}", pomocniProizvod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PomocniProizvod> pomocniProizvodList = pomocniProizvodRepository.findAll();
        assertThat(pomocniProizvodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PomocniProizvod.class);
        PomocniProizvod pomocniProizvod1 = new PomocniProizvod();
        pomocniProizvod1.setId(1L);
        PomocniProizvod pomocniProizvod2 = new PomocniProizvod();
        pomocniProizvod2.setId(pomocniProizvod1.getId());
        assertThat(pomocniProizvod1).isEqualTo(pomocniProizvod2);
        pomocniProizvod2.setId(2L);
        assertThat(pomocniProizvod1).isNotEqualTo(pomocniProizvod2);
        pomocniProizvod1.setId(null);
        assertThat(pomocniProizvod1).isNotEqualTo(pomocniProizvod2);
    }
}
