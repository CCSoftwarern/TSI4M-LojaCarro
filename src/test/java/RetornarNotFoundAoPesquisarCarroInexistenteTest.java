import br.org.edu.ifrn.LojaCarro.LojaCarroApplication;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LojaCarroApplication.class)
@AutoConfigureMockMvc


public class RetornarNotFoundAoPesquisarCarroInexistenteTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarNotFoundAoPesquisarCarroInexistente() throws Exception {

        Mockito.when(carroService.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isNotFound());
    }

}
