package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.FacturaRepository;
import com.umg.ventas.core.ies.bo.Cliente;
import com.umg.ventas.core.ies.bo.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/factura")
public class FacturaController {
  @Autowired
  private FacturaRepository facturaRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable getAll(){
    return facturaRepository.findAll();
  }

  @RequestMapping(value = "/{id}")
  private ResponseEntity<Factura> getFactura(@PathVariable("id") Long id){
    return new ResponseEntity<Factura>(facturaRepository.findOne(id), HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) Factura registro){
    return facturaRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<Factura> update(@PathVariable("id") Long id, @RequestBody Factura registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(facturaRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Factura factura =facturaRepository.findOne(id);
    factura.setNumeroFactura(registro.getNumeroFactura());
    factura.setSerie(registro.getSerie());
    factura.setFechaFactura(registro.getFechaFactura());
    factura.setTotal(registro.getTotal());
    facturaRepository.save(factura);
    return new ResponseEntity<Factura>(factura,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (facturaRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Factura factura = facturaRepository.findOne(id);
    facturaRepository.delete(factura);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);

  }

}
