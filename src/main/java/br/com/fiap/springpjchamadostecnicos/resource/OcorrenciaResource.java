package br.com.fiap.springpjchamadostecnicos.resource;


import br.com.fiap.springpjchamadostecnicos.entity.Chamado;
import br.com.fiap.springpjchamadostecnicos.entity.Ocorrencia;
import br.com.fiap.springpjchamadostecnicos.repository.ChamadoRepository;
import br.com.fiap.springpjchamadostecnicos.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/ocorrencia")
@RestController
public class OcorrenciaResource {

    @Autowired
    private OcorrenciaRepository repo;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping
    public List<Ocorrencia> findAll() {

        return repo.findAll();
    }

    @Transactional
    @PostMapping
    public Ocorrencia save(@RequestBody Ocorrencia ocorrencia) {

        if(Objects.isNull(ocorrencia)) return null;

        if(Objects.nonNull(ocorrencia.getChamado().getId())){
            Chamado chamado = chamadoRepository.findById(ocorrencia.getChamado().getId()).orElseThrow();
            ocorrencia.setChamado(chamado);
            ocorrencia.setData(LocalDateTime.now());
        }

        return repo.save(ocorrencia);
    }

    @GetMapping(value = "/{id}")
    public Ocorrencia findById(@PathVariable Long id) {

        return repo.findById(id).orElseThrow();
    }

}
