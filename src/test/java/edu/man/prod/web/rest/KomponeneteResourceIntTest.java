package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Komponenete;
import edu.man.prod.repository.KomponeneteRepository;
import edu.man.prod.service.KomponeneteService;
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
 * Test class for the KomponeneteResource REST controller.
 *
 * @see KomponeneteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class KomponeneteResourceIntTest {

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
    private KomponeneteRepository komponeneteRepository;
    
    @Autowired
    private KomponeneteService komponeneteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKomponeneteMockMvc;

    private Komponenete komponenete;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KomponeneteResource komponeneteResource = new KomponeneteResource(komponeneteService);
        this.restKomponeneteMockMvc = MockMvcBuilders.standaloneSetup(komponeneteResource)
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
    public static Komponenete createEntity(EntityManager em) {
        Komponenete komponenete = new Komponenete()
            .sifra(DEFAULT_SIFRA)
            .naziv(DEFAULT_NAZIV)
            .vrsta(DEFAULT_VRSTA)
            .jedMere(DEFAULT_JED_MERE)
            .namena(DEFAULT_NAMENA)
            .nabavnaCena(DEFAULT_NABAVNA_CENA)
            .napomena(DEFAULT_NAPOMENA);
        return komponenete;
    }

    @Before
    public void initTest() {
        komponenete = createEntity(em);
    }

    @Test
    @Transactional
    public void createKomponenete() throws Exception {
        int databaseSizeBeforeCreate = komponeneteRepository.findAll().size();

        // Create the Komponenete
        restKomponeneteMockMvc.perform(post("/api/komponenetes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(komponenete)))
            .andExpect(status().isCreated());

        // Validate the Komponenete in the database
        List<Komponenete> komponeneteList = komponeneteRepository.findAll();
        assertThat(komponeneteList).hasSize(databaseSizeBeforeCreate + 1);
        Komponenete testKomponenete = komponeneteList.get(komponeneteList.size() - 1);
        assertThat(testKomponenete.getSifra()).isEqualTo(DEFAULT_SIFRA);
        assertThat(testKomponenete.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testKomponenete.getVrsta()).isEqualTo(DEFAULT_VRSTA);
        assertThat(testKomponenete.getJedMere()).isEqualTo(DEFAULT_JED_MERE);
        assertThat(testKomponenete.getNamena()).isEqualTo(DEFAULT_NAMENA);
        assertThat(testKomponenete.getNabavnaCena()).isEqualTo(DEFAULT_NABAVNA_CENA);
        assertThat(testKomponenete.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createKomponeneteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = komponeneteRepository.findAll().size();

        // Create the Komponenete with an existing ID
        komponenete.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKomponeneteMockMvc.perform(post("/api/komponenetes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(komponenete)))
            .andExpect(status().isBadRequest());

        // Validate the Komponenete in the database
        List<Komponenete> komponeneteList = komponeneteRepository.findAll();
        assertThat(komponeneteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKomponenetes() throws Exception {
        // Initialize the database
        komponeneteRepository.saveAndFlush(komponenete);

        // Get all the komponeneteList
        restKomponeneteMockMvc.perform(get("/api/komponenetes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(komponenete.getId().intValue())))
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
    public void getKomponenete() throws Exception {
        // Initialize the database
        komponeneteRepository.saveAndFlush(komponenete);

        // Get the komponenete
        restKomponeneteMockMvc.perform(get("/api/komponenetes/{id}", komponenete.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(komponenete.getId().intValue()))
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
    public void getNonExistingKomponenete() throws Exception {
        // Get the komponenete
        restKomponeneteMockMvc.perform(get("/api/komponenetes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKomponenete() throws Exception {
        // Initialize the database
        komponeneteService.save(komponenete);

        int databaseSizeBeforeUpdate = komponeneteRepository.findAll().size();

        // Update the komponenete
        Komponenete updatedKomponenete = komponeneteRepository.findById(komponenete.getId()).get();
        // Disconnect from session so that the updates on updatedKomponenete are not directly saved in db
        em.detach(updatedKomponenete);
        updatedKomponenete
            .sifra(UPDATED_SIFRA)
            .naziv(UPDATED_NAZIV)
            .vrsta(UPDATED_VRSTA)
            .jedMere(UPDATED_JED_MERE)
            .namena(UPDATED_NAMENA)
            .nabavnaCena(UPDATED_NABAVNA_CENA)
            .napomena(UPDATED_NAPOMENA);

        restKomponeneteMockMvc.perform(put("/api/komponenetes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKomponenete)))
            .andExpect(status().isOk());

        // Validate the Komponenete in the database
        List<Komponenete> komponeneteList = komponeneteRepository.findAll();
        assertThat(komponeneteList).hasSize(databaseSizeBeforeUpdate);
        Komponenete testKomponenete = komponeneteList.get(komponeneteList.size() - 1);
        assertThat(testKomponenete.getSifra()).isEqualTo(UPDATED_SIFRA);
        assertThat(testKomponenete.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testKomponenete.getVrsta()).isEqualTo(UPDATED_VRSTA);
        assertThat(testKomponenete.getJedMere()).isEqualTo(UPDATED_JED_MERE);
        assertThat(testKomponenete.getNamena()).isEqualTo(UPDATED_NAMENA);
        assertThat(testKomponenete.getNabavnaCena()).isEqualTo(UPDATED_NABAVNA_CENA);
        assertThat(testKomponenete.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingKomponenete() throws Exception {
        int databaseSizeBeforeUpdate = komponeneteRepository.findAll().size();

        // Create the Komponenete

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKomponeneteMockMvc.perform(put("/api/komponenetes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(komponenete)))
            .andExpect(status().isBadRequest());

        // Validate the Komponenete in the database
        List<Komponenete> komponeneteList = komponeneteRepository.findAll();
        assertThat(komponeneteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKomponenete() throws Exception {
        // Initialize the database
        komponeneteService.save(komponenete);

        int databaseSizeBeforeDelete = komponeneteRepository.findAll().size();

        // Get the komponenete
        restKomponeneteMockMvc.perform(delete("/api/komponenetes/{id}", komponenete.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Komponenete> komponeneteList = komponeneteRepository.findAll();
        assertThat(komponeneteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Komponenete.class);
        Komponenete komponenete1 = new Komponenete();
        komponenete1.setId(1L);
        Komponenete komponenete2 = new Komponenete();
        komponenete2.setId(komponenete1.getId());
        assertThat(komponenete1).isEqualTo(komponenete2);
        komponenete2.setId(2L);
        assertThat(komponenete1).isNotEqualTo(komponenete2);
        komponenete1.setId(null);
        assertThat(komponenete1).isNotEqualTo(komponenete2);
    }
}
