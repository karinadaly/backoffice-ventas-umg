package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.DireccionClienteRepository;
import com.umg.ventas.core.ies.bo.DireccionCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/direccion-cliente",produces = MediaType.APPLICATION_JSON_VALUE)
public class DireccionClienteController {
  @Autowired
  private DireccionClienteRepository direccionClienteRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<DireccionCliente> getAll(){
    return direccionClienteRepository.findAll();
  }

  @RequestMapping(value = "/{id}")
  public ResponseEntity<DireccionCliente> getDireccionCliente(@PathVariable("id") Long id){
    return  new ResponseEntity<DireccionCliente>(direccionClienteRepository.findOne(id),HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object agregar(@RequestBody(required = true) DireccionCliente direccionCliente){
    return direccionClienteRepository.save(direccionCliente);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
  public ResponseEntity<DireccionCliente> update(@PathVariable("id") Long id, @RequestBody DireccionCliente registro){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(direccionClienteRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DireccionCliente direccionCliente = direccionClienteRepository.findOne(id);
    direccionCliente.setDireccion(registro.getDireccion());
    direccionCliente.setDescripcion(registro.getDescripcion());
    direccionCliente.setCliente(registro.getCliente());
    direccionClienteRepository.save(direccionCliente);
    return new ResponseEntity<DireccionCliente>(direccionCliente,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(direccionClienteRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DireccionCliente direccionCliente = direccionClienteRepository.findOne(id);
    direccionClienteRepository.delete(direccionCliente);
    return new ResponseEntity("{ \"message\" : \"delete online-course\"}",HttpStatus.OK);
  }
}
