package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.EmailClienteRepository;
import com.umg.ventas.core.bs.dao.TelefonoProveedorRepository;
import com.umg.ventas.core.ies.bo.EmailCliente;
import com.umg.ventas.core.ies.bo.TelefonoProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/telefono-proveedor")
public class TelefonoProveedorController {
  @Autowired
  private TelefonoProveedorRepository telefonoProveedorRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<TelefonoProveedor> getAll(){
    return telefonoProveedorRepository.findAll();
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<TelefonoProveedor> getTelefonoProveedor(@PathVariable("id") Long id){
    return new ResponseEntity<TelefonoProveedor>(telefonoProveedorRepository.findOne(id),HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object agregar(@RequestBody(required = true) TelefonoProveedor telefonoProveedor){
    System.out.println(telefonoProveedor);
    return telefonoProveedorRepository.save(telefonoProveedor);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
  public ResponseEntity<TelefonoProveedor> update(@PathVariable("id") Long id, @RequestBody TelefonoProveedor telefonoProveedor){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}", HttpStatus.CONFLICT);
    }
    if(telefonoProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TelefonoProveedor telefonoProveedor1 = telefonoProveedorRepository.findOne(id);
    telefonoProveedor.setNumeroTelefono(telefonoProveedor.getNumeroTelefono());
    telefonoProveedor.setDescripcion(telefonoProveedor.getDescripcion());
    telefonoProveedorRepository.save(telefonoProveedor);
    return new ResponseEntity<TelefonoProveedor>(telefonoProveedor,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(telefonoProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TelefonoProveedor telefonoProveedor = telefonoProveedorRepository.findOne(id);
    telefonoProveedorRepository.delete(telefonoProveedor);
    return new ResponseEntity("{ \"message\" : \"delete online-course\"}",HttpStatus.OK);
  }






}
