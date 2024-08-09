package it.uniroma3.siw.mapper;

import it.uniroma3.siw.dto.CredentialsDTO;
import it.uniroma3.siw.model.Credential;
import org.modelmapper.ModelMapper;

public class CredentialsMapper {

    public static Credential fromDTOToEntity(CredentialsDTO credentialsDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(credentialsDTO, Credential.class);
    }
}
