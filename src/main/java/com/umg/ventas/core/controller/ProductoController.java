package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.FacturaRepository;
import com.umg.ventas.core.bs.dao.ProductoRepository;
import com.umg.ventas.core.ies.bo.Compra;
import com.umg.ventas.core.ies.bo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/producto")
public class ProductoController  {
  @Autowired
  private ProductoRepository productoRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable getAll(@RequestParam( value =  "termino", required = false) String termino){
    if(termino == null || termino.length() == 0 ){
      return productoRepository.findAll();
    }
    return productoRepository.findAllByDescripcionIsContaining(termino);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public  ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
    return  new ResponseEntity<Producto>(productoRepository.findOne(id),HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) Producto registro){
    return productoRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Producto> update(@PathVariable("id") Long id, @RequestBody Producto registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(productoRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Producto producto =productoRepository.findOne(id);
    producto.setDescripcion(registro.getDescripcion());
    producto.setPrecioUnitario(registro.getPrecioUnitario());
    producto.setPrecioDocena(registro.getPrecioDocena());
    producto.setPrecioPorMayor(registro.getPrecioPorMayor());
    producto.setExistencia(registro.getExistencia());
    producto.setImagen(registro.getImagen());
    productoRepository.save(producto);
    return new ResponseEntity<Producto>(producto,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (productoRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Producto producto = productoRepository.findOne(id);
    productoRepository.delete(producto);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }


}
