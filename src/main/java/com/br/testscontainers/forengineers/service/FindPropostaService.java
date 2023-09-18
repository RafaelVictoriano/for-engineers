package com.br.testscontainers.forengineers.service;

import com.br.testscontainers.forengineers.dto.CreatePropostaDTO;
import com.br.testscontainers.forengineers.dto.PropostaResponseDTO;

import java.util.List;

public interface FindPropostaService {

    PropostaResponseDTO byId(String propostaId);
    List<PropostaResponseDTO> all();
}
