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

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
public class PesquisarCarroPorIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;


    @Test
    void devePesquisarCarroPorId() throws Exception {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("HB20");
        carro.setAno(2022);

        Mockito.when(carroService.findById(1L))
                .thenReturn(Optional.of(carro));

        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("HB20")) // Ajustado de HB202 para HB20
                .andExpect(jsonPath("$.ano").value(2022));

        Mockito.verify(carroService, Mockito.times(1)).findById(1L);
    }
}