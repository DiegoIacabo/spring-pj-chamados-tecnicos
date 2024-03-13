package br.com.fiap.springpjchamadostecnicos.resource;


import br.com.fiap.springpjchamadostecnicos.entity.Endereco;
import br.com.fiap.springpjchamadostecnicos.entity.Solicitante;
import br.com.fiap.springpjchamadostecnicos.entity.Telefone;
import br.com.fiap.springpjchamadostecnicos.repository.EnderecoRepository;
import br.com.fiap.springpjchamadostecnicos.repository.SolicitanteRepository;
import br.com.fiap.springpjchamadostecnicos.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/solicitante")
@RestController
public class SolicitanteResource {

    @Autowired
    private SolicitanteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping
    public List<Solicitante> findAll() {

        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Solicitante save(@RequestBody Solicitante solicitante) {

        return repo.save(solicitante);
    }

    @GetMapping(value = "/{id}")
    public Solicitante findById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow();
    }

    @GetMapping(value = "/{id}/endereco")
    public List<Endereco> getSolicitanteByEndereco(@PathVariable("id") Long idSolicitante) {
        return enderecoRepository.findBySolicitanteId(idSolicitante);
    }

    @Transactional
    @PostMapping(value = "/{id}/endereco")
    public Endereco addEndereco(@PathVariable Long id, @RequestBody Endereco e) {

        Solicitante solicitante = repo.findById(id).orElseThrow();

        if(Objects.isNull(e)) return null;
        e.setSolicitante(solicitante);
        enderecoRepository.save(e);

        return e;
    }

    @GetMapping(value = "/{id}/telefone")
    public List<Telefone> getSolicitanteByTelefone(@PathVariable("id") Long idSolicitante) {
        return telefoneRepository.findBySolicitanteId(idSolicitante);
    }

    @Transactional
    @PostMapping(value = "/{id}/telefone")
    public Telefone addTelefone(@PathVariable Long id, @RequestBody Telefone t) {

        Solicitante solicitante = repo.findById(id).orElseThrow();

        if (Objects.isNull(t)) return null;
        t.setSolicitante(solicitante);
        telefoneRepository.save(t);

        return t;
    }

}
