package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Zahvati;
import edu.man.prod.repository.ZahvatiRepository;
import edu.man.prod.service.ZahvatiService;
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

/**
 * Test class for the ZahvatiResource REST controller.
 *
 * @see ZahvatiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class ZahvatiResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final Double DEFAULT_VREME_RADA = 1D;
    private static final Double UPDATED_VREME_RADA = 2D;

    private static final Double DEFAULT_POMOCNO_VREME = 1D;
    private static final Double UPDATED_POMOCNO_VREME = 2D;

    private static final Double DEFAULT_CENA = 1D;
    private static final Double UPDATED_CENA = 2D;

    private static final String DEFAULT_SKICA = "AAAAAAAAAA";
    private static final String UPDATED_SKICA = "BBBBBBBBBB";

    @Autowired
    private ZahvatiRepository zahvatiRepository;
    
    @Autowired
    private ZahvatiService zahvatiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restZahvatiMockMvc;

    private Zahvati zahvati;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ZahvatiResource zahvatiResource = new ZahvatiResource(zahvatiService);
        this.restZahvatiMockMvc = MockMvcBuilders.standaloneSetup(zahvatiResource)
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
    public static Zahvati createEntity(EntityManager em) {
        Zahvati zahvati = new Zahvati()
            .naziv(DEFAULT_NAZIV)
            .vremeRada(DEFAULT_VREME_RADA)
            .pomocnoVreme(DEFAULT_POMOCNO_VREME)
            .cena(DEFAULT_CENA)
            .skica(DEFAULT_SKICA);
        return zahvati;
    }

    @Before
    public void initTest() {
        zahvati = createEntity(em);
    }

    @Test
    @Transactional
    public void createZahvati() throws Exception {
        int databaseSizeBeforeCreate = zahvatiRepository.findAll().size();

        // Create the Zahvati
        restZahvatiMockMvc.perform(post("/api/zahvatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zahvati)))
            .andExpect(status().isCreated());

        // Validate the Zahvati in the database
        List<Zahvati> zahvatiList = zahvatiRepository.findAll();
        assertThat(zahvatiList).hasSize(databaseSizeBeforeCreate + 1);
        Zahvati testZahvati = zahvatiList.get(zahvatiList.size() - 1);
        assertThat(testZahvati.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testZahvati.getVremeRada()).isEqualTo(DEFAULT_VREME_RADA);
        assertThat(testZahvati.getPomocnoVreme()).isEqualTo(DEFAULT_POMOCNO_VREME);
        assertThat(testZahvati.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testZahvati.getSkica()).isEqualTo(DEFAULT_SKICA);
    }

    @Test
    @Transactional
    public void createZahvatiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zahvatiRepository.findAll().size();

        // Create the Zahvati with an existing ID
        zahvati.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZahvatiMockMvc.perform(post("/api/zahvatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zahvati)))
            .andExpect(status().isBadRequest());

        // Validate the Zahvati in the database
        List<Zahvati> zahvatiList = zahvatiRepository.findAll();
        assertThat(zahvatiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllZahvatis() throws Exception {
        // Initialize the database
        zahvatiRepository.saveAndFlush(zahvati);

        // Get all the zahvatiList
        restZahvatiMockMvc.perform(get("/api/zahvatis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zahvati.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vremeRada").value(hasItem(DEFAULT_VREME_RADA.doubleValue())))
            .andExpect(jsonPath("$.[*].pomocnoVreme").value(hasItem(DEFAULT_POMOCNO_VREME.doubleValue())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].skica").value(hasItem(DEFAULT_SKICA.toString())));
    }
    
    @Test
    @Transactional
    public void getZahvati() throws Exception {
        // Initialize the database
        zahvatiRepository.saveAndFlush(zahvati);

        // Get the zahvati
        restZahvatiMockMvc.perform(get("/api/zahvatis/{id}", zahvati.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(zahvati.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vremeRada").value(DEFAULT_VREME_RADA.doubleValue()))
            .andExpect(jsonPath("$.pomocnoVreme").value(DEFAULT_POMOCNO_VREME.doubleValue()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.skica").value(DEFAULT_SKICA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingZahvati() throws Exception {
        // Get the zahvati
        restZahvatiMockMvc.perform(get("/api/zahvatis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZahvati() throws Exception {
        // Initialize the database
        zahvatiService.save(zahvati);

        int databaseSizeBeforeUpdate = zahvatiRepository.findAll().size();

        // Update the zahvati
        Zahvati updatedZahvati = zahvatiRepository.findById(zahvati.getId()).get();
        // Disconnect from session so that the updates on updatedZahvati are not directly saved in db
        em.detach(updatedZahvati);
        updatedZahvati
            .naziv(UPDATED_NAZIV)
            .vremeRada(UPDATED_VREME_RADA)
            .pomocnoVreme(UPDATED_POMOCNO_VREME)
            .cena(UPDATED_CENA)
            .skica(UPDATED_SKICA);

        restZahvatiMockMvc.perform(put("/api/zahvatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedZahvati)))
            .andExpect(status().isOk());

        // Validate the Zahvati in the database
        List<Zahvati> zahvatiList = zahvatiRepository.findAll();
        assertThat(zahvatiList).hasSize(databaseSizeBeforeUpdate);
        Zahvati testZahvati = zahvatiList.get(zahvatiList.size() - 1);
        assertThat(testZahvati.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testZahvati.getVremeRada()).isEqualTo(UPDATED_VREME_RADA);
        assertThat(testZahvati.getPomocnoVreme()).isEqualTo(UPDATED_POMOCNO_VREME);
        assertThat(testZahvati.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testZahvati.getSkica()).isEqualTo(UPDATED_SKICA);
    }

    @Test
    @Transactional
    public void updateNonExistingZahvati() throws Exception {
        int databaseSizeBeforeUpdate = zahvatiRepository.findAll().size();

        // Create the Zahvati

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZahvatiMockMvc.perform(put("/api/zahvatis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zahvati)))
            .andExpect(status().isBadRequest());

        // Validate the Zahvati in the database
        List<Zahvati> zahvatiList = zahvatiRepository.findAll();
        assertThat(zahvatiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZahvati() throws Exception {
        // Initialize the database
        zahvatiService.save(zahvati);

        int databaseSizeBeforeDelete = zahvatiRepository.findAll().size();

        // Get the zahvati
        restZahvatiMockMvc.perform(delete("/api/zahvatis/{id}", zahvati.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Zahvati> zahvatiList = zahvatiRepository.findAll();
        assertThat(zahvatiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Zahvati.class);
        Zahvati zahvati1 = new Zahvati();
        zahvati1.setId(1L);
        Zahvati zahvati2 = new Zahvati();
        zahvati2.setId(zahvati1.getId());
        assertThat(zahvati1).isEqualTo(zahvati2);
        zahvati2.setId(2L);
        assertThat(zahvati1).isNotEqualTo(zahvati2);
        zahvati1.setId(null);
        assertThat(zahvati1).isNotEqualTo(zahvati2);
    }
}
