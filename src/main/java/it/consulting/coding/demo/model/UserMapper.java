package it.consulting.coding.demo.model;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    /**
     * This User mapper is simple example, we don't have a complex objects
     */

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
