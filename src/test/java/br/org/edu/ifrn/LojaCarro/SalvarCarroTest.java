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
        // Cenário: Cria o objeto sem ID, pois o MySQL vai gerar
        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setAno(2024);

        // Ação e Verificação: Envia os dados para a API salvar no banco
        mockMvc.perform(post("/carro/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()) // Garante que o banco gerou um ID
                .andExpect(jsonPath("$.modelo").value("Civic"))
                .andExpect(jsonPath("$.ano").value(2024));
    }
}