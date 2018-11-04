package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Dobavljac;
import edu.man.prod.repository.DobavljacRepository;
import edu.man.prod.service.DobavljacService;
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
 * Test class for the DobavljacResource REST controller.
 *
 * @see DobavljacResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class DobavljacResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final Double DEFAULT_KOLICINA = 1D;
    private static final Double UPDATED_KOLICINA = 2D;

    @Autowired
    private DobavljacRepository dobavljacRepository;
    
    @Autowired
    private DobavljacService dobavljacService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDobavljacMockMvc;

    private Dobavljac dobavljac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DobavljacResource dobavljacResource = new DobavljacResource(dobavljacService);
        this.restDobavljacMockMvc = MockMvcBuilders.standaloneSetup(dobavljacResource)
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
    public static Dobavljac createEntity(EntityManager em) {
        Dobavljac dobavljac = new Dobavljac()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .kolicina(DEFAULT_KOLICINA);
        return dobavljac;
    }

    @Before
    public void initTest() {
        dobavljac = createEntity(em);
    }

    @Test
    @Transactional
    public void createDobavljac() throws Exception {
        int databaseSizeBeforeCreate = dobavljacRepository.findAll().size();

        // Create the Dobavljac
        restDobavljacMockMvc.perform(post("/api/dobavljacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dobavljac)))
            .andExpect(status().isCreated());

        // Validate the Dobavljac in the database
        List<Dobavljac> dobavljacList = dobavljacRepository.findAll();
        assertThat(dobavljacList).hasSize(databaseSizeBeforeCreate + 1);
        Dobavljac testDobavljac = dobavljacList.get(dobavljacList.size() - 1);
        assertThat(testDobavljac.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testDobavljac.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testDobavljac.getKolicina()).isEqualTo(DEFAULT_KOLICINA);
    }

    @Test
    @Transactional
    public void createDobavljacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dobavljacRepository.findAll().size();

        // Create the Dobavljac with an existing ID
        dobavljac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDobavljacMockMvc.perform(post("/api/dobavljacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dobavljac)))
            .andExpect(status().isBadRequest());

        // Validate the Dobavljac in the database
        List<Dobavljac> dobavljacList = dobavljacRepository.findAll();
        assertThat(dobavljacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDobavljacs() throws Exception {
        // Initialize the database
        dobavljacRepository.saveAndFlush(dobavljac);

        // Get all the dobavljacList
        restDobavljacMockMvc.perform(get("/api/dobavljacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dobavljac.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].kolicina").value(hasItem(DEFAULT_KOLICINA.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDobavljac() throws Exception {
        // Initialize the database
        dobavljacRepository.saveAndFlush(dobavljac);

        // Get the dobavljac
        restDobavljacMockMvc.perform(get("/api/dobavljacs/{id}", dobavljac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dobavljac.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.kolicina").value(DEFAULT_KOLICINA.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDobavljac() throws Exception {
        // Get the dobavljac
        restDobavljacMockMvc.perform(get("/api/dobavljacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDobavljac() throws Exception {
        // Initialize the database
        dobavljacService.save(dobavljac);

        int databaseSizeBeforeUpdate = dobavljacRepository.findAll().size();

        // Update the dobavljac
        Dobavljac updatedDobavljac = dobavljacRepository.findById(dobavljac.getId()).get();
        // Disconnect from session so that the updates on updatedDobavljac are not directly saved in db
        em.detach(updatedDobavljac);
        updatedDobavljac
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .kolicina(UPDATED_KOLICINA);

        restDobavljacMockMvc.perform(put("/api/dobavljacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDobavljac)))
            .andExpect(status().isOk());

        // Validate the Dobavljac in the database
        List<Dobavljac> dobavljacList = dobavljacRepository.findAll();
        assertThat(dobavljacList).hasSize(databaseSizeBeforeUpdate);
        Dobavljac testDobavljac = dobavljacList.get(dobavljacList.size() - 1);
        assertThat(testDobavljac.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testDobavljac.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testDobavljac.getKolicina()).isEqualTo(UPDATED_KOLICINA);
    }

    @Test
    @Transactional
    public void updateNonExistingDobavljac() throws Exception {
        int databaseSizeBeforeUpdate = dobavljacRepository.findAll().size();

        // Create the Dobavljac

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDobavljacMockMvc.perform(put("/api/dobavljacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dobavljac)))
            .andExpect(status().isBadRequest());

        // Validate the Dobavljac in the database
        List<Dobavljac> dobavljacList = dobavljacRepository.findAll();
        assertThat(dobavljacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDobavljac() throws Exception {
        // Initialize the database
        dobavljacService.save(dobavljac);

        int databaseSizeBeforeDelete = dobavljacRepository.findAll().size();

        // Get the dobavljac
        restDobavljacMockMvc.perform(delete("/api/dobavljacs/{id}", dobavljac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dobavljac> dobavljacList = dobavljacRepository.findAll();
        assertThat(dobavljacList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dobavljac.class);
        Dobavljac dobavljac1 = new Dobavljac();
        dobavljac1.setId(1L);
        Dobavljac dobavljac2 = new Dobavljac();
        dobavljac2.setId(dobavljac1.getId());
        assertThat(dobavljac1).isEqualTo(dobavljac2);
        dobavljac2.setId(2L);
        assertThat(dobavljac1).isNotEqualTo(dobavljac2);
        dobavljac1.setId(null);
        assertThat(dobavljac1).isNotEqualTo(dobavljac2);
    }
}
