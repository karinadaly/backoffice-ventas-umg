package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.EmailClienteRepository;
import com.umg.ventas.core.ies.bo.EmailCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/email-cliente",produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailClienteController {
  @Autowired
  private EmailClienteRepository emailClienteRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<EmailCliente> getAll(){
    return emailClienteRepository.findAll();
  }

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public ResponseEntity<EmailCliente> getEmailCliente(@PathVariable("id") Long id){
    return new ResponseEntity<EmailCliente>(emailClienteRepository.findOne(id),HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object agregar(@RequestBody(required = true) EmailCliente emailCliente){
    return emailClienteRepository.save(emailCliente);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.POST)
  public ResponseEntity<EmailCliente> update(@PathVariable("id") Long id, @RequestBody EmailCliente registro){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(emailClienteRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    EmailCliente emailCliente = emailClienteRepository.findOne(id);
    emailCliente.setEmail(registro.getEmail());
    emailCliente.setDescripcion(registro.getDescripcion());
    emailCliente.setCliente(registro.getCliente());
    emailClienteRepository.save(emailCliente);
    return new ResponseEntity<EmailCliente>(emailCliente,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(emailClienteRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    EmailCliente emailCliente = emailClienteRepository.findOne(id);
    emailClienteRepository.delete(emailCliente);
    return new ResponseEntity("{ \"message\" : \"delete online-course\"}",HttpStatus.OK);
  }
}
