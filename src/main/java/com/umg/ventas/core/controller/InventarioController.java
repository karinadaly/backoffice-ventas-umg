package com.umg.ventas.core.controller;


import com.umg.ventas.core.bs.dao.FacturaRepository;
import com.umg.ventas.core.bs.dao.InventarioRepository;
import com.umg.ventas.core.ies.bo.Cliente;
import com.umg.ventas.core.ies.bo.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/inventario")
public class InventarioController {
  @Autowired
  private InventarioRepository inventarioRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable getAll(){
    return inventarioRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) Inventario registro){
    return inventarioRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity<Inventario> update(@PathVariable("id") Long id, @RequestBody Inventario registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(inventarioRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Inventario inventario =inventarioRepository.findOne(id);
    inventario.setSalida(registro.getSalida());
    inventarioRepository.save(inventario);
    return new ResponseEntity<Inventario>(inventario,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (inventarioRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Inventario inventario = inventarioRepository.findOne(id);
    inventarioRepository.delete(inventario);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);

  }
}
