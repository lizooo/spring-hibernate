package com.iot.dto;

import javax.persistence.Column;

public class CredentialsDTO {
  private Integer id;
  private String userName;
  private String dcryptPassword;
  private String name;
  private String surname;

  public CredentialsDTO(Integer id, String userName, String dcryptPassword, String name, String surname) {
    this.id = id;
    this.userName = userName;
    this.dcryptPassword = dcryptPassword;
    this.name = name;
    this.surname = surname;
  }

  public CredentialsDTO(){
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDcryptPassword() {
    return dcryptPassword;
  }

  public void setDcryptPassword(String dcryptPassword) {
    this.dcryptPassword = dcryptPassword;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}
