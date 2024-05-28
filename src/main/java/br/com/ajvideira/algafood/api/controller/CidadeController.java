package br.com.ajvideira.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ajvideira.algafood.domain.exception.EntityInUseException;
import br.com.ajvideira.algafood.domain.exception.EntityNotFoundException;
import br.com.ajvideira.algafood.domain.model.Cidade;
import br.com.ajvideira.algafood.domain.repository.CidadeRepository;
import br.com.ajvideira.algafood.domain.service.CidadeService;

@RequestMapping("/cidades")
@RestController
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CidadeService cidadeService;

    public CidadeController(CidadeRepository cidadeRepository, CidadeService cidadeService) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> getAll() {
        return ResponseEntity.ok(this.cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> getById(@PathVariable Long cidadeId) {
        var cidade = cidadeRepository.findById(cidadeId);

        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cidade> create(@RequestBody Cidade cidade) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidade));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> update(@PathVariable Long cidadeId,
            @RequestBody Cidade cidadeRequest) {
        try {
            var cidade = cidadeRepository.findById(cidadeId);

            if (cidade != null) {
                BeanUtils.copyProperties(cidadeRequest, cidade, "id");
                return ResponseEntity.ok(cidadeService.save(cidade));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{cidadeId}")
    public ResponseEntity<Cidade> partialUpdate(@PathVariable Long cidadeId,
            @RequestBody Map<String, Object> cidadeFields) {
        var cidade = cidadeRepository.findById(cidadeId);

        if (cidade == null) {
            return ResponseEntity.notFound().build();
        }

        var objectMapper = new ObjectMapper();
        var cidadeRequest = objectMapper.convertValue(cidadeFields, Cidade.class);

        var beanWrapperCidade = new BeanWrapperImpl(cidade);
        var beanWrapperCidadeRequest = new BeanWrapperImpl(cidadeRequest);

        cidadeFields.forEach((fieldName, fieldValue) -> {
            beanWrapperCidade.setPropertyValue(fieldName, beanWrapperCidadeRequest.getPropertyValue(fieldName));
        });

        return update(cidadeId, cidade);
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<String> delete(@PathVariable Long cidadeId) {
        try {
            cidadeService.delete(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
