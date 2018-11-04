package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.ProizvodniPogoni;
import edu.man.prod.repository.ProizvodniPogoniRepository;
import edu.man.prod.service.ProizvodniPogoniService;
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
 * Test class for the ProizvodniPogoniResource REST controller.
 *
 * @see ProizvodniPogoniResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class ProizvodniPogoniResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final Double DEFAULT_POVRSINA = 1D;
    private static final Double UPDATED_POVRSINA = 2D;

    private static final String DEFAULT_RUKOVODILAC = "AAAAAAAAAA";
    private static final String UPDATED_RUKOVODILAC = "BBBBBBBBBB";

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private ProizvodniPogoniRepository proizvodniPogoniRepository;
    
    @Autowired
    private ProizvodniPogoniService proizvodniPogoniService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProizvodniPogoniMockMvc;

    private ProizvodniPogoni proizvodniPogoni;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProizvodniPogoniResource proizvodniPogoniResource = new ProizvodniPogoniResource(proizvodniPogoniService);
        this.restProizvodniPogoniMockMvc = MockMvcBuilders.standaloneSetup(proizvodniPogoniResource)
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
    public static ProizvodniPogoni createEntity(EntityManager em) {
        ProizvodniPogoni proizvodniPogoni = new ProizvodniPogoni()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .povrsina(DEFAULT_POVRSINA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return proizvodniPogoni;
    }

    @Before
    public void initTest() {
        proizvodniPogoni = createEntity(em);
    }

    @Test
    @Transactional
    public void createProizvodniPogoni() throws Exception {
        int databaseSizeBeforeCreate = proizvodniPogoniRepository.findAll().size();

        // Create the ProizvodniPogoni
        restProizvodniPogoniMockMvc.perform(post("/api/proizvodni-pogonis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodniPogoni)))
            .andExpect(status().isCreated());

        // Validate the ProizvodniPogoni in the database
        List<ProizvodniPogoni> proizvodniPogoniList = proizvodniPogoniRepository.findAll();
        assertThat(proizvodniPogoniList).hasSize(databaseSizeBeforeCreate + 1);
        ProizvodniPogoni testProizvodniPogoni = proizvodniPogoniList.get(proizvodniPogoniList.size() - 1);
        assertThat(testProizvodniPogoni.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testProizvodniPogoni.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testProizvodniPogoni.getPovrsina()).isEqualTo(DEFAULT_POVRSINA);
        assertThat(testProizvodniPogoni.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testProizvodniPogoni.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createProizvodniPogoniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proizvodniPogoniRepository.findAll().size();

        // Create the ProizvodniPogoni with an existing ID
        proizvodniPogoni.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProizvodniPogoniMockMvc.perform(post("/api/proizvodni-pogonis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodniPogoni)))
            .andExpect(status().isBadRequest());

        // Validate the ProizvodniPogoni in the database
        List<ProizvodniPogoni> proizvodniPogoniList = proizvodniPogoniRepository.findAll();
        assertThat(proizvodniPogoniList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProizvodniPogonis() throws Exception {
        // Initialize the database
        proizvodniPogoniRepository.saveAndFlush(proizvodniPogoni);

        // Get all the proizvodniPogoniList
        restProizvodniPogoniMockMvc.perform(get("/api/proizvodni-pogonis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proizvodniPogoni.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].povrsina").value(hasItem(DEFAULT_POVRSINA.doubleValue())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getProizvodniPogoni() throws Exception {
        // Initialize the database
        proizvodniPogoniRepository.saveAndFlush(proizvodniPogoni);

        // Get the proizvodniPogoni
        restProizvodniPogoniMockMvc.perform(get("/api/proizvodni-pogonis/{id}", proizvodniPogoni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proizvodniPogoni.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.povrsina").value(DEFAULT_POVRSINA.doubleValue()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProizvodniPogoni() throws Exception {
        // Get the proizvodniPogoni
        restProizvodniPogoniMockMvc.perform(get("/api/proizvodni-pogonis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProizvodniPogoni() throws Exception {
        // Initialize the database
        proizvodniPogoniService.save(proizvodniPogoni);

        int databaseSizeBeforeUpdate = proizvodniPogoniRepository.findAll().size();

        // Update the proizvodniPogoni
        ProizvodniPogoni updatedProizvodniPogoni = proizvodniPogoniRepository.findById(proizvodniPogoni.getId()).get();
        // Disconnect from session so that the updates on updatedProizvodniPogoni are not directly saved in db
        em.detach(updatedProizvodniPogoni);
        updatedProizvodniPogoni
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .povrsina(UPDATED_POVRSINA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restProizvodniPogoniMockMvc.perform(put("/api/proizvodni-pogonis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProizvodniPogoni)))
            .andExpect(status().isOk());

        // Validate the ProizvodniPogoni in the database
        List<ProizvodniPogoni> proizvodniPogoniList = proizvodniPogoniRepository.findAll();
        assertThat(proizvodniPogoniList).hasSize(databaseSizeBeforeUpdate);
        ProizvodniPogoni testProizvodniPogoni = proizvodniPogoniList.get(proizvodniPogoniList.size() - 1);
        assertThat(testProizvodniPogoni.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testProizvodniPogoni.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testProizvodniPogoni.getPovrsina()).isEqualTo(UPDATED_POVRSINA);
        assertThat(testProizvodniPogoni.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testProizvodniPogoni.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingProizvodniPogoni() throws Exception {
        int databaseSizeBeforeUpdate = proizvodniPogoniRepository.findAll().size();

        // Create the ProizvodniPogoni

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProizvodniPogoniMockMvc.perform(put("/api/proizvodni-pogonis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodniPogoni)))
            .andExpect(status().isBadRequest());

        // Validate the ProizvodniPogoni in the database
        List<ProizvodniPogoni> proizvodniPogoniList = proizvodniPogoniRepository.findAll();
        assertThat(proizvodniPogoniList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProizvodniPogoni() throws Exception {
        // Initialize the database
        proizvodniPogoniService.save(proizvodniPogoni);

        int databaseSizeBeforeDelete = proizvodniPogoniRepository.findAll().size();

        // Get the proizvodniPogoni
        restProizvodniPogoniMockMvc.perform(delete("/api/proizvodni-pogonis/{id}", proizvodniPogoni.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProizvodniPogoni> proizvodniPogoniList = proizvodniPogoniRepository.findAll();
        assertThat(proizvodniPogoniList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProizvodniPogoni.class);
        ProizvodniPogoni proizvodniPogoni1 = new ProizvodniPogoni();
        proizvodniPogoni1.setId(1L);
        ProizvodniPogoni proizvodniPogoni2 = new ProizvodniPogoni();
        proizvodniPogoni2.setId(proizvodniPogoni1.getId());
        assertThat(proizvodniPogoni1).isEqualTo(proizvodniPogoni2);
        proizvodniPogoni2.setId(2L);
        assertThat(proizvodniPogoni1).isNotEqualTo(proizvodniPogoni2);
        proizvodniPogoni1.setId(null);
        assertThat(proizvodniPogoni1).isNotEqualTo(proizvodniPogoni2);
    }
}
