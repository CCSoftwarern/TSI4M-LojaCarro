package br.org.edu.ifrn.LojaCarro;

import br.org.edu.ifrn.LojaCarro.controllers.CarroController;
import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class PesquisarTodosCarrosTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

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

        Mockito.when(carroService.findAll()).thenReturn(carros);

        mockMvc.perform(get("/carro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].modelo").value("Gol"))
                .andExpect(jsonPath("$[0].ano").value(2020))
                .andExpect(jsonPath("$[1].modelo").value("Onix"))
                .andExpect(jsonPath("$[1].ano").value(2021));

        Mockito.verify(carroService, Mockito.times(1)).findAll();
    }
}