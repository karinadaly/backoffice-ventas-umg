package com.umg.ventas.core.controller;


import com.umg.ventas.core.bs.dao.DireccionProveedorRepository;
import com.umg.ventas.core.ies.bo.DireccionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/direccion-proveedor",produces = MediaType.APPLICATION_JSON_VALUE)
public class DireccionProveedorController {
  @Autowired
  private DireccionProveedorRepository direccionProveedorRepository;
  @RequestMapping(method = RequestMethod.GET)
  public Iterable<DireccionProveedor> getAll(){
    return direccionProveedorRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public  ResponseEntity<DireccionProveedor> getDireccionProveedor(@PathVariable("id") Long id){
    return new ResponseEntity<DireccionProveedor>(direccionProveedorRepository.findOne(id),HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object agregar(@RequestBody(required = true) DireccionProveedor direccionProveedor){
    return direccionProveedorRepository.save(direccionProveedor);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
  public ResponseEntity<DireccionProveedor> update(@PathVariable("id") Long id, @RequestBody DireccionProveedor registro){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(direccionProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DireccionProveedor direccionProveedor = direccionProveedorRepository.findOne(id);
    direccionProveedor.setDireccion(registro.getDireccion());
    direccionProveedor.setDescripcion(registro.getDescripcion());
    direccionProveedorRepository.save(direccionProveedor);
    return new ResponseEntity<DireccionProveedor>(direccionProveedor,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id <= 0){
      return new ResponseEntity("{ \"message\" : \"id is required\"}",HttpStatus.CONFLICT);
    }
    if(direccionProveedorRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DireccionProveedor direccionProveedor = direccionProveedorRepository.findOne(id);
    direccionProveedorRepository.delete(direccionProveedor);
    return new ResponseEntity("{ \"message\" : \"delete online-course\"}",HttpStatus.OK);
  }




}
