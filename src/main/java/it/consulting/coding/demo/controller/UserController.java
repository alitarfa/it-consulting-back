package it.consulting.coding.demo.controller;

import it.consulting.coding.demo.model.UserDTO;
import it.consulting.coding.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        logger.debug("RESOURCE::REQUEST TO CREATE NEW USER {}", userDTO);
        UserDTO result = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        logger.debug("RESOURCE::REQUEST TO UPDATE USER {}", userDTO);
        UserDTO result = userService.update(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        logger.debug("RESOURCE::REQUEST TO FIND BY PAGE & SIZE {} {}", page, size);
        List<UserDTO> result = userService.findAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.debug("RESOURCE::REQUEST TO DELETE USER BY ID {}", id);
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
