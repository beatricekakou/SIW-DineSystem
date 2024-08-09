package it.uniroma3.siw.mapper;

import it.uniroma3.siw.dto.UserDTO;
import it.uniroma3.siw.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static User fromDTOToEntity(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO,User.class);
    }
}
