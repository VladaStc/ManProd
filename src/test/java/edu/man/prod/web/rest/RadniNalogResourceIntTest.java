package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.RadniNalog;
import edu.man.prod.repository.RadniNalogRepository;
import edu.man.prod.service.RadniNalogService;
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

import edu.man.prod.domain.enumeration.Status;
/**
 * Test class for the RadniNalogResource REST controller.
 *
 * @see RadniNalogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class RadniNalogResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATUM_I_VREME_OTVARANJA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATUM_I_VREME_OTVARANJA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATUM_I_VREME_ZATVARANJA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATUM_I_VREME_ZATVARANJA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.OTVOREN;
    private static final Status UPDATED_STATUS = Status.AKTIVAN;

    private static final String DEFAULT_NOSILAC = "AAAAAAAAAA";
    private static final String UPDATED_NOSILAC = "BBBBBBBBBB";

    private static final Double DEFAULT_CENA = 1D;
    private static final Double UPDATED_CENA = 2D;

    private static final Double DEFAULT_KOLICINA = 1D;
    private static final Double UPDATED_KOLICINA = 2D;

    @Autowired
    private RadniNalogRepository radniNalogRepository;
    
    @Autowired
    private RadniNalogService radniNalogService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRadniNalogMockMvc;

    private RadniNalog radniNalog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RadniNalogResource radniNalogResource = new RadniNalogResource(radniNalogService);
        this.restRadniNalogMockMvc = MockMvcBuilders.standaloneSetup(radniNalogResource)
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
    public static RadniNalog createEntity(EntityManager em) {
        RadniNalog radniNalog = new RadniNalog()
            .naziv(DEFAULT_NAZIV)
            .datumIVremeOtvaranja(DEFAULT_DATUM_I_VREME_OTVARANJA)
            .datumIVremeZatvaranja(DEFAULT_DATUM_I_VREME_ZATVARANJA)
            .status(DEFAULT_STATUS)
            .nosilac(DEFAULT_NOSILAC)
            .cena(DEFAULT_CENA)
            .kolicina(DEFAULT_KOLICINA);
        return radniNalog;
    }

    @Before
    public void initTest() {
        radniNalog = createEntity(em);
    }

    @Test
    @Transactional
    public void createRadniNalog() throws Exception {
        int databaseSizeBeforeCreate = radniNalogRepository.findAll().size();

        // Create the RadniNalog
        restRadniNalogMockMvc.perform(post("/api/radni-nalogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radniNalog)))
            .andExpect(status().isCreated());

        // Validate the RadniNalog in the database
        List<RadniNalog> radniNalogList = radniNalogRepository.findAll();
        assertThat(radniNalogList).hasSize(databaseSizeBeforeCreate + 1);
        RadniNalog testRadniNalog = radniNalogList.get(radniNalogList.size() - 1);
        assertThat(testRadniNalog.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testRadniNalog.getDatumIVremeOtvaranja()).isEqualTo(DEFAULT_DATUM_I_VREME_OTVARANJA);
        assertThat(testRadniNalog.getDatumIVremeZatvaranja()).isEqualTo(DEFAULT_DATUM_I_VREME_ZATVARANJA);
        assertThat(testRadniNalog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRadniNalog.getNosilac()).isEqualTo(DEFAULT_NOSILAC);
        assertThat(testRadniNalog.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testRadniNalog.getKolicina()).isEqualTo(DEFAULT_KOLICINA);
    }

    @Test
    @Transactional
    public void createRadniNalogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = radniNalogRepository.findAll().size();

        // Create the RadniNalog with an existing ID
        radniNalog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRadniNalogMockMvc.perform(post("/api/radni-nalogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radniNalog)))
            .andExpect(status().isBadRequest());

        // Validate the RadniNalog in the database
        List<RadniNalog> radniNalogList = radniNalogRepository.findAll();
        assertThat(radniNalogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRadniNalogs() throws Exception {
        // Initialize the database
        radniNalogRepository.saveAndFlush(radniNalog);

        // Get all the radniNalogList
        restRadniNalogMockMvc.perform(get("/api/radni-nalogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(radniNalog.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].datumIVremeOtvaranja").value(hasItem(DEFAULT_DATUM_I_VREME_OTVARANJA.toString())))
            .andExpect(jsonPath("$.[*].datumIVremeZatvaranja").value(hasItem(DEFAULT_DATUM_I_VREME_ZATVARANJA.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].nosilac").value(hasItem(DEFAULT_NOSILAC.toString())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].kolicina").value(hasItem(DEFAULT_KOLICINA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getRadniNalog() throws Exception {
        // Initialize the database
        radniNalogRepository.saveAndFlush(radniNalog);

        // Get the radniNalog
        restRadniNalogMockMvc.perform(get("/api/radni-nalogs/{id}", radniNalog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(radniNalog.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.datumIVremeOtvaranja").value(DEFAULT_DATUM_I_VREME_OTVARANJA.toString()))
            .andExpect(jsonPath("$.datumIVremeZatvaranja").value(DEFAULT_DATUM_I_VREME_ZATVARANJA.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.nosilac").value(DEFAULT_NOSILAC.toString()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.kolicina").value(DEFAULT_KOLICINA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRadniNalog() throws Exception {
        // Get the radniNalog
        restRadniNalogMockMvc.perform(get("/api/radni-nalogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRadniNalog() throws Exception {
        // Initialize the database
        radniNalogService.save(radniNalog);

        int databaseSizeBeforeUpdate = radniNalogRepository.findAll().size();

        // Update the radniNalog
        RadniNalog updatedRadniNalog = radniNalogRepository.findById(radniNalog.getId()).get();
        // Disconnect from session so that the updates on updatedRadniNalog are not directly saved in db
        em.detach(updatedRadniNalog);
        updatedRadniNalog
            .naziv(UPDATED_NAZIV)
            .datumIVremeOtvaranja(UPDATED_DATUM_I_VREME_OTVARANJA)
            .datumIVremeZatvaranja(UPDATED_DATUM_I_VREME_ZATVARANJA)
            .status(UPDATED_STATUS)
            .nosilac(UPDATED_NOSILAC)
            .cena(UPDATED_CENA)
            .kolicina(UPDATED_KOLICINA);

        restRadniNalogMockMvc.perform(put("/api/radni-nalogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRadniNalog)))
            .andExpect(status().isOk());

        // Validate the RadniNalog in the database
        List<RadniNalog> radniNalogList = radniNalogRepository.findAll();
        assertThat(radniNalogList).hasSize(databaseSizeBeforeUpdate);
        RadniNalog testRadniNalog = radniNalogList.get(radniNalogList.size() - 1);
        assertThat(testRadniNalog.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testRadniNalog.getDatumIVremeOtvaranja()).isEqualTo(UPDATED_DATUM_I_VREME_OTVARANJA);
        assertThat(testRadniNalog.getDatumIVremeZatvaranja()).isEqualTo(UPDATED_DATUM_I_VREME_ZATVARANJA);
        assertThat(testRadniNalog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRadniNalog.getNosilac()).isEqualTo(UPDATED_NOSILAC);
        assertThat(testRadniNalog.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testRadniNalog.getKolicina()).isEqualTo(UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    public void updateNonExistingRadniNalog() throws Exception {
        int databaseSizeBeforeUpdate = radniNalogRepository.findAll().size();

        // Create the RadniNalog

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadniNalogMockMvc.perform(put("/api/radni-nalogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radniNalog)))
            .andExpect(status().isBadRequest());

        // Validate the RadniNalog in the database
        List<RadniNalog> radniNalogList = radniNalogRepository.findAll();
        assertThat(radniNalogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRadniNalog() throws Exception {
        // Initialize the database
        radniNalogService.save(radniNalog);

        int databaseSizeBeforeDelete = radniNalogRepository.findAll().size();

        // Get the radniNalog
        restRadniNalogMockMvc.perform(delete("/api/radni-nalogs/{id}", radniNalog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RadniNalog> radniNalogList = radniNalogRepository.findAll();
        assertThat(radniNalogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RadniNalog.class);
        RadniNalog radniNalog1 = new RadniNalog();
        radniNalog1.setId(1L);
        RadniNalog radniNalog2 = new RadniNalog();
        radniNalog2.setId(radniNalog1.getId());
        assertThat(radniNalog1).isEqualTo(radniNalog2);
        radniNalog2.setId(2L);
        assertThat(radniNalog1).isNotEqualTo(radniNalog2);
        radniNalog1.setId(null);
        assertThat(radniNalog1).isNotEqualTo(radniNalog2);
    }
}
