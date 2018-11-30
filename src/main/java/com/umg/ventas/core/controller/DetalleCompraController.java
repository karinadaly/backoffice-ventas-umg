package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.DetalleCompraRepository;
import com.umg.ventas.core.bs.dao.DetalleFacturaRepository;
import com.umg.ventas.core.ies.bo.Compra;
import com.umg.ventas.core.ies.bo.DetalleCompra;
import com.umg.ventas.core.ies.bo.DetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/detalle-compra")
public class DetalleCompraController {
  @Autowired
  private DetalleCompraRepository detalleCompraRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable<DetalleCompra> getAll() {
    return detalleCompraRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<DetalleCompra> getDetalleCompra(@PathVariable("id") Long id){
    return new ResponseEntity<DetalleCompra>(detalleCompraRepository.findOne(id),HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) DetalleCompra registro){
    return detalleCompraRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<DetalleCompra> update(@PathVariable("id") Long id, @RequestBody DetalleCompra registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(detalleCompraRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DetalleCompra detalleCompra =detalleCompraRepository.findOne(id);
    detalleCompra.setPrecio(registro.getPrecio());
    detalleCompra.setCantidad(registro.getCantidad());
    detalleCompra.setCantidad(registro.getCantidad());
    detalleCompra.setSubTotal(registro.getSubTotal());
    detalleCompraRepository.save(detalleCompra);
    return new ResponseEntity<DetalleCompra>(detalleCompra,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (detalleCompraRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    DetalleCompra detalleCompra = detalleCompraRepository.findOne(id);
    detalleCompraRepository.delete(detalleCompra);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }

}
