package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Radionica;
import edu.man.prod.repository.RadionicaRepository;
import edu.man.prod.service.RadionicaService;
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
 * Test class for the RadionicaResource REST controller.
 *
 * @see RadionicaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class RadionicaResourceIntTest {

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
    private RadionicaRepository radionicaRepository;
    
    @Autowired
    private RadionicaService radionicaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRadionicaMockMvc;

    private Radionica radionica;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RadionicaResource radionicaResource = new RadionicaResource(radionicaService);
        this.restRadionicaMockMvc = MockMvcBuilders.standaloneSetup(radionicaResource)
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
    public static Radionica createEntity(EntityManager em) {
        Radionica radionica = new Radionica()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .povrsina(DEFAULT_POVRSINA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return radionica;
    }

    @Before
    public void initTest() {
        radionica = createEntity(em);
    }

    @Test
    @Transactional
    public void createRadionica() throws Exception {
        int databaseSizeBeforeCreate = radionicaRepository.findAll().size();

        // Create the Radionica
        restRadionicaMockMvc.perform(post("/api/radionicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radionica)))
            .andExpect(status().isCreated());

        // Validate the Radionica in the database
        List<Radionica> radionicaList = radionicaRepository.findAll();
        assertThat(radionicaList).hasSize(databaseSizeBeforeCreate + 1);
        Radionica testRadionica = radionicaList.get(radionicaList.size() - 1);
        assertThat(testRadionica.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testRadionica.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testRadionica.getPovrsina()).isEqualTo(DEFAULT_POVRSINA);
        assertThat(testRadionica.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testRadionica.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createRadionicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = radionicaRepository.findAll().size();

        // Create the Radionica with an existing ID
        radionica.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRadionicaMockMvc.perform(post("/api/radionicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radionica)))
            .andExpect(status().isBadRequest());

        // Validate the Radionica in the database
        List<Radionica> radionicaList = radionicaRepository.findAll();
        assertThat(radionicaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRadionicas() throws Exception {
        // Initialize the database
        radionicaRepository.saveAndFlush(radionica);

        // Get all the radionicaList
        restRadionicaMockMvc.perform(get("/api/radionicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(radionica.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].povrsina").value(hasItem(DEFAULT_POVRSINA.doubleValue())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getRadionica() throws Exception {
        // Initialize the database
        radionicaRepository.saveAndFlush(radionica);

        // Get the radionica
        restRadionicaMockMvc.perform(get("/api/radionicas/{id}", radionica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(radionica.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.povrsina").value(DEFAULT_POVRSINA.doubleValue()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRadionica() throws Exception {
        // Get the radionica
        restRadionicaMockMvc.perform(get("/api/radionicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRadionica() throws Exception {
        // Initialize the database
        radionicaService.save(radionica);

        int databaseSizeBeforeUpdate = radionicaRepository.findAll().size();

        // Update the radionica
        Radionica updatedRadionica = radionicaRepository.findById(radionica.getId()).get();
        // Disconnect from session so that the updates on updatedRadionica are not directly saved in db
        em.detach(updatedRadionica);
        updatedRadionica
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .povrsina(UPDATED_POVRSINA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restRadionicaMockMvc.perform(put("/api/radionicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRadionica)))
            .andExpect(status().isOk());

        // Validate the Radionica in the database
        List<Radionica> radionicaList = radionicaRepository.findAll();
        assertThat(radionicaList).hasSize(databaseSizeBeforeUpdate);
        Radionica testRadionica = radionicaList.get(radionicaList.size() - 1);
        assertThat(testRadionica.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testRadionica.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testRadionica.getPovrsina()).isEqualTo(UPDATED_POVRSINA);
        assertThat(testRadionica.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testRadionica.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingRadionica() throws Exception {
        int databaseSizeBeforeUpdate = radionicaRepository.findAll().size();

        // Create the Radionica

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadionicaMockMvc.perform(put("/api/radionicas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radionica)))
            .andExpect(status().isBadRequest());

        // Validate the Radionica in the database
        List<Radionica> radionicaList = radionicaRepository.findAll();
        assertThat(radionicaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRadionica() throws Exception {
        // Initialize the database
        radionicaService.save(radionica);

        int databaseSizeBeforeDelete = radionicaRepository.findAll().size();

        // Get the radionica
        restRadionicaMockMvc.perform(delete("/api/radionicas/{id}", radionica.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Radionica> radionicaList = radionicaRepository.findAll();
        assertThat(radionicaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Radionica.class);
        Radionica radionica1 = new Radionica();
        radionica1.setId(1L);
        Radionica radionica2 = new Radionica();
        radionica2.setId(radionica1.getId());
        assertThat(radionica1).isEqualTo(radionica2);
        radionica2.setId(2L);
        assertThat(radionica1).isNotEqualTo(radionica2);
        radionica1.setId(null);
        assertThat(radionica1).isNotEqualTo(radionica2);
    }
}
