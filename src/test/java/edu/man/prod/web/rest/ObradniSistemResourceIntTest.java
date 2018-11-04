package edu.man.prod.web.rest;

import edu.man.prod.ManProdApp;

import edu.man.prod.domain.ObradniSistem;
import edu.man.prod.repository.ObradniSistemRepository;
import edu.man.prod.service.ObradniSistemService;
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
 * Test class for the ObradniSistemResource REST controller.
 *
 * @see ObradniSistemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManProdApp.class)
public class ObradniSistemResourceIntTest {

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    private static final String DEFAULT_LOKACIJA = "AAAAAAAAAA";
    private static final String UPDATED_LOKACIJA = "BBBBBBBBBB";

    private static final String DEFAULT_STRUKTURA = "AAAAAAAAAA";
    private static final String UPDATED_STRUKTURA = "BBBBBBBBBB";

    private static final String DEFAULT_RUKOVODILAC = "AAAAAAAAAA";
    private static final String UPDATED_RUKOVODILAC = "BBBBBBBBBB";

    private static final String DEFAULT_NAPOMENA = "AAAAAAAAAA";
    private static final String UPDATED_NAPOMENA = "BBBBBBBBBB";

    @Autowired
    private ObradniSistemRepository obradniSistemRepository;
    
    @Autowired
    private ObradniSistemService obradniSistemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObradniSistemMockMvc;

    private ObradniSistem obradniSistem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObradniSistemResource obradniSistemResource = new ObradniSistemResource(obradniSistemService);
        this.restObradniSistemMockMvc = MockMvcBuilders.standaloneSetup(obradniSistemResource)
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
    public static ObradniSistem createEntity(EntityManager em) {
        ObradniSistem obradniSistem = new ObradniSistem()
            .naziv(DEFAULT_NAZIV)
            .lokacija(DEFAULT_LOKACIJA)
            .struktura(DEFAULT_STRUKTURA)
            .rukovodilac(DEFAULT_RUKOVODILAC)
            .napomena(DEFAULT_NAPOMENA);
        return obradniSistem;
    }

    @Before
    public void initTest() {
        obradniSistem = createEntity(em);
    }

    @Test
    @Transactional
    public void createObradniSistem() throws Exception {
        int databaseSizeBeforeCreate = obradniSistemRepository.findAll().size();

        // Create the ObradniSistem
        restObradniSistemMockMvc.perform(post("/api/obradni-sistems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obradniSistem)))
            .andExpect(status().isCreated());

        // Validate the ObradniSistem in the database
        List<ObradniSistem> obradniSistemList = obradniSistemRepository.findAll();
        assertThat(obradniSistemList).hasSize(databaseSizeBeforeCreate + 1);
        ObradniSistem testObradniSistem = obradniSistemList.get(obradniSistemList.size() - 1);
        assertThat(testObradniSistem.getNaziv()).isEqualTo(DEFAULT_NAZIV);
        assertThat(testObradniSistem.getLokacija()).isEqualTo(DEFAULT_LOKACIJA);
        assertThat(testObradniSistem.getStruktura()).isEqualTo(DEFAULT_STRUKTURA);
        assertThat(testObradniSistem.getRukovodilac()).isEqualTo(DEFAULT_RUKOVODILAC);
        assertThat(testObradniSistem.getNapomena()).isEqualTo(DEFAULT_NAPOMENA);
    }

    @Test
    @Transactional
    public void createObradniSistemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = obradniSistemRepository.findAll().size();

        // Create the ObradniSistem with an existing ID
        obradniSistem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObradniSistemMockMvc.perform(post("/api/obradni-sistems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obradniSistem)))
            .andExpect(status().isBadRequest());

        // Validate the ObradniSistem in the database
        List<ObradniSistem> obradniSistemList = obradniSistemRepository.findAll();
        assertThat(obradniSistemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObradniSistems() throws Exception {
        // Initialize the database
        obradniSistemRepository.saveAndFlush(obradniSistem);

        // Get all the obradniSistemList
        restObradniSistemMockMvc.perform(get("/api/obradni-sistems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(obradniSistem.getId().intValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV.toString())))
            .andExpect(jsonPath("$.[*].lokacija").value(hasItem(DEFAULT_LOKACIJA.toString())))
            .andExpect(jsonPath("$.[*].struktura").value(hasItem(DEFAULT_STRUKTURA.toString())))
            .andExpect(jsonPath("$.[*].rukovodilac").value(hasItem(DEFAULT_RUKOVODILAC.toString())))
            .andExpect(jsonPath("$.[*].napomena").value(hasItem(DEFAULT_NAPOMENA.toString())));
    }
    
    @Test
    @Transactional
    public void getObradniSistem() throws Exception {
        // Initialize the database
        obradniSistemRepository.saveAndFlush(obradniSistem);

        // Get the obradniSistem
        restObradniSistemMockMvc.perform(get("/api/obradni-sistems/{id}", obradniSistem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(obradniSistem.getId().intValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV.toString()))
            .andExpect(jsonPath("$.lokacija").value(DEFAULT_LOKACIJA.toString()))
            .andExpect(jsonPath("$.struktura").value(DEFAULT_STRUKTURA.toString()))
            .andExpect(jsonPath("$.rukovodilac").value(DEFAULT_RUKOVODILAC.toString()))
            .andExpect(jsonPath("$.napomena").value(DEFAULT_NAPOMENA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObradniSistem() throws Exception {
        // Get the obradniSistem
        restObradniSistemMockMvc.perform(get("/api/obradni-sistems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObradniSistem() throws Exception {
        // Initialize the database
        obradniSistemService.save(obradniSistem);

        int databaseSizeBeforeUpdate = obradniSistemRepository.findAll().size();

        // Update the obradniSistem
        ObradniSistem updatedObradniSistem = obradniSistemRepository.findById(obradniSistem.getId()).get();
        // Disconnect from session so that the updates on updatedObradniSistem are not directly saved in db
        em.detach(updatedObradniSistem);
        updatedObradniSistem
            .naziv(UPDATED_NAZIV)
            .lokacija(UPDATED_LOKACIJA)
            .struktura(UPDATED_STRUKTURA)
            .rukovodilac(UPDATED_RUKOVODILAC)
            .napomena(UPDATED_NAPOMENA);

        restObradniSistemMockMvc.perform(put("/api/obradni-sistems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedObradniSistem)))
            .andExpect(status().isOk());

        // Validate the ObradniSistem in the database
        List<ObradniSistem> obradniSistemList = obradniSistemRepository.findAll();
        assertThat(obradniSistemList).hasSize(databaseSizeBeforeUpdate);
        ObradniSistem testObradniSistem = obradniSistemList.get(obradniSistemList.size() - 1);
        assertThat(testObradniSistem.getNaziv()).isEqualTo(UPDATED_NAZIV);
        assertThat(testObradniSistem.getLokacija()).isEqualTo(UPDATED_LOKACIJA);
        assertThat(testObradniSistem.getStruktura()).isEqualTo(UPDATED_STRUKTURA);
        assertThat(testObradniSistem.getRukovodilac()).isEqualTo(UPDATED_RUKOVODILAC);
        assertThat(testObradniSistem.getNapomena()).isEqualTo(UPDATED_NAPOMENA);
    }

    @Test
    @Transactional
    public void updateNonExistingObradniSistem() throws Exception {
        int databaseSizeBeforeUpdate = obradniSistemRepository.findAll().size();

        // Create the ObradniSistem

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObradniSistemMockMvc.perform(put("/api/obradni-sistems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(obradniSistem)))
            .andExpect(status().isBadRequest());

        // Validate the ObradniSistem in the database
        List<ObradniSistem> obradniSistemList = obradniSistemRepository.findAll();
        assertThat(obradniSistemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObradniSistem() throws Exception {
        // Initialize the database
        obradniSistemService.save(obradniSistem);

        int databaseSizeBeforeDelete = obradniSistemRepository.findAll().size();

        // Get the obradniSistem
        restObradniSistemMockMvc.perform(delete("/api/obradni-sistems/{id}", obradniSistem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ObradniSistem> obradniSistemList = obradniSistemRepository.findAll();
        assertThat(obradniSistemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObradniSistem.class);
        ObradniSistem obradniSistem1 = new ObradniSistem();
        obradniSistem1.setId(1L);
        ObradniSistem obradniSistem2 = new ObradniSistem();
        obradniSistem2.setId(obradniSistem1.getId());
        assertThat(obradniSistem1).isEqualTo(obradniSistem2);
        obradniSistem2.setId(2L);
        assertThat(obradniSistem1).isNotEqualTo(obradniSistem2);
        obradniSistem1.setId(null);
        assertThat(obradniSistem1).isNotEqualTo(obradniSistem2);
    }
}
