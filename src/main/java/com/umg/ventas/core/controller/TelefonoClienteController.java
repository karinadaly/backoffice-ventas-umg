package com.umg.ventas.core.controller;


import com.umg.ventas.core.bs.dao.TelefonoClienteRepository;
import com.umg.ventas.core.ies.bo.TelefonoCliente;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/telefono-cliente")

public class TelefonoClienteController {

  @Autowired
  private TelefonoClienteRepository telefonoClienteRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable getAll(){
    return telefonoClienteRepository.findAll();
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<TelefonoCliente> getTelefonoCliente(@PathVariable("id") Long id){
    return new ResponseEntity<TelefonoCliente>(telefonoClienteRepository.findOne(id), HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) TelefonoCliente telefonoCliente){
    return telefonoClienteRepository.save(telefonoCliente);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<TelefonoCliente> update(@PathVariable("id") Long id, @RequestBody TelefonoCliente telefonoCliente){
    if(id == null || id < 0){
      return  new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    TelefonoCliente telefonoClienteUpdate = telefonoClienteRepository.findOne(id);
    if(telefonoClienteUpdate == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    telefonoClienteUpdate.setDescripcion(telefonoCliente.getDescripcion());
    telefonoClienteUpdate.setNumeroTelefono(telefonoCliente.getNumeroTelefono());
    return new ResponseEntity<TelefonoCliente>(telefonoClienteRepository.save(telefonoClienteUpdate),HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id < 0){
      return  new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    TelefonoCliente telefonoCliente = telefonoClienteRepository.findOne(id);
    if(telefonoCliente == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    telefonoClienteRepository.delete(telefonoCliente);
    return  new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }
}
