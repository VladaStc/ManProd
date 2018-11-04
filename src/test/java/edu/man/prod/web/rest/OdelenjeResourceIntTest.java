package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.Odelenje;
import edu.man.prod.repository.OdelenjeRepository;
import edu.man.prod.service.OdelenjeService;
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
 * Test class for the OdelenjeResource REST controller.
 *
 * @see OdelenjeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class OdelenjeResourceIntTest {

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
    private OdelenjeRepository odelenjeRepository;
    
    @Autowired
    private OdelenjeService odelenjeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOdelenjeMockMvc;

    private Odelenje odelenje;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OdelenjeResource odelenjeResource = new OdelenjeResource(odelenjeService);
        this.restOdelenjeMockMvc = MockMvcBuilders.standaloneSetup(odelenjeResource)
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
    public static Odelenje createEntity(EntityManager em) {
        Odelenje odelenje = new Odelenje()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .povrsina(DEFAULT_POVRSINA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return odelenje;
    }

    @Before
    public void initTest() {
        odelenje = createEntity(em);
    }

    @Test
    @Transactional
    public void createOdelenje() throws Exception {
        int databaseSizeBeforeCreate = odelenjeRepository.findAll().size();

        // Create the Odelenje
        restOdelenjeMockMvc.perform(post("/api/odelenjes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(odelenje)))
            .andExpect(status().isCreated());

        // Validate the Odelenje in the database
        List<Odelenje> odelenjeList = odelenjeRepository.findAll();
        assertThat(odelenjeList).hasSize(databaseSizeBeforeCreate + 1);
        Odelenje testOdelenje = odelenjeList.get(odelenjeList.size() - 1);
        assertThat(testOdelenje.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testOdelenje.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testOdelenje.getPovrsina()).isEqualTo(DEFAULT_POVRSINA);
        assertThat(testOdelenje.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testOdelenje.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createOdelenjeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = odelenjeRepository.findAll().size();

        // Create the Odelenje with an existing ID
        odelenje.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOdelenjeMockMvc.perform(post("/api/odelenjes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(odelenje)))
            .andExpect(status().isBadRequest());

        // Validate the Odelenje in the database
        List<Odelenje> odelenjeList = odelenjeRepository.findAll();
        assertThat(odelenjeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOdelenjes() throws Exception {
        // Initialize the database
        odelenjeRepository.saveAndFlush(odelenje);

        // Get all the odelenjeList
        restOdelenjeMockMvc.perform(get("/api/odelenjes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(odelenje.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].povrsina").value(hasItem(DEFAULT_POVRSINA.doubleValue())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getOdelenje() throws Exception {
        // Initialize the database
        odelenjeRepository.saveAndFlush(odelenje);

        // Get the odelenje
        restOdelenjeMockMvc.perform(get("/api/odelenjes/{id}", odelenje.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(odelenje.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.povrsina").value(DEFAULT_POVRSINA.doubleValue()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOdelenje() throws Exception {
        // Get the odelenje
        restOdelenjeMockMvc.perform(get("/api/odelenjes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOdelenje() throws Exception {
        // Initialize the database
        odelenjeService.save(odelenje);

        int databaseSizeBeforeUpdate = odelenjeRepository.findAll().size();

        // Update the odelenje
        Odelenje updatedOdelenje = odelenjeRepository.findById(odelenje.getId()).get();
        // Disconnect from session so that the updates on updatedOdelenje are not directly saved in db
        em.detach(updatedOdelenje);
        updatedOdelenje
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .povrsina(UPDATED_POVRSINA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restOdelenjeMockMvc.perform(put("/api/odelenjes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOdelenje)))
            .andExpect(status().isOk());

        // Validate the Odelenje in the database
        List<Odelenje> odelenjeList = odelenjeRepository.findAll();
        assertThat(odelenjeList).hasSize(databaseSizeBeforeUpdate);
        Odelenje testOdelenje = odelenjeList.get(odelenjeList.size() - 1);
        assertThat(testOdelenje.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testOdelenje.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testOdelenje.getPovrsina()).isEqualTo(UPDATED_POVRSINA);
        assertThat(testOdelenje.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testOdelenje.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingOdelenje() throws Exception {
        int databaseSizeBeforeUpdate = odelenjeRepository.findAll().size();

        // Create the Odelenje

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOdelenjeMockMvc.perform(put("/api/odelenjes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(odelenje)))
            .andExpect(status().isBadRequest());

        // Validate the Odelenje in the database
        List<Odelenje> odelenjeList = odelenjeRepository.findAll();
        assertThat(odelenjeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOdelenje() throws Exception {
        // Initialize the database
        odelenjeService.save(odelenje);

        int databaseSizeBeforeDelete = odelenjeRepository.findAll().size();

        // Get the odelenje
        restOdelenjeMockMvc.perform(delete("/api/odelenjes/{id}", odelenje.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Odelenje> odelenjeList = odelenjeRepository.findAll();
        assertThat(odelenjeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Odelenje.class);
        Odelenje odelenje1 = new Odelenje();
        odelenje1.setId(1L);
        Odelenje odelenje2 = new Odelenje();
        odelenje2.setId(odelenje1.getId());
        assertThat(odelenje1).isEqualTo(odelenje2);
        odelenje2.setId(2L);
        assertThat(odelenje1).isNotEqualTo(odelenje2);
        odelenje1.setId(null);
        assertThat(odelenje1).isNotEqualTo(odelenje2);
    }
}
