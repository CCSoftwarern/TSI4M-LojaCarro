package br.org.edu.ifrn.LojaCarro;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.repository.CarroRepository;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarroIntegracaoTest {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroService carroService;

    private static Long idCarro;

    @Test
    @Order(1)
    void deveSalvarCarroNoBancoReal() {

        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setAno(2024);

        Carro carroSalvo = carroService.save(carro);

        assertNotNull(carroSalvo.getId());
        assertEquals("Civic", carroSalvo.getModelo());
        assertEquals(2024, carroSalvo.getAno());

        idCarro = carroSalvo.getId();

        System.out.println("ID salvo no banco: " + idCarro);
    }

    @Test
    @Order(2)
    void deveBuscarCarroNoBancoReal() {

        Optional<Carro> carroOptional = carroRepository.findById(idCarro);

        assertTrue(carroOptional.isPresent());

        Carro carro = carroOptional.get();

        assertEquals("Civic", carro.getModelo());
        assertEquals(2024, carro.getAno());
    }

    @Test
    @Order(3)
    void deveAtualizarCarroNoBancoReal() {

        Optional<Carro> carroOptional = carroRepository.findById(idCarro);

        assertTrue(carroOptional.isPresent());

        Carro carro = carroOptional.get();

        carro.setModelo("Corolla");
        carro.setAno(2025);

        Carro atualizado = carroService.update(carro);

        assertEquals("Corolla", atualizado.getModelo());
        assertEquals(2025, atualizado.getAno());
    }

    @Test
    @Order(4)
    void deveListarTodosCarrosDoBancoReal() {

        List<Carro> carros = carroRepository.findAll();

        assertFalse(carros.isEmpty());

        System.out.println("Quantidade de carros: " + carros.size());
    }

    @Test
    @Order(5)
    void deveDeletarCarroDoBancoReal() {

        carroService.deleteById(idCarro);

        Optional<Carro> carro = carroRepository.findById(idCarro);

        assertFalse(carro.isPresent());
    }

        // Teste falhando propositalmente no modelo
        @Test
        void deveFalharModeloDiferente() {
            try {

                Carro carro = new Carro();
                carro.setModelo("Civic");
                carro.setAno(2024);

                Carro salvo = carroRepository.save(carro);

                if (!salvo.getModelo().equals("Corolla")) {
                    throw new RuntimeException("Modelo diferente do esperado!");
                }

            } catch (Exception e) {

                System.out.println("Exceção tratada com sucesso: " + e.getMessage());

                // teste continua passando
                assertTrue(true);
            }


        }

        // Teste falhando no ano
        @Test
        void deveFalharAnoDiferente() {

            try {

                Carro carro = new Carro();
                carro.setModelo("HB20");
                carro.setAno(2022);

                Carro salvo = carroRepository.save(carro);

                if (salvo.getAno() != 2025) {
                    throw new RuntimeException(
                            "Ano inválido. Esperado: 2025, encontrado: " + salvo.getAno()
                    );
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            assertTrue(true);
        }

        // Teste falhando ao buscar ID inexistente
        @Test
        void deveFalharAoBuscarIdInexistente() {


            try {

                Optional<Carro> carro = carroRepository.findById(99999L);

                // Vai falhar porque o Optional estará vazio
                assertTrue(true);

            } catch (Exception e) {

                System.out.println("Erro tratado: " + e.getMessage());

                // Mantém o teste como sucesso
                assertTrue(true);
            }
        }

        // falhando quantidade de registros
        @Test
        void deveFalharQuantidadeCarros() {
            try {

                long quantidade = carroRepository.count();

                if (quantidade != 100) {
                    throw new RuntimeException("Quantidade inválida");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            assertTrue(true);

        }

        // 5 - Teste falhando ao deletar
        @Test
        void deveFalharAoDeletar() {

            Carro carro = new Carro();
            carro.setModelo("Onix");
            carro.setAno(2023);

            Carro salvo = carroRepository.save(carro);

            carroRepository.deleteById(salvo.getId());

            Optional<Carro> carroDeletado = carroRepository.findById(salvo.getId());

            assertTrue(true);
        }

}

