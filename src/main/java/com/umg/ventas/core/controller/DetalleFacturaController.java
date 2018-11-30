package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.DetalleFacturaRepository;

import com.umg.ventas.core.ies.bo.Compra;
import com.umg.ventas.core.ies.bo.DetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/detalle-factura")
public class DetalleFacturaController {
  @Autowired
  private DetalleFacturaRepository detalleFacturaRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable<DetalleFactura> getAll() {
    return detalleFacturaRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public  ResponseEntity<DetalleFactura> getDetalleFactura(@PathVariable("id") Long id){
    return new ResponseEntity<DetalleFactura>(detalleFacturaRepository.findOne(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) DetalleFactura registro){
    return detalleFacturaRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<DetalleFactura> update(@PathVariable("id") Long id, @RequestBody DetalleFactura registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(detalleFacturaRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DetalleFactura detalleFactura =detalleFacturaRepository.findOne(id);
    detalleFactura.setPrecio(registro.getPrecio());
    detalleFactura.setProducto(registro.getProducto());
    detalleFactura.setCantidad(registro.getCantidad());
    detalleFactura.setSubTotal(registro.getSubTotal());
    detalleFacturaRepository.save(detalleFactura);
    return new ResponseEntity<DetalleFactura>(detalleFactura,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (detalleFacturaRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DetalleFactura detalleFactura = detalleFacturaRepository.findOne(id);
    detalleFacturaRepository.delete(detalleFactura);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }
}


