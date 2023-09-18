package com.br.testscontainers.forengineers.service.impl;

import com.br.testscontainers.forengineers.dto.PropostaResponseDTO;
import com.br.testscontainers.forengineers.mapper.PropostaMapper;
import com.br.testscontainers.forengineers.model.Proposta;
import com.br.testscontainers.forengineers.repository.PropostaRepository;
import com.br.testscontainers.forengineers.service.FindPropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindPropostaServiceImpl implements FindPropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaMapper propostaMapper;

    @Override
    public PropostaResponseDTO byId(String propostaId) {
        final var proposta = propostaRepository.findById(propostaId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Proposta não encontrada"));

        return propostaMapper.toResponseDto(proposta);
    }

    @Override
    public List<PropostaResponseDTO> all() {
        final var propostas = propostaRepository.findAll();
        return propostaMapper.toResponseDto(propostas);
    }
}
