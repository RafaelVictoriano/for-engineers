package com.br.testscontainers.forengineers.service;

import com.br.testscontainers.forengineers.dto.CreatePropostaDTO;
import com.br.testscontainers.forengineers.dto.PropostaResponseDTO;

public interface FindPropostaService {

    PropostaResponseDTO byId(String propostaId);
}
