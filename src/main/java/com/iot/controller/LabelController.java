package com.iot.controller;


import com.iot.domain.Label;
import com.iot.dto.LabelDTO;
import com.iot.service.LabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/label")
@RestController

public class LabelController {
  private final LabelService labelService;

  public LabelController(LabelService labelService){
    this.labelService = labelService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<LabelDTO>> getAll() {
    List<Label> labels = labelService.getAll();
    List<LabelDTO> labelDTOs = new ArrayList<>();
    for (Label label : labels) {
      LabelDTO userDTO = new LabelDTO (
          label.getId(),
          label.getName(),
          label.getPlaylist()
      );
      labelDTOs.add(userDTO);
    }
    return new ResponseEntity<>(labelDTOs, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<LabelDTO> getById(@PathVariable Integer id) {
    Label label = labelService.getById(id);
    if (label != null) {
      LabelDTO labelDto = new LabelDTO(
          label.getId(),
          label.getName(),
          label.getPlaylist()
      );
      return new ResponseEntity<>(labelDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Label newLabel) {
    labelService.create(newLabel);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT,
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<LabelDTO> update(@PathVariable Integer id,
                                        @RequestBody Label label) {
    Label oldLabel = labelService.getById(id);
    if (oldLabel != null) {
      labelService.update(id, label);
      LabelDTO oldLabelDTO = new LabelDTO(
          label.getId(),
          label.getName(),
          label.getPlaylist()
      );
      return new ResponseEntity<>(oldLabelDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (labelService.getById(id) != null) {
      labelService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
