package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.ProizvodneLinije;
import edu.man.prod.repository.ProizvodneLinijeRepository;
import edu.man.prod.service.ProizvodneLinijeService;
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
 * Test class for the ProizvodneLinijeResource REST controller.
 *
 * @see ProizvodneLinijeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class ProizvodneLinijeResourceIntTest {

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
    private ProizvodneLinijeRepository proizvodneLinijeRepository;
    
    @Autowired
    private ProizvodneLinijeService proizvodneLinijeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProizvodneLinijeMockMvc;

    private ProizvodneLinije proizvodneLinije;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProizvodneLinijeResource proizvodneLinijeResource = new ProizvodneLinijeResource(proizvodneLinijeService);
        this.restProizvodneLinijeMockMvc = MockMvcBuilders.standaloneSetup(proizvodneLinijeResource)
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
    public static ProizvodneLinije createEntity(EntityManager em) {
        ProizvodneLinije proizvodneLinije = new ProizvodneLinije()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .povrsina(DEFAULT_POVRSINA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return proizvodneLinije;
    }

    @Before
    public void initTest() {
        proizvodneLinije = createEntity(em);
    }

    @Test
    @Transactional
    public void createProizvodneLinije() throws Exception {
        int databaseSizeBeforeCreate = proizvodneLinijeRepository.findAll().size();

        // Create the ProizvodneLinije
        restProizvodneLinijeMockMvc.perform(post("/api/proizvodne-linijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodneLinije)))
            .andExpect(status().isCreated());

        // Validate the ProizvodneLinije in the database
        List<ProizvodneLinije> proizvodneLinijeList = proizvodneLinijeRepository.findAll();
        assertThat(proizvodneLinijeList).hasSize(databaseSizeBeforeCreate + 1);
        ProizvodneLinije testProizvodneLinije = proizvodneLinijeList.get(proizvodneLinijeList.size() - 1);
        assertThat(testProizvodneLinije.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testProizvodneLinije.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testProizvodneLinije.getPovrsina()).isEqualTo(DEFAULT_POVRSINA);
        assertThat(testProizvodneLinije.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testProizvodneLinije.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createProizvodneLinijeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proizvodneLinijeRepository.findAll().size();

        // Create the ProizvodneLinije with an existing ID
        proizvodneLinije.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProizvodneLinijeMockMvc.perform(post("/api/proizvodne-linijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodneLinije)))
            .andExpect(status().isBadRequest());

        // Validate the ProizvodneLinije in the database
        List<ProizvodneLinije> proizvodneLinijeList = proizvodneLinijeRepository.findAll();
        assertThat(proizvodneLinijeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProizvodneLinijes() throws Exception {
        // Initialize the database
        proizvodneLinijeRepository.saveAndFlush(proizvodneLinije);

        // Get all the proizvodneLinijeList
        restProizvodneLinijeMockMvc.perform(get("/api/proizvodne-linijes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proizvodneLinije.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].povrsina").value(hasItem(DEFAULT_POVRSINA.doubleValue())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getProizvodneLinije() throws Exception {
        // Initialize the database
        proizvodneLinijeRepository.saveAndFlush(proizvodneLinije);

        // Get the proizvodneLinije
        restProizvodneLinijeMockMvc.perform(get("/api/proizvodne-linijes/{id}", proizvodneLinije.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proizvodneLinije.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.povrsina").value(DEFAULT_POVRSINA.doubleValue()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProizvodneLinije() throws Exception {
        // Get the proizvodneLinije
        restProizvodneLinijeMockMvc.perform(get("/api/proizvodne-linijes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProizvodneLinije() throws Exception {
        // Initialize the database
        proizvodneLinijeService.save(proizvodneLinije);

        int databaseSizeBeforeUpdate = proizvodneLinijeRepository.findAll().size();

        // Update the proizvodneLinije
        ProizvodneLinije updatedProizvodneLinije = proizvodneLinijeRepository.findById(proizvodneLinije.getId()).get();
        // Disconnect from session so that the updates on updatedProizvodneLinije are not directly saved in db
        em.detach(updatedProizvodneLinije);
        updatedProizvodneLinije
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .povrsina(UPDATED_POVRSINA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restProizvodneLinijeMockMvc.perform(put("/api/proizvodne-linijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProizvodneLinije)))
            .andExpect(status().isOk());

        // Validate the ProizvodneLinije in the database
        List<ProizvodneLinije> proizvodneLinijeList = proizvodneLinijeRepository.findAll();
        assertThat(proizvodneLinijeList).hasSize(databaseSizeBeforeUpdate);
        ProizvodneLinije testProizvodneLinije = proizvodneLinijeList.get(proizvodneLinijeList.size() - 1);
        assertThat(testProizvodneLinije.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testProizvodneLinije.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testProizvodneLinije.getPovrsina()).isEqualTo(UPDATED_POVRSINA);
        assertThat(testProizvodneLinije.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testProizvodneLinije.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingProizvodneLinije() throws Exception {
        int databaseSizeBeforeUpdate = proizvodneLinijeRepository.findAll().size();

        // Create the ProizvodneLinije

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProizvodneLinijeMockMvc.perform(put("/api/proizvodne-linijes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proizvodneLinije)))
            .andExpect(status().isBadRequest());

        // Validate the ProizvodneLinije in the database
        List<ProizvodneLinije> proizvodneLinijeList = proizvodneLinijeRepository.findAll();
        assertThat(proizvodneLinijeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProizvodneLinije() throws Exception {
        // Initialize the database
        proizvodneLinijeService.save(proizvodneLinije);

        int databaseSizeBeforeDelete = proizvodneLinijeRepository.findAll().size();

        // Get the proizvodneLinije
        restProizvodneLinijeMockMvc.perform(delete("/api/proizvodne-linijes/{id}", proizvodneLinije.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProizvodneLinije> proizvodneLinijeList = proizvodneLinijeRepository.findAll();
        assertThat(proizvodneLinijeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProizvodneLinije.class);
        ProizvodneLinije proizvodneLinije1 = new ProizvodneLinije();
        proizvodneLinije1.setId(1L);
        ProizvodneLinije proizvodneLinije2 = new ProizvodneLinije();
        proizvodneLinije2.setId(proizvodneLinije1.getId());
        assertThat(proizvodneLinije1).isEqualTo(proizvodneLinije2);
        proizvodneLinije2.setId(2L);
        assertThat(proizvodneLinije1).isNotEqualTo(proizvodneLinije2);
        proizvodneLinije1.setId(null);
        assertThat(proizvodneLinije1).isNotEqualTo(proizvodneLinije2);
    }
}
