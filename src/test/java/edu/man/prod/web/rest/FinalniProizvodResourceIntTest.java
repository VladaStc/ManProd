package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.FinalniProizvod;
import edu.man.prod.repository.FinalniProizvodRepository;
import edu.man.prod.service.FinalniProizvodService;
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
 * Test class for the FinalniProizvodResource REST controller.
 *
 * @see FinalniProizvodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class FinalniProizvodResourceIntTest {

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
    private FinalniProizvodRepository finalniProizvodRepository;
    
    @Autowired
    private FinalniProizvodService finalniProizvodService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFinalniProizvodMockMvc;

    private FinalniProizvod finalniProizvod;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FinalniProizvodResource finalniProizvodResource = new FinalniProizvodResource(finalniProizvodService);
        this.restFinalniProizvodMockMvc = MockMvcBuilders.standaloneSetup(finalniProizvodResource)
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
    public static FinalniProizvod createEntity(EntityManager em) {
        FinalniProizvod finalniProizvod = new FinalniProizvod()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return finalniProizvod;
    }

    @Before
    public void initTest() {
        finalniProizvod = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinalniProizvod() throws Exception {
        int databaseSizeBeforeCreate = finalniProizvodRepository.findAll().size();

        // Create the FinalniProizvod
        restFinalniProizvodMockMvc.perform(post("/api/finalni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalniProizvod)))
            .andExpect(status().isCreated());

        // Validate the FinalniProizvod in the database
        List<FinalniProizvod> finalniProizvodList = finalniProizvodRepository.findAll();
        assertThat(finalniProizvodList).hasSize(databaseSizeBeforeCreate + 1);
        FinalniProizvod testFinalniProizvod = finalniProizvodList.get(finalniProizvodList.size() - 1);
        assertThat(testFinalniProizvod.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testFinalniProizvod.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testFinalniProizvod.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testFinalniProizvod.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testFinalniProizvod.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testFinalniProizvod.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createFinalniProizvodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = finalniProizvodRepository.findAll().size();

        // Create the FinalniProizvod with an existing ID
        finalniProizvod.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinalniProizvodMockMvc.perform(post("/api/finalni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the FinalniProizvod in the database
        List<FinalniProizvod> finalniProizvodList = finalniProizvodRepository.findAll();
        assertThat(finalniProizvodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFinalniProizvods() throws Exception {
        // Initialize the database
        finalniProizvodRepository.saveAndFlush(finalniProizvod);

        // Get all the finalniProizvodList
        restFinalniProizvodMockMvc.perform(get("/api/finalni-proizvods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finalniProizvod.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getFinalniProizvod() throws Exception {
        // Initialize the database
        finalniProizvodRepository.saveAndFlush(finalniProizvod);

        // Get the finalniProizvod
        restFinalniProizvodMockMvc.perform(get("/api/finalni-proizvods/{id}", finalniProizvod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(finalniProizvod.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFinalniProizvod() throws Exception {
        // Get the finalniProizvod
        restFinalniProizvodMockMvc.perform(get("/api/finalni-proizvods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinalniProizvod() throws Exception {
        // Initialize the database
        finalniProizvodService.save(finalniProizvod);

        int databaseSizeBeforeUpdate = finalniProizvodRepository.findAll().size();

        // Update the finalniProizvod
        FinalniProizvod updatedFinalniProizvod = finalniProizvodRepository.findById(finalniProizvod.getId()).get();
        // Disconnect from session so that the updates on updatedFinalniProizvod are not directly saved in db
        em.detach(updatedFinalniProizvod);
        updatedFinalniProizvod
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .napomena(UPDATED_NAPOMENA);

        restFinalniProizvodMockMvc.perform(put("/api/finalni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFinalniProizvod)))
            .andExpect(status().isOk());

        // Validate the FinalniProizvod in the database
        List<FinalniProizvod> finalniProizvodList = finalniProizvodRepository.findAll();
        assertThat(finalniProizvodList).hasSize(databaseSizeBeforeUpdate);
        FinalniProizvod testFinalniProizvod = finalniProizvodList.get(finalniProizvodList.size() - 1);
        assertThat(testFinalniProizvod.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testFinalniProizvod.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testFinalniProizvod.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testFinalniProizvod.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testFinalniProizvod.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testFinalniProizvod.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingFinalniProizvod() throws Exception {
        int databaseSizeBeforeUpdate = finalniProizvodRepository.findAll().size();

        // Create the FinalniProizvod

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinalniProizvodMockMvc.perform(put("/api/finalni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finalniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the FinalniProizvod in the database
        List<FinalniProizvod> finalniProizvodList = finalniProizvodRepository.findAll();
        assertThat(finalniProizvodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinalniProizvod() throws Exception {
        // Initialize the database
        finalniProizvodService.save(finalniProizvod);

        int databaseSizeBeforeDelete = finalniProizvodRepository.findAll().size();

        // Get the finalniProizvod
        restFinalniProizvodMockMvc.perform(delete("/api/finalni-proizvods/{id}", finalniProizvod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FinalniProizvod> finalniProizvodList = finalniProizvodRepository.findAll();
        assertThat(finalniProizvodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinalniProizvod.class);
        FinalniProizvod finalniProizvod1 = new FinalniProizvod();
        finalniProizvod1.setId(1L);
        FinalniProizvod finalniProizvod2 = new FinalniProizvod();
        finalniProizvod2.setId(finalniProizvod1.getId());
        assertThat(finalniProizvod1).isEqualTo(finalniProizvod2);
        finalniProizvod2.setId(2L);
        assertThat(finalniProizvod1).isNotEqualTo(finalniProizvod2);
        finalniProizvod1.setId(null);
        assertThat(finalniProizvod1).isNotEqualTo(finalniProizvod2);
    }
}
