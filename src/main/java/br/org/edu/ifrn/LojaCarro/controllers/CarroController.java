
package br.org.edu.ifrn.LojaCarro.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;
    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping("/salvar")
    public ResponseEntity<Carro> salvarCarro(
            @RequestBody Carro c) {

        return ResponseEntity.ok(
                carroService.save(c));
    }
    @PreAuthorize(
            "hasAnyRole('GERENTE','VENDEDOR')")

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(
            @PathVariable Long id,
            @RequestBody Carro c) {

        c.setId(id);

        return ResponseEntity.ok(
                carroService.update(c));
    }

    @PreAuthorize("hasRole('GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(
            @PathVariable Long id) {

        carroService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @PreAuthorize(
            "hasAnyRole('GERENTE','VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Carro> pesquisarCarroPorId(
            @PathVariable Long id) {

        return carroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PreAuthorize(
            "hasAnyRole('GERENTE','VENDEDOR')")

    @GetMapping
    public ResponseEntity<List<Carro>> pesquisarTodosCarros() {

        return ResponseEntity.ok(
                carroService.findAll());
    }
}