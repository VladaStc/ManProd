package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.RadnoMesto;
import edu.man.prod.repository.RadnoMestoRepository;
import edu.man.prod.service.RadnoMestoService;
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
 * Test class for the RadnoMestoResource REST controller.
 *
 * @see RadnoMestoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class RadnoMestoResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final Double DEFAULT_POVRSINA = 1D;
    private static final Double UPDATED_POVRSINA = 2D;

    private static final String DEFAULT_RUKOVODILAC = "AAAAAAAAAA";
    private static final String UPDATED_RUKOVODILAC = "BBBBBBBBBB";

    private static final Long DEFAULT_NORMA_SAT = 1L;
    private static final Long UPDATED_NORMA_SAT = 2L;

    @Autowired
    private RadnoMestoRepository radnoMestoRepository;
    
    @Autowired
    private RadnoMestoService radnoMestoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRadnoMestoMockMvc;

    private RadnoMesto radnoMesto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RadnoMestoResource radnoMestoResource = new RadnoMestoResource(radnoMestoService);
        this.restRadnoMestoMockMvc = MockMvcBuilders.standaloneSetup(radnoMestoResource)
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
    public static RadnoMesto createEntity(EntityManager em) {
        RadnoMesto radnoMesto = new RadnoMesto()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .povrsina(DEFAULT_POVRSINA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .normaSat(DEFAULT_NORMA_SAT);
        return radnoMesto;
    }

    @Before
    public void initTest() {
        radnoMesto = createEntity(em);
    }

    @Test
    @Transactional
    public void createRadnoMesto() throws Exception {
        int databaseSizeBeforeCreate = radnoMestoRepository.findAll().size();

        // Create the RadnoMesto
        restRadnoMestoMockMvc.perform(post("/api/radno-mestos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnoMesto)))
            .andExpect(status().isCreated());

        // Validate the RadnoMesto in the database
        List<RadnoMesto> radnoMestoList = radnoMestoRepository.findAll();
        assertThat(radnoMestoList).hasSize(databaseSizeBeforeCreate + 1);
        RadnoMesto testRadnoMesto = radnoMestoList.get(radnoMestoList.size() - 1);
        assertThat(testRadnoMesto.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testRadnoMesto.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testRadnoMesto.getPovrsina()).isEqualTo(DEFAULT_POVRSINA);
        assertThat(testRadnoMesto.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testRadnoMesto.getNormaSat()).isEqualTo(DEFAULT_NORMA_SAT);
    }

    @Test
    @Transactional
    public void createRadnoMestoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = radnoMestoRepository.findAll().size();

        // Create the RadnoMesto with an existing ID
        radnoMesto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRadnoMestoMockMvc.perform(post("/api/radno-mestos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnoMesto)))
            .andExpect(status().isBadRequest());

        // Validate the RadnoMesto in the database
        List<RadnoMesto> radnoMestoList = radnoMestoRepository.findAll();
        assertThat(radnoMestoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRadnoMestos() throws Exception {
        // Initialize the database
        radnoMestoRepository.saveAndFlush(radnoMesto);

        // Get all the radnoMestoList
        restRadnoMestoMockMvc.perform(get("/api/radno-mestos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(radnoMesto.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].povrsina").value(hasItem(DEFAULT_POVRSINA.doubleValue())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].normaSat").value(hasItem(DEFAULT_NORMA_SAT.intValue())));
    }
    
    @Test
    @Transactional
    public void getRadnoMesto() throws Exception {
        // Initialize the database
        radnoMestoRepository.saveAndFlush(radnoMesto);

        // Get the radnoMesto
        restRadnoMestoMockMvc.perform(get("/api/radno-mestos/{id}", radnoMesto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(radnoMesto.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.povrsina").value(DEFAULT_POVRSINA.doubleValue()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.normaSat").value(DEFAULT_NORMA_SAT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRadnoMesto() throws Exception {
        // Get the radnoMesto
        restRadnoMestoMockMvc.perform(get("/api/radno-mestos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRadnoMesto() throws Exception {
        // Initialize the database
        radnoMestoService.save(radnoMesto);

        int databaseSizeBeforeUpdate = radnoMestoRepository.findAll().size();

        // Update the radnoMesto
        RadnoMesto updatedRadnoMesto = radnoMestoRepository.findById(radnoMesto.getId()).get();
        // Disconnect from session so that the updates on updatedRadnoMesto are not directly saved in db
        em.detach(updatedRadnoMesto);
        updatedRadnoMesto
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .povrsina(UPDATED_POVRSINA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .normaSat(UPDATED_NORMA_SAT);

        restRadnoMestoMockMvc.perform(put("/api/radno-mestos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRadnoMesto)))
            .andExpect(status().isOk());

        // Validate the RadnoMesto in the database
        List<RadnoMesto> radnoMestoList = radnoMestoRepository.findAll();
        assertThat(radnoMestoList).hasSize(databaseSizeBeforeUpdate);
        RadnoMesto testRadnoMesto = radnoMestoList.get(radnoMestoList.size() - 1);
        assertThat(testRadnoMesto.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testRadnoMesto.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testRadnoMesto.getPovrsina()).isEqualTo(UPDATED_POVRSINA);
        assertThat(testRadnoMesto.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testRadnoMesto.getNormaSat()).isEqualTo(UPDATED_NORMA_SAT);
    }

    @Test
    @Transactional
    public void updateNonExistingRadnoMesto() throws Exception {
        int databaseSizeBeforeUpdate = radnoMestoRepository.findAll().size();

        // Create the RadnoMesto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRadnoMestoMockMvc.perform(put("/api/radno-mestos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(radnoMesto)))
            .andExpect(status().isBadRequest());

        // Validate the RadnoMesto in the database
        List<RadnoMesto> radnoMestoList = radnoMestoRepository.findAll();
        assertThat(radnoMestoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRadnoMesto() throws Exception {
        // Initialize the database
        radnoMestoService.save(radnoMesto);

        int databaseSizeBeforeDelete = radnoMestoRepository.findAll().size();

        // Get the radnoMesto
        restRadnoMestoMockMvc.perform(delete("/api/radno-mestos/{id}", radnoMesto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RadnoMesto> radnoMestoList = radnoMestoRepository.findAll();
        assertThat(radnoMestoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RadnoMesto.class);
        RadnoMesto radnoMesto1 = new RadnoMesto();
        radnoMesto1.setId(1L);
        RadnoMesto radnoMesto2 = new RadnoMesto();
        radnoMesto2.setId(radnoMesto1.getId());
        assertThat(radnoMesto1).isEqualTo(radnoMesto2);
        radnoMesto2.setId(2L);
        assertThat(radnoMesto1).isNotEqualTo(radnoMesto2);
        radnoMesto1.setId(null);
        assertThat(radnoMesto1).isNotEqualTo(radnoMesto2);
    }
}
