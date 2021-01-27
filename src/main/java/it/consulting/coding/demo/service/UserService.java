package it.consulting.coding.demo.service;

import it.consulting.coding.demo.model.User;
import it.consulting.coding.demo.model.UserDTO;
import it.consulting.coding.demo.model.UserMapper;
import it.consulting.coding.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO create(UserDTO userDTO) {
        logger.debug("SERVICE::REQUEST TO CREATE NEW USER {}", userDTO);
        return Optional.of(userDTO)
                .map(userMapper::toModel)
                .map(userRepository::save)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    public UserDTO update(UserDTO userDTO) {
        logger.debug("SERVICE::REQUEST TO UPDATE USER {}", userDTO);
        return create(userDTO);
    }

    public List<UserDTO> findAll(int page, int size) {
        logger.debug("SERVICE::REQUEST TO FIND BY PAGE, SIZE {} {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Set<UserDTO> findAllFilter(String filter) {
        switch (filter) {
            case "function": {
                Set<String> functions = this.userRepository
                        .findAll()
                        .stream().map(User::getFunction)
                        .collect(Collectors.toSet());
                return findUsers(functions, filter);
            }

            case "birthday": {
                Set<String> functions = this.userRepository
                        .findAll()
                        .stream().map(User::getBirthday)
                        .collect(Collectors.toSet());
                return findUsers(functions, filter);
            }

            case "address": {
                Set<String> functions = this.userRepository
                        .findAll()
                        .stream().map(User::getAddress)
                        .collect(Collectors.toSet());
                return findUsers(functions, filter);
            }
            case "lastname": {
                Set<String> functions = this.userRepository
                        .findAll()
                        .stream().map(User::getLastname)
                        .collect(Collectors.toSet());
                return findUsers(functions, filter);
            }
        }
        return null;
    }

    private Set<UserDTO> findUsers(Set<String> target, String filter) {
        return target.stream()
                .map(s -> userRepository.findUsers(filter, s).stream().findFirst())
                .map(Optional::get)
                .map(userMapper::toDTO)
                .collect(Collectors.toSet());
    }

    public void delete(String id) {
        logger.debug("SERVICE::REQUEST TO DELETE USER BY ID {}", id);
        this.userRepository.deleteById(id);
    }
}
