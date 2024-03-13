package br.com.fiap.springpjchamadostecnicos.resource;

import br.com.fiap.springpjchamadostecnicos.entity.Chamado;
import br.com.fiap.springpjchamadostecnicos.entity.Especialidade;
import br.com.fiap.springpjchamadostecnicos.entity.Tecnico;
import br.com.fiap.springpjchamadostecnicos.repository.ChamadoRepository;
import br.com.fiap.springpjchamadostecnicos.repository.EspecialidadeRepository;
import br.com.fiap.springpjchamadostecnicos.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/chamado")
public class ChamadoResource {

    @Autowired
    private ChamadoRepository repo;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<Chamado> findAll() {

        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Chamado save(@RequestBody Chamado chamado) {

        if(Objects.isNull(chamado)) return null;

        if(Objects.nonNull(chamado.getTecnico().getId())) {
            Tecnico tecnico = tecnicoRepository.findById(chamado.getTecnico().getId()).orElseThrow();
            chamado.setTecnico(tecnico);
        }

        if(Objects.nonNull(chamado.getEspecialidade().getId())){
            Especialidade especialidade = especialidadeRepository.findById(chamado.getEspecialidade().getId()).orElseThrow();
            chamado.setEspecialidade(especialidade);
        }

        return repo.save(chamado);
    }

    @GetMapping(value = "/{id}")
    public Chamado findById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow();
    }

}


