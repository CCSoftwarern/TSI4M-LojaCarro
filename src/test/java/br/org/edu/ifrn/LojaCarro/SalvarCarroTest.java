package br.org.edu.ifrn.LojaCarro;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Inicia o Spring e conecta no banco real
@AutoConfigureMockMvc // Permite testar as rotas HTTP do Controller
@Transactional // Apaga o registro do banco após o teste terminar
public class SalvarCarroTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarCarro() throws Exception {

        // Cenário: Modelo inválido (vazio)
        Carro carroInvalido = new Carro();
        carroInvalido.setModelo(""); // String vazia para forçar o erro de validação
        carroInvalido.setAno(2024);

        // Ação e Verificação: O Spring deve interceptar e devolver 400 Bad Request
        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carroInvalido)))
                .andExpect(status().isBadRequest()); // Espera erro 400 (Bad Request)
    }


}

