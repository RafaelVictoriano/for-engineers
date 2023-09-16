package com.br.testscontainers.forengineers.controller;

import com.br.testscontainers.forengineers.dto.CreatePropostaDTO;
import com.br.testscontainers.forengineers.dto.PropostaResponseDTO;
import com.br.testscontainers.forengineers.service.CreatePropostaService;
import com.br.testscontainers.forengineers.service.FindPropostaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("creditos/v1/proposta")
@RestController
public class PropostaController {

    @Autowired
    private CreatePropostaService createPropostaService;

    @Autowired
    private FindPropostaService findPropostaService;

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody CreatePropostaDTO body, UriComponentsBuilder uri) {
        final var propostaId = createPropostaService.execute(body);
        final var uriCreated = uri.path("/creditos/v1/{propostaId}").buildAndExpand(propostaId).toUri();
        return ResponseEntity.created(uriCreated).build();
    }

    @GetMapping("/{propostaId}")
    public ResponseEntity<PropostaResponseDTO> get(@PathVariable("propostaId") String propostaId) {
        return ResponseEntity.ok(findPropostaService.byId(propostaId));
    }
}
