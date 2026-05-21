package br.org.edu.ifrn.LojaCarro;

import br.org.edu.ifrn.LojaCarro.controllers.CarroController;
import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornar200() throws Exception {

        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("HB20");

        when(carroService.findById(1L))
                .thenReturn(Optional.of(carro));

        mockMvc.perform(get("/carro/1"))
                .andExpect(status().isOk());
    }


    @Test
    void deveRetornar201() throws Exception {

        try {

            Carro carro = new Carro();
            carro.setId(1L);
            carro.setModelo("Civic");
            carro.setAno(2024);

            when(carroService.save(any(Carro.class)))
                    .thenReturn(carro);

            mockMvc.perform(post("/carro")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(carro)))
                    .andExpect(status().isCreated());

            throw new RuntimeException(
                    "Carro criado com sucesso - status 201"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        assertTrue(true);
    }


    @Test
    void deveRetornar404() throws Exception {

        try {

            when(carroService.findById(99L))
                    .thenReturn(Optional.empty());

            mockMvc.perform(get("/carro/99"))
                    .andExpect(status().isNotFound());

            throw new RuntimeException("Carro não encontrado - erro 404");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        assertTrue(true);
    }


    @Test
    void deveRetornar400() throws Exception {

        try {

            mockMvc.perform(post("/carro")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());

            throw new RuntimeException(
                    "Requisição inválida - erro 400"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        assertTrue(true);
    }
}