package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.KupovniProizvod;
import edu.man.prod.repository.KupovniProizvodRepository;
import edu.man.prod.service.KupovniProizvodService;
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
 * Test class for the KupovniProizvodResource REST controller.
 *
 * @see KupovniProizvodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class KupovniProizvodResourceIntTest {

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
    private KupovniProizvodRepository kupovniProizvodRepository;
    
    @Autowired
    private KupovniProizvodService kupovniProizvodService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKupovniProizvodMockMvc;

    private KupovniProizvod kupovniProizvod;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KupovniProizvodResource kupovniProizvodResource = new KupovniProizvodResource(kupovniProizvodService);
        this.restKupovniProizvodMockMvc = MockMvcBuilders.standaloneSetup(kupovniProizvodResource)
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
    public static KupovniProizvod createEntity(EntityManager em) {
        KupovniProizvod kupovniProizvod = new KupovniProizvod()
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .cena(DEFAULT_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return kupovniProizvod;
    }

    @Before
    public void initTest() {
        kupovniProizvod = createEntity(em);
    }

    @Test
    @Transactional
    public void createKupovniProizvod() throws Exception {
        int databaseSizeBeforeCreate = kupovniProizvodRepository.findAll().size();

        // Create the KupovniProizvod
        restKupovniProizvodMockMvc.perform(post("/api/kupovni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniProizvod)))
            .andExpect(status().isCreated());

        // Validate the KupovniProizvod in the database
        List<KupovniProizvod> kupovniProizvodList = kupovniProizvodRepository.findAll();
        assertThat(kupovniProizvodList).hasSize(databaseSizeBeforeCreate + 1);
        KupovniProizvod testKupovniProizvod = kupovniProizvodList.get(kupovniProizvodList.size() - 1);
        assertThat(testKupovniProizvod.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testKupovniProizvod.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testKupovniProizvod.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testKupovniProizvod.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testKupovniProizvod.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testKupovniProizvod.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createKupovniProizvodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kupovniProizvodRepository.findAll().size();

        // Create the KupovniProizvod with an existing ID
        kupovniProizvod.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKupovniProizvodMockMvc.perform(post("/api/kupovni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the KupovniProizvod in the database
        List<KupovniProizvod> kupovniProizvodList = kupovniProizvodRepository.findAll();
        assertThat(kupovniProizvodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKupovniProizvods() throws Exception {
        // Initialize the database
        kupovniProizvodRepository.saveAndFlush(kupovniProizvod);

        // Get all the kupovniProizvodList
        restKupovniProizvodMockMvc.perform(get("/api/kupovni-proizvods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kupovniProizvod.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vrsta").value(hasItem(DEFAULT_VRSTA.toString())))
            .andExpect(jsonPath("$.[*].jedMere").value(hasItem(DEFAULT_JED_MERE.toString())))
            .andExpect(jsonPath("$.[*].namena").value(hasItem(DEFAULT_NAMENA.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getKupovniProizvod() throws Exception {
        // Initialize the database
        kupovniProizvodRepository.saveAndFlush(kupovniProizvod);

        // Get the kupovniProizvod
        restKupovniProizvodMockMvc.perform(get("/api/kupovni-proizvods/{id}", kupovniProizvod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kupovniProizvod.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vrsta").value(DEFAULT_VRSTA.toString()))
            .andExpect(jsonPath("$.jedMere").value(DEFAULT_JED_MERE.toString()))
            .andExpect(jsonPath("$.namena").value(DEFAULT_NAMENA.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKupovniProizvod() throws Exception {
        // Get the kupovniProizvod
        restKupovniProizvodMockMvc.perform(get("/api/kupovni-proizvods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKupovniProizvod() throws Exception {
        // Initialize the database
        kupovniProizvodService.save(kupovniProizvod);

        int databaseSizeBeforeUpdate = kupovniProizvodRepository.findAll().size();

        // Update the kupovniProizvod
        KupovniProizvod updatedKupovniProizvod = kupovniProizvodRepository.findById(kupovniProizvod.getId()).get();
        // Disconnect from session so that the updates on updatedKupovniProizvod are not directly saved in db
        em.detach(updatedKupovniProizvod);
        updatedKupovniProizvod
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .cena(UPDATED_CENA)
            .napomena(UPDATED_NAPOMENA);

        restKupovniProizvodMockMvc.perform(put("/api/kupovni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKupovniProizvod)))
            .andExpect(status().isOk());

        // Validate the KupovniProizvod in the database
        List<KupovniProizvod> kupovniProizvodList = kupovniProizvodRepository.findAll();
        assertThat(kupovniProizvodList).hasSize(databaseSizeBeforeUpdate);
        KupovniProizvod testKupovniProizvod = kupovniProizvodList.get(kupovniProizvodList.size() - 1);
        assertThat(testKupovniProizvod.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testKupovniProizvod.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testKupovniProizvod.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testKupovniProizvod.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testKupovniProizvod.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testKupovniProizvod.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingKupovniProizvod() throws Exception {
        int databaseSizeBeforeUpdate = kupovniProizvodRepository.findAll().size();

        // Create the KupovniProizvod

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKupovniProizvodMockMvc.perform(put("/api/kupovni-proizvods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kupovniProizvod)))
            .andExpect(status().isBadRequest());

        // Validate the KupovniProizvod in the database
        List<KupovniProizvod> kupovniProizvodList = kupovniProizvodRepository.findAll();
        assertThat(kupovniProizvodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKupovniProizvod() throws Exception {
        // Initialize the database
        kupovniProizvodService.save(kupovniProizvod);

        int databaseSizeBeforeDelete = kupovniProizvodRepository.findAll().size();

        // Get the kupovniProizvod
        restKupovniProizvodMockMvc.perform(delete("/api/kupovni-proizvods/{id}", kupovniProizvod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KupovniProizvod> kupovniProizvodList = kupovniProizvodRepository.findAll();
        assertThat(kupovniProizvodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KupovniProizvod.class);
        KupovniProizvod kupovniProizvod1 = new KupovniProizvod();
        kupovniProizvod1.setId(1L);
        KupovniProizvod kupovniProizvod2 = new KupovniProizvod();
        kupovniProizvod2.setId(kupovniProizvod1.getId());
        assertThat(kupovniProizvod1).isEqualTo(kupovniProizvod2);
        kupovniProizvod2.setId(2L);
        assertThat(kupovniProizvod1).isNotEqualTo(kupovniProizvod2);
        kupovniProizvod1.setId(null);
        assertThat(kupovniProizvod1).isNotEqualTo(kupovniProizvod2);
    }
}
