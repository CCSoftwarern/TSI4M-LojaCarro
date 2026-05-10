import br.org.edu.ifrn.LojaCarro.LojaCarroApplication;
import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LojaCarroApplication.class)
@AutoConfigureMockMvc
public class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarCarro() throws Exception {

        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Civic");
        carro.setAno(2024);

        Mockito.when(carroService.save(any(Carro.class)))
                .thenReturn(carro);

        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("Civic"))
                .andExpect(jsonPath("$.ano").value(2024));
    }

    @Test
    void deveAtualizarCarro() throws Exception {

        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Corolla");
        carro.setAno(2023);

        Mockito.when(carroService.update(any(Carro.class)))
                .thenReturn(carro);

        mockMvc.perform(put("/carro/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("Corolla"))
                .andExpect(jsonPath("$.ano").value(2023));
    }

    @Test
    void deveDeletarCarro() throws Exception {

        Mockito.doNothing().when(carroService).deleteById(anyLong());

        mockMvc.perform(delete("/carro/1"))
                .andExpect(status().isNoContent());
    }

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
                .andExpect(jsonPath("$.modelo").value("HB20"))
                .andExpect(jsonPath("$.ano").value(2022));
    }

    @Test
    void deveRetornarNotFoundAoPesquisarCarroInexistente() throws Exception {

        Mockito.when(carroService.findById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isNotFound());
    }

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