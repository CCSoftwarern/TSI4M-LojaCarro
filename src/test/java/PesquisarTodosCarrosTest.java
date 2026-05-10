import br.org.edu.ifrn.LojaCarro.LojaCarroApplication;
import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LojaCarroApplication.class)
@AutoConfigureMockMvc


public class PesquisarTodosCarrosTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void devePesquisarTodosCarros() throws Exception {

        Carro carro1 = new Carro();
        carro1.setId(1L);
        carro1.setModelo("Gol");
        carro1.setAno(2020);

        Carro carro2 = new Carro();
        carro2.setId(2L);
        carro2.setModelo("Onix");
        carro2.setAno(2021);

        List<Carro> carros = Arrays.asList(carro1, carro2);

        Mockito.when(carroService.findAll())
                .thenReturn(carros);

        mockMvc.perform(get("/carro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modelo").value("Gol"))
                .andExpect(jsonPath("$[0].ano").value(2020))
                .andExpect(jsonPath("$[1].modelo").value("Onix"))
                .andExpect(jsonPath("$[1].ano").value(2021));
    }


}
