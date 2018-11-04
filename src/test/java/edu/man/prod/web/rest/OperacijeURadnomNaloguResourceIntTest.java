package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.OperacijeURadnomNalogu;
import edu.man.prod.repository.OperacijeURadnomNaloguRepository;
import edu.man.prod.service.OperacijeURadnomNaloguService;
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

/**
 * Test class for the OperacijeURadnomNaloguResource REST controller.
 *
 * @see OperacijeURadnomNaloguResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class OperacijeURadnomNaloguResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final Double DEFAULT_VREME_RADA = 1D;
    private static final Double UPDATED_VREME_RADA = 2D;

    private static final Double DEFAULT_PRIPREMNO_ZAVRSNO_VREME = 1D;
    private static final Double UPDATED_PRIPREMNO_ZAVRSNO_VREME = 2D;

    private static final Double DEFAULT_CENA = 1D;
    private static final Double UPDATED_CENA = 2D;

    private static final String DEFAULT_SKICA = "AAAAAAAAAA";
    private static final String UPDATED_SKICA = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATUM_I_VREME_POCETKA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATUM_I_VREME_POCETKA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATUM_I_VREME_ZAVRSETKA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATUM_I_VREME_ZAVRSETKA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OperacijeURadnomNaloguRepository operacijeURadnomNaloguRepository;
    
    @Autowired
    private OperacijeURadnomNaloguService operacijeURadnomNaloguService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOperacijeURadnomNaloguMockMvc;

    private OperacijeURadnomNalogu operacijeURadnomNalogu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OperacijeURadnomNaloguResource operacijeURadnomNaloguResource = new OperacijeURadnomNaloguResource(operacijeURadnomNaloguService);
        this.restOperacijeURadnomNaloguMockMvc = MockMvcBuilders.standaloneSetup(operacijeURadnomNaloguResource)
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
    public static OperacijeURadnomNalogu createEntity(EntityManager em) {
        OperacijeURadnomNalogu operacijeURadnomNalogu = new OperacijeURadnomNalogu()
            .naziv(DEFAULT_NAZIV)
            .vremeRada(DEFAULT_VREME_RADA)
            .pripremnoZavrsnoVreme(DEFAULT_PRIPREMNO_ZAVRSNO_VREME)
            .cena(DEFAULT_CENA)
            .skica(DEFAULT_SKICA)
            .datumIVremePocetka(DEFAULT_DATUM_I_VREME_POCETKA)
            .datumIVremeZavrsetka(DEFAULT_DATUM_I_VREME_ZAVRSETKA);
        return operacijeURadnomNalogu;
    }

    @Before
    public void initTest() {
        operacijeURadnomNalogu = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperacijeURadnomNalogu() throws Exception {
        int databaseSizeBeforeCreate = operacijeURadnomNaloguRepository.findAll().size();

        // Create the OperacijeURadnomNalogu
        restOperacijeURadnomNaloguMockMvc.perform(post("/api/operacije-u-radnom-nalogus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacijeURadnomNalogu)))
            .andExpect(status().isCreated());

        // Validate the OperacijeURadnomNalogu in the database
        List<OperacijeURadnomNalogu> operacijeURadnomNaloguList = operacijeURadnomNaloguRepository.findAll();
        assertThat(operacijeURadnomNaloguList).hasSize(databaseSizeBeforeCreate + 1);
        OperacijeURadnomNalogu testOperacijeURadnomNalogu = operacijeURadnomNaloguList.get(operacijeURadnomNaloguList.size() - 1);
        assertThat(testOperacijeURadnomNalogu.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testOperacijeURadnomNalogu.getVremeRada()).isEqualTo(DEFAULT_VREME_RADA);
        assertThat(testOperacijeURadnomNalogu.getPripremnoZavrsnoVreme()).isEqualTo(DEFAULT_PRIPREMNO_ZAVRSNO_VREME);
        assertThat(testOperacijeURadnomNalogu.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testOperacijeURadnomNalogu.getSkica()).isEqualTo(DEFAULT_SKICA);
        assertThat(testOperacijeURadnomNalogu.getDatumIVremePocetka()).isEqualTo(DEFAULT_DATUM_I_VREME_POCETKA);
        assertThat(testOperacijeURadnomNalogu.getDatumIVremeZavrsetka()).isEqualTo(DEFAULT_DATUM_I_VREME_ZAVRSETKA);
    }

    @Test
    @Transactional
    public void createOperacijeURadnomNaloguWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operacijeURadnomNaloguRepository.findAll().size();

        // Create the OperacijeURadnomNalogu with an existing ID
        operacijeURadnomNalogu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperacijeURadnomNaloguMockMvc.perform(post("/api/operacije-u-radnom-nalogus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacijeURadnomNalogu)))
            .andExpect(status().isBadRequest());

        // Validate the OperacijeURadnomNalogu in the database
        List<OperacijeURadnomNalogu> operacijeURadnomNaloguList = operacijeURadnomNaloguRepository.findAll();
        assertThat(operacijeURadnomNaloguList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOperacijeURadnomNalogus() throws Exception {
        // Initialize the database
        operacijeURadnomNaloguRepository.saveAndFlush(operacijeURadnomNalogu);

        // Get all the operacijeURadnomNaloguList
        restOperacijeURadnomNaloguMockMvc.perform(get("/api/operacije-u-radnom-nalogus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operacijeURadnomNalogu.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vremeRada").value(hasItem(DEFAULT_VREME_RADA.doubleValue())))
            .andExpect(jsonPath("$.[*].pripremnoZavrsnoVreme").value(hasItem(DEFAULT_PRIPREMNO_ZAVRSNO_VREME.doubleValue())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].skica").value(hasItem(DEFAULT_SKICA.toString())))
            .andExpect(jsonPath("$.[*].datumIVremePocetka").value(hasItem(DEFAULT_DATUM_I_VREME_POCETKA.toString())))
            .andExpect(jsonPath("$.[*].datumIVremeZavrsetka").value(hasItem(DEFAULT_DATUM_I_VREME_ZAVRSETKA.toString())));
    }
    
    @Test
    @Transactional
    public void getOperacijeURadnomNalogu() throws Exception {
        // Initialize the database
        operacijeURadnomNaloguRepository.saveAndFlush(operacijeURadnomNalogu);

        // Get the operacijeURadnomNalogu
        restOperacijeURadnomNaloguMockMvc.perform(get("/api/operacije-u-radnom-nalogus/{id}", operacijeURadnomNalogu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(operacijeURadnomNalogu.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vremeRada").value(DEFAULT_VREME_RADA.doubleValue()))
            .andExpect(jsonPath("$.pripremnoZavrsnoVreme").value(DEFAULT_PRIPREMNO_ZAVRSNO_VREME.doubleValue()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.skica").value(DEFAULT_SKICA.toString()))
            .andExpect(jsonPath("$.datumIVremePocetka").value(DEFAULT_DATUM_I_VREME_POCETKA.toString()))
            .andExpect(jsonPath("$.datumIVremeZavrsetka").value(DEFAULT_DATUM_I_VREME_ZAVRSETKA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOperacijeURadnomNalogu() throws Exception {
        // Get the operacijeURadnomNalogu
        restOperacijeURadnomNaloguMockMvc.perform(get("/api/operacije-u-radnom-nalogus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperacijeURadnomNalogu() throws Exception {
        // Initialize the database
        operacijeURadnomNaloguService.save(operacijeURadnomNalogu);

        int databaseSizeBeforeUpdate = operacijeURadnomNaloguRepository.findAll().size();

        // Update the operacijeURadnomNalogu
        OperacijeURadnomNalogu updatedOperacijeURadnomNalogu = operacijeURadnomNaloguRepository.findById(operacijeURadnomNalogu.getId()).get();
        // Disconnect from session so that the updates on updatedOperacijeURadnomNalogu are not directly saved in db
        em.detach(updatedOperacijeURadnomNalogu);
        updatedOperacijeURadnomNalogu
            .naziv(UPDATED_NAZIV)
            .vremeRada(UPDATED_VREME_RADA)
            .pripremnoZavrsnoVreme(UPDATED_PRIPREMNO_ZAVRSNO_VREME)
            .cena(UPDATED_CENA)
            .skica(UPDATED_SKICA)
            .datumIVremePocetka(UPDATED_DATUM_I_VREME_POCETKA)
            .datumIVremeZavrsetka(UPDATED_DATUM_I_VREME_ZAVRSETKA);

        restOperacijeURadnomNaloguMockMvc.perform(put("/api/operacije-u-radnom-nalogus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOperacijeURadnomNalogu)))
            .andExpect(status().isOk());

        // Validate the OperacijeURadnomNalogu in the database
        List<OperacijeURadnomNalogu> operacijeURadnomNaloguList = operacijeURadnomNaloguRepository.findAll();
        assertThat(operacijeURadnomNaloguList).hasSize(databaseSizeBeforeUpdate);
        OperacijeURadnomNalogu testOperacijeURadnomNalogu = operacijeURadnomNaloguList.get(operacijeURadnomNaloguList.size() - 1);
        assertThat(testOperacijeURadnomNalogu.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testOperacijeURadnomNalogu.getVremeRada()).isEqualTo(UPDATED_VREME_RADA);
        assertThat(testOperacijeURadnomNalogu.getPripremnoZavrsnoVreme()).isEqualTo(UPDATED_PRIPREMNO_ZAVRSNO_VREME);
        assertThat(testOperacijeURadnomNalogu.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testOperacijeURadnomNalogu.getSkica()).isEqualTo(UPDATED_SKICA);
        assertThat(testOperacijeURadnomNalogu.getDatumIVremePocetka()).isEqualTo(UPDATED_DATUM_I_VREME_POCETKA);
        assertThat(testOperacijeURadnomNalogu.getDatumIVremeZavrsetka()).isEqualTo(UPDATED_DATUM_I_VREME_ZAVRSETKA);
    }

    @Test
    @Transactional
    public void updateNonExistingOperacijeURadnomNalogu() throws Exception {
        int databaseSizeBeforeUpdate = operacijeURadnomNaloguRepository.findAll().size();

        // Create the OperacijeURadnomNalogu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperacijeURadnomNaloguMockMvc.perform(put("/api/operacije-u-radnom-nalogus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacijeURadnomNalogu)))
            .andExpect(status().isBadRequest());

        // Validate the OperacijeURadnomNalogu in the database
        List<OperacijeURadnomNalogu> operacijeURadnomNaloguList = operacijeURadnomNaloguRepository.findAll();
        assertThat(operacijeURadnomNaloguList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperacijeURadnomNalogu() throws Exception {
        // Initialize the database
        operacijeURadnomNaloguService.save(operacijeURadnomNalogu);

        int databaseSizeBeforeDelete = operacijeURadnomNaloguRepository.findAll().size();

        // Get the operacijeURadnomNalogu
        restOperacijeURadnomNaloguMockMvc.perform(delete("/api/operacije-u-radnom-nalogus/{id}", operacijeURadnomNalogu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OperacijeURadnomNalogu> operacijeURadnomNaloguList = operacijeURadnomNaloguRepository.findAll();
        assertThat(operacijeURadnomNaloguList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperacijeURadnomNalogu.class);
        OperacijeURadnomNalogu operacijeURadnomNalogu1 = new OperacijeURadnomNalogu();
        operacijeURadnomNalogu1.setId(1L);
        OperacijeURadnomNalogu operacijeURadnomNalogu2 = new OperacijeURadnomNalogu();
        operacijeURadnomNalogu2.setId(operacijeURadnomNalogu1.getId());
        assertThat(operacijeURadnomNalogu1).isEqualTo(operacijeURadnomNalogu2);
        operacijeURadnomNalogu2.setId(2L);
        assertThat(operacijeURadnomNalogu1).isNotEqualTo(operacijeURadnomNalogu2);
        operacijeURadnomNalogu1.setId(null);
        assertThat(operacijeURadnomNalogu1).isNotEqualTo(operacijeURadnomNalogu2);
    }
}
