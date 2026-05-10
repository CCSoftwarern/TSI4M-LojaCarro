package br.org.edu.ifrn.LojaCarro;

import br.org.edu.ifrn.LojaCarro.controllers.CarroController;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class DeletarCarroTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;


    @Test
    void deveDeletarCarro() throws Exception {

        Mockito.doNothing().when(carroService).deleteById(anyLong());
        mockMvc.perform(delete("/carro/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(carroService, Mockito.times(1)).deleteById(1L);
    }
}