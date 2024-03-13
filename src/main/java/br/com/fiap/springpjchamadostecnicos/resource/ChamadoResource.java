package br.com.fiap.springpjchamadostecnicos.resource;

import br.com.fiap.springpjchamadostecnicos.entity.Chamado;
import br.com.fiap.springpjchamadostecnicos.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chamado")
public class ChamadoResource {

    @Autowired
    private ChamadoRepository repo;

    @GetMapping
    public List<Chamado> findAll() {

        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Chamado save(@RequestBody Chamado chamado) {

        return repo.save(chamado);
    }

    @GetMapping(value = "/{id}")
    public Chamado findById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow();
    }

}


