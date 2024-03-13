package br.com.fiap.springpjchamadostecnicos.resource;


import br.com.fiap.springpjchamadostecnicos.entity.Especialidade;
import br.com.fiap.springpjchamadostecnicos.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/especialidade")
@RestController
public class EspecialidadeResource {

    @Autowired
    private EspecialidadeRepository repo;

    @GetMapping
    public List<Especialidade> findAll() {
        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Especialidade save(@RequestBody Especialidade especialidade) {
        return repo.save(especialidade);
    }

    @GetMapping(value = "/{id}")
    public Especialidade findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

}
