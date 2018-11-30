package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.CompraRepository;
import com.umg.ventas.core.bs.dao.FacturaRepository;
import com.umg.ventas.core.ies.bo.Cliente;
import com.umg.ventas.core.ies.bo.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/compra")
public class CompraController {
  @Autowired
  private CompraRepository compraRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable getAll(){
    return compraRepository.findAll();
  }

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public  ResponseEntity<Compra> getCompra(@PathVariable("id") Long id){
    return new ResponseEntity<Compra>(compraRepository.findOne(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) Compra registro){
    return compraRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<Compra> update(@PathVariable("id") Long id, @RequestBody Compra registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(compraRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Compra compra =compraRepository.findOne(id);
    compra.setFecha(registro.getFecha());
    compra.setTotalCompra(registro.getTotalCompra());
    compra.setNombreUsuario(registro.getNombreUsuario());
    compraRepository.save(compra);
    return new ResponseEntity<Compra>(compra,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (compraRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Compra compra = compraRepository.findOne(id);
    compraRepository.delete(compra);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }
}
