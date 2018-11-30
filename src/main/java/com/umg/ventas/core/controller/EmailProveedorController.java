package com.umg.ventas.core.controller;


import com.umg.ventas.core.bs.dao.EmailProveedorRepository;
import com.umg.ventas.core.ies.bo.EmailCliente;
import com.umg.ventas.core.ies.bo.EmailProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/email-proveedor",produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailProveedorController {
  @Autowired
  private EmailProveedorRepository emailProveedorRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<EmailProveedor> getAll(){
    return emailProveedorRepository.findAll();
  }

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public ResponseEntity<EmailProveedor> getEmailProveedor(@PathVariable("id") Long id){
    return  new ResponseEntity<EmailProveedor>(emailProveedorRepository.findOne(id),HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object agregar(@RequestBody(required = true) EmailProveedor emailProveedor){
    System.out.println(emailProveedor);
    return emailProveedorRepository.save(emailProveedor);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
  public ResponseEntity<EmailProveedor> update(@PathVariable("id") Long id, @RequestBody EmailProveedor emailProveedor){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}", HttpStatus.CONFLICT);
    }
    if(emailProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    EmailProveedor emailProveedor1 = emailProveedorRepository.findOne(id);
    emailProveedor.setEmail(emailProveedor.getEmail());
    emailProveedor.setDescripcion(emailProveedor.getDescripcion());
    emailProveedor.setProveedor(emailProveedor.getProveedor());
    emailProveedorRepository.save(emailProveedor);
    return new ResponseEntity<EmailProveedor>(emailProveedor,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(emailProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    EmailProveedor emailProveedor = emailProveedorRepository.findOne(id);
    emailProveedorRepository.delete(emailProveedor);
    return new ResponseEntity("{ \"message\" : \"delete online-course\"}",HttpStatus.OK);
  }



}
