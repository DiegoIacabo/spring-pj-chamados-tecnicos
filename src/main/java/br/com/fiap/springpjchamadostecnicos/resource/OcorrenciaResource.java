package br.com.fiap.springpjchamadostecnicos.resource;


import br.com.fiap.springpjchamadostecnicos.entity.Chamado;
import br.com.fiap.springpjchamadostecnicos.entity.Ocorrencia;
import br.com.fiap.springpjchamadostecnicos.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/ocorrencia")
@RestController
public class OcorrenciaResource {

    @Autowired
    private OcorrenciaRepository repo;

    @GetMapping
    public List<Ocorrencia> findAll() {

        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Ocorrencia save(@RequestBody Ocorrencia ocorrencia) {

        return repo.save(ocorrencia);
    }

    @GetMapping(value = "/{id}")
    public Ocorrencia findById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow();
    }

}
