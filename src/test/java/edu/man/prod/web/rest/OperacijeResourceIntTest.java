package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Operacije;
import edu.man.prod.repository.OperacijeRepository;
import edu.man.prod.service.OperacijeService;
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
 * Test class for the OperacijeResource REST controller.
 *
 * @see OperacijeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class OperacijeResourceIntTest {

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
    private OperacijeRepository operacijeRepository;
    
    @Autowired
    private OperacijeService operacijeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOperacijeMockMvc;

    private Operacije operacije;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OperacijeResource operacijeResource = new OperacijeResource(operacijeService);
        this.restOperacijeMockMvc = MockMvcBuilders.standaloneSetup(operacijeResource)
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
    public static Operacije createEntity(EntityManager em) {
        Operacije operacije = new Operacije()
            .naziv(DEFAULT_NAZIV)
            .vremeRada(DEFAULT_VREME_RADA)
            .pomocnoVreme(DEFAULT_POMOCNO_VREME)
            .cena(DEFAULT_CENA)
            .skica(DEFAULT_SKICA);
        return operacije;
    }

    @Before
    public void initTest() {
        operacije = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperacije() throws Exception {
        int databaseSizeBeforeCreate = operacijeRepository.findAll().size();

        // Create the Operacije
        restOperacijeMockMvc.perform(post("/api/operacijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacije)))
            .andExpect(status().isCreated());

        // Validate the Operacije in the database
        List<Operacije> operacijeList = operacijeRepository.findAll();
        assertThat(operacijeList).hasSize(databaseSizeBeforeCreate + 1);
        Operacije testOperacije = operacijeList.get(operacijeList.size() - 1);
        assertThat(testOperacije.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testOperacije.getVremeRada()).isEqualTo(DEFAULT_VREME_RADA);
        assertThat(testOperacije.getPomocnoVreme()).isEqualTo(DEFAULT_POMOCNO_VREME);
        assertThat(testOperacije.getCena()).isEqualTo(DEFAULT_CENA);
        assertThat(testOperacije.getSkica()).isEqualTo(DEFAULT_SKICA);
    }

    @Test
    @Transactional
    public void createOperacijeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operacijeRepository.findAll().size();

        // Create the Operacije with an existing ID
        operacije.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperacijeMockMvc.perform(post("/api/operacijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacije)))
            .andExpect(status().isBadRequest());

        // Validate the Operacije in the database
        List<Operacije> operacijeList = operacijeRepository.findAll();
        assertThat(operacijeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOperacijes() throws Exception {
        // Initialize the database
        operacijeRepository.saveAndFlush(operacije);

        // Get all the operacijeList
        restOperacijeMockMvc.perform(get("/api/operacijes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operacije.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].vremeRada").value(hasItem(DEFAULT_VREME_RADA.doubleValue())))
            .andExpect(jsonPath("$.[*].pomocnoVreme").value(hasItem(DEFAULT_POMOCNO_VREME.doubleValue())))
            .andExpect(jsonPath("$.[*].cena").value(hasItem(DEFAULT_CENA.doubleValue())))
            .andExpect(jsonPath("$.[*].skica").value(hasItem(DEFAULT_SKICA.toString())));
    }
    
    @Test
    @Transactional
    public void getOperacije() throws Exception {
        // Initialize the database
        operacijeRepository.saveAndFlush(operacije);

        // Get the operacije
        restOperacijeMockMvc.perform(get("/api/operacijes/{id}", operacije.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(operacije.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.vremeRada").value(DEFAULT_VREME_RADA.doubleValue()))
            .andExpect(jsonPath("$.pomocnoVreme").value(DEFAULT_POMOCNO_VREME.doubleValue()))
            .andExpect(jsonPath("$.cena").value(DEFAULT_CENA.doubleValue()))
            .andExpect(jsonPath("$.skica").value(DEFAULT_SKICA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOperacije() throws Exception {
        // Get the operacije
        restOperacijeMockMvc.perform(get("/api/operacijes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperacije() throws Exception {
        // Initialize the database
        operacijeService.save(operacije);

        int databaseSizeBeforeUpdate = operacijeRepository.findAll().size();

        // Update the operacije
        Operacije updatedOperacije = operacijeRepository.findById(operacije.getId()).get();
        // Disconnect from session so that the updates on updatedOperacije are not directly saved in db
        em.detach(updatedOperacije);
        updatedOperacije
            .naziv(UPDATED_NAZIV)
            .vremeRada(UPDATED_VREME_RADA)
            .pomocnoVreme(UPDATED_POMOCNO_VREME)
            .cena(UPDATED_CENA)
            .skica(UPDATED_SKICA);

        restOperacijeMockMvc.perform(put("/api/operacijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOperacije)))
            .andExpect(status().isOk());

        // Validate the Operacije in the database
        List<Operacije> operacijeList = operacijeRepository.findAll();
        assertThat(operacijeList).hasSize(databaseSizeBeforeUpdate);
        Operacije testOperacije = operacijeList.get(operacijeList.size() - 1);
        assertThat(testOperacije.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testOperacije.getVremeRada()).isEqualTo(UPDATED_VREME_RADA);
        assertThat(testOperacije.getPomocnoVreme()).isEqualTo(UPDATED_POMOCNO_VREME);
        assertThat(testOperacije.getCena()).isEqualTo(UPDATED_CENA);
        assertThat(testOperacije.getSkica()).isEqualTo(UPDATED_SKICA);
    }

    @Test
    @Transactional
    public void updateNonExistingOperacije() throws Exception {
        int databaseSizeBeforeUpdate = operacijeRepository.findAll().size();

        // Create the Operacije

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperacijeMockMvc.perform(put("/api/operacijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operacije)))
            .andExpect(status().isBadRequest());

        // Validate the Operacije in the database
        List<Operacije> operacijeList = operacijeRepository.findAll();
        assertThat(operacijeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperacije() throws Exception {
        // Initialize the database
        operacijeService.save(operacije);

        int databaseSizeBeforeDelete = operacijeRepository.findAll().size();

        // Get the operacije
        restOperacijeMockMvc.perform(delete("/api/operacijes/{id}", operacije.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Operacije> operacijeList = operacijeRepository.findAll();
        assertThat(operacijeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Operacije.class);
        Operacije operacije1 = new Operacije();
        operacije1.setId(1L);
        Operacije operacije2 = new Operacije();
        operacije2.setId(operacije1.getId());
        assertThat(operacije1).isEqualTo(operacije2);
        operacije2.setId(2L);
        assertThat(operacije1).isNotEqualTo(operacije2);
        operacije1.setId(null);
        assertThat(operacije1).isNotEqualTo(operacije2);
    }
}
