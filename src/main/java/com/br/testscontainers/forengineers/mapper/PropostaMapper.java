package com.br.testscontainers.forengineers.mapper;

import com.br.testscontainers.forengineers.dto.CreatePropostaDTO;
import com.br.testscontainers.forengineers.dto.PropostaResponseDTO;
import com.br.testscontainers.forengineers.model.Proposta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropostaMapper {


    Proposta toEntity(CreatePropostaDTO createProposta);
    PropostaResponseDTO toResponseDto(Proposta entity);
    List<PropostaResponseDTO> toResponseDto(List<Proposta> entity);
}
