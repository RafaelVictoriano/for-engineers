package com.br.testscontainers.forengineers.service.impl;

import com.br.testscontainers.forengineers.dto.CreatePropostaDTO;
import com.br.testscontainers.forengineers.mapper.PropostaMapper;
import com.br.testscontainers.forengineers.repository.PropostaRepository;
import com.br.testscontainers.forengineers.service.CreatePropostaService;
import com.br.testscontainers.forengineers.service.PublishMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CreatePropostaServiceImpl implements CreatePropostaService {


    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaMapper propostaMapper;

    @Autowired
    private PublishMessageService publishMessageService;

    @Override
    public String execute(CreatePropostaDTO createPropostaDTO) {
        log.info("Criando uma nova proposta, request:{}", createPropostaDTO);
        final var proposta = propostaMapper.toEntity(createPropostaDTO);
        propostaRepository.save(proposta);
        publishMessageService.execute("for-engineers-queue", proposta);
        return proposta.getId();
    }
}
