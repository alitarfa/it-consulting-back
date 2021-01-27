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
        return null;
    }

    public void delete(String id) {
        logger.debug("SERVICE::REQUEST TO DELETE USER BY ID {}", id);
        this.userRepository.deleteById(id);
    }
}
