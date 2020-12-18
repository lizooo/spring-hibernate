package com.iot.controller;

import com.iot.domain.Credentials;
import com.iot.dto.CredentialsDTO;
import com.iot.service.CredentialsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/credentials")
@RestController

public class CredentialsController {
  private final CredentialsService credentialsService;

  public CredentialsController(CredentialsService credentialsService){
    this.credentialsService = credentialsService;
  }

    @RequestMapping(method = RequestMethod.GET)
    // зверніть увагу: get - віддає щось до користувача, то ми маємо віддати DTO, а не об'єкти
    public ResponseEntity<List<CredentialsDTO>> getAll() {
      // стягуємо всі ентіті
      List<Credentials> credentials = credentialsService.getAll();
      // створюємо (поки що) пустий список для DTOшок
      List<CredentialsDTO> credentialsDtos = new ArrayList<>();
      // перебираємо кожен ентіті, що ми знайшли, конвертуємо в DTO і додаємо DTO до ліста
      for (Credentials credential : credentials) {
        CredentialsDTO credentialDto = new CredentialsDTO (
            credential.getId(),
            credential.getUserName(),
            credential.getDcryptPassword(),
            credential.getUser().getName(),
            credential.getUser().getSurname()
        );
        credentialsDtos.add(credentialDto);
      }
      return new ResponseEntity<>(credentialsDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    // віддаємо на клієнтську частину дані - знову ж таки, віддаємо їх як DTO
    public ResponseEntity<CredentialsDTO> getById(@PathVariable Integer id) {
      Credentials credential = credentialsService.getById(id);
      if (credential != null) {
        // і знову та сама хуйня дублюється... бачите чому класно мати якийсь Mapper?
        CredentialsDTO credentialDto = new CredentialsDTO(
            credential.getId(), // сетаємо значення для id
            credential.getUserName(),
            credential.getDcryptPassword(),
            credential.getUser().getName(),
            credential.getUser().getSurname()
        );
        return new ResponseEntity<>(credentialDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    // параметр consumes - кажемо, що POST в нас хаває дані теж в форматі JSON
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody Credentials newCredentials) {
      // а тут DTOшок вже не буде, бо нам ж треба в базу зберегти нормальний об'єкт,
      // а не DTO, правда?
      credentialsService.create(newCredentials);
      return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
        value = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CredentialsDTO> update(@PathVariable Integer id,
                                             @RequestBody Credentials credential) {
      Credentials oldCredential = credentialsService.getById(id);
      if (oldCredential != null) {
        credentialsService.update(id, credential);
        CredentialsDTO oldCredentialDTO = new CredentialsDTO(
            credential.getId(), // сетаємо значення для id
            credential.getUserName(),
            credential.getDcryptPassword(),
            credential.getUser().getName(),
            credential.getUser().getSurname()
        );
        return new ResponseEntity<>(oldCredentialDTO, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
      if (credentialsService.getById(id) != null) {
        credentialsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }





