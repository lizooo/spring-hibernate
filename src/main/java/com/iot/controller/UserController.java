package com.iot.controller;



import com.iot.domain.User;
import com.iot.dto.UserDTO;
import com.iot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
      this.userService = userService;
    }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> getAll() {
    List<User> users = userService.getAll();
    List<UserDTO> usersDTOs = new ArrayList<>();
    for (User user : users) {
      UserDTO userDTO = new UserDTO (
          user.getId(),
          user.getName(),
          user.getSurname(),
          user.getEmail()
      );
      usersDTOs.add(userDTO);
    }
    return new ResponseEntity<>(usersDTOs, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
    User user = userService.getById(id);
    if (user != null) {
      UserDTO userDTO = new UserDTO(
          user.getId(),
          user.getName(),
          user.getSurname(),
          user.getEmail()
      );
      return new ResponseEntity<>(userDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody User newUser) {
    userService.create(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDTO> update(@PathVariable Integer id,
                                               @RequestBody User user) {
    User oldUser = userService.getById(id);
    if (oldUser != null) {
      userService.update(id, user);
      UserDTO oldUserDTO = new UserDTO(
          user.getId(),
          user.getName(),
          user.getSurname(),
          user.getEmail()
      );
      return new ResponseEntity<>(oldUserDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (userService.getById(id) != null) {
      userService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  }
