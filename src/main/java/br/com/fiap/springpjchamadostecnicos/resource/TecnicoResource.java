package br.com.fiap.springpjchamadostecnicos.resource;


import br.com.fiap.springpjchamadostecnicos.entity.*;
import br.com.fiap.springpjchamadostecnicos.repository.EspecialidadeRepository;
import br.com.fiap.springpjchamadostecnicos.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/tecnico")
@RestController
public class TecnicoResource {

    @Autowired
    private TecnicoRepository repo;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<Tecnico> findAll() {
        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Tecnico save(@RequestBody Tecnico tecnico) {
        return repo.save(tecnico);
    }

    @GetMapping(value = "/{id}")
    public Tecnico findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping(value = "/{id}/especialidade")
    public Tecnico addEspecialidade(@PathVariable Long id, @RequestBody Especialidade e) {

        Tecnico tecnico = repo.findById(id).orElseThrow();

        if(Objects.isNull(e)) return null;

        if(Objects.nonNull(e.getId())) {
            Especialidade especialidade = especialidadeRepository.findById(e.getId()).orElseThrow();
            tecnico.getEspecialidades().add(especialidade);
            return tecnico;
        }
        tecnico.getEspecialidades().add(e);

        return tecnico;

}
