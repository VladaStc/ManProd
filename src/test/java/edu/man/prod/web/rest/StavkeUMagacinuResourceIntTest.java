package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.StavkeUMagacinu;
import edu.man.prod.repository.StavkeUMagacinuRepository;
import edu.man.prod.service.StavkeUMagacinuService;
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

import edu.man.prod.domain.enumeration.TipMagacina;
/**
 * Test class for the StavkeUMagacinuResource REST controller.
 *
 * @see StavkeUMagacinuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class StavkeUMagacinuResourceIntTest {

    private static final Double DEFAULT_STANJE = 1D;
    private static final Double UPDATED_STANJE = 2D;

    private static final TipMagacina DEFAULT_TIP_MAGACINA = TipMagacina.MAGACIN_SIROVINA;
    private static final TipMagacina UPDATED_TIP_MAGACINA = TipMagacina.MAGACIN_GOTOVIH_PROIZVODA;

    @Autowired
    private StavkeUMagacinuRepository stavkeUMagacinuRepository;
    
    @Autowired
    private StavkeUMagacinuService stavkeUMagacinuService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStavkeUMagacinuMockMvc;

    private StavkeUMagacinu stavkeUMagacinu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StavkeUMagacinuResource stavkeUMagacinuResource = new StavkeUMagacinuResource(stavkeUMagacinuService);
        this.restStavkeUMagacinuMockMvc = MockMvcBuilders.standaloneSetup(stavkeUMagacinuResource)
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
    public static StavkeUMagacinu createEntity(EntityManager em) {
        StavkeUMagacinu stavkeUMagacinu = new StavkeUMagacinu()
            .stanje(DEFAULT_STANJE)
            .tipMagacina(DEFAULT_TIP_MAGACINA);
        return stavkeUMagacinu;
    }

    @Before
    public void initTest() {
        stavkeUMagacinu = createEntity(em);
    }

    @Test
    @Transactional
    public void createStavkeUMagacinu() throws Exception {
        int databaseSizeBeforeCreate = stavkeUMagacinuRepository.findAll().size();

        // Create the StavkeUMagacinu
        restStavkeUMagacinuMockMvc.perform(post("/api/stavke-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stavkeUMagacinu)))
            .andExpect(status().isCreated());

        // Validate the StavkeUMagacinu in the database
        List<StavkeUMagacinu> stavkeUMagacinuList = stavkeUMagacinuRepository.findAll();
        assertThat(stavkeUMagacinuList).hasSize(databaseSizeBeforeCreate + 1);
        StavkeUMagacinu testStavkeUMagacinu = stavkeUMagacinuList.get(stavkeUMagacinuList.size() - 1);
        assertThat(testStavkeUMagacinu.getStanje()).isEqualTo(DEFAULT_STANJE);
        assertThat(testStavkeUMagacinu.getTipMagacina()).isEqualTo(DEFAULT_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void createStavkeUMagacinuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stavkeUMagacinuRepository.findAll().size();

        // Create the StavkeUMagacinu with an existing ID
        stavkeUMagacinu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStavkeUMagacinuMockMvc.perform(post("/api/stavke-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stavkeUMagacinu)))
            .andExpect(status().isBadRequest());

        // Validate the StavkeUMagacinu in the database
        List<StavkeUMagacinu> stavkeUMagacinuList = stavkeUMagacinuRepository.findAll();
        assertThat(stavkeUMagacinuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStavkeUMagacinus() throws Exception {
        // Initialize the database
        stavkeUMagacinuRepository.saveAndFlush(stavkeUMagacinu);

        // Get all the stavkeUMagacinuList
        restStavkeUMagacinuMockMvc.perform(get("/api/stavke-u-magacinus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stavkeUMagacinu.getId().intValue())))
            .andExpect(jsonPath("$.[*].stanje").value(hasItem(DEFAULT_STANJE.doubleValue())))
            .andExpect(jsonPath("$.[*].tipMagacina").value(hasItem(DEFAULT_TIP_MAGACINA.toString())));
    }
    
    @Test
    @Transactional
    public void getStavkeUMagacinu() throws Exception {
        // Initialize the database
        stavkeUMagacinuRepository.saveAndFlush(stavkeUMagacinu);

        // Get the stavkeUMagacinu
        restStavkeUMagacinuMockMvc.perform(get("/api/stavke-u-magacinus/{id}", stavkeUMagacinu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stavkeUMagacinu.getId().intValue()))
            .andExpect(jsonPath("$.stanje").value(DEFAULT_STANJE.doubleValue()))
            .andExpect(jsonPath("$.tipMagacina").value(DEFAULT_TIP_MAGACINA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStavkeUMagacinu() throws Exception {
        // Get the stavkeUMagacinu
        restStavkeUMagacinuMockMvc.perform(get("/api/stavke-u-magacinus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStavkeUMagacinu() throws Exception {
        // Initialize the database
        stavkeUMagacinuService.save(stavkeUMagacinu);

        int databaseSizeBeforeUpdate = stavkeUMagacinuRepository.findAll().size();

        // Update the stavkeUMagacinu
        StavkeUMagacinu updatedStavkeUMagacinu = stavkeUMagacinuRepository.findById(stavkeUMagacinu.getId()).get();
        // Disconnect from session so that the updates on updatedStavkeUMagacinu are not directly saved in db
        em.detach(updatedStavkeUMagacinu);
        updatedStavkeUMagacinu
            .stanje(UPDATED_STANJE)
            .tipMagacina(UPDATED_TIP_MAGACINA);

        restStavkeUMagacinuMockMvc.perform(put("/api/stavke-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStavkeUMagacinu)))
            .andExpect(status().isOk());

        // Validate the StavkeUMagacinu in the database
        List<StavkeUMagacinu> stavkeUMagacinuList = stavkeUMagacinuRepository.findAll();
        assertThat(stavkeUMagacinuList).hasSize(databaseSizeBeforeUpdate);
        StavkeUMagacinu testStavkeUMagacinu = stavkeUMagacinuList.get(stavkeUMagacinuList.size() - 1);
        assertThat(testStavkeUMagacinu.getStanje()).isEqualTo(UPDATED_STANJE);
        assertThat(testStavkeUMagacinu.getTipMagacina()).isEqualTo(UPDATED_TIP_MAGACINA);
    }

    @Test
    @Transactional
    public void updateNonExistingStavkeUMagacinu() throws Exception {
        int databaseSizeBeforeUpdate = stavkeUMagacinuRepository.findAll().size();

        // Create the StavkeUMagacinu

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStavkeUMagacinuMockMvc.perform(put("/api/stavke-u-magacinus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stavkeUMagacinu)))
            .andExpect(status().isBadRequest());

        // Validate the StavkeUMagacinu in the database
        List<StavkeUMagacinu> stavkeUMagacinuList = stavkeUMagacinuRepository.findAll();
        assertThat(stavkeUMagacinuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStavkeUMagacinu() throws Exception {
        // Initialize the database
        stavkeUMagacinuService.save(stavkeUMagacinu);

        int databaseSizeBeforeDelete = stavkeUMagacinuRepository.findAll().size();

        // Get the stavkeUMagacinu
        restStavkeUMagacinuMockMvc.perform(delete("/api/stavke-u-magacinus/{id}", stavkeUMagacinu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StavkeUMagacinu> stavkeUMagacinuList = stavkeUMagacinuRepository.findAll();
        assertThat(stavkeUMagacinuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StavkeUMagacinu.class);
        StavkeUMagacinu stavkeUMagacinu1 = new StavkeUMagacinu();
        stavkeUMagacinu1.setId(1L);
        StavkeUMagacinu stavkeUMagacinu2 = new StavkeUMagacinu();
        stavkeUMagacinu2.setId(stavkeUMagacinu1.getId());
        assertThat(stavkeUMagacinu1).isEqualTo(stavkeUMagacinu2);
        stavkeUMagacinu2.setId(2L);
        assertThat(stavkeUMagacinu1).isNotEqualTo(stavkeUMagacinu2);
        stavkeUMagacinu1.setId(null);
        assertThat(stavkeUMagacinu1).isNotEqualTo(stavkeUMagacinu2);
    }
}
