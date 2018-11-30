package com.umg.ventas.core.controller;



import com.umg.ventas.core.bs.dao.TipoEmpaqueRepository;

import com.umg.ventas.core.ies.bo.TipoEmpaque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping(value = "/api/v1/tipo-empaque")
public class TipoempaqueController {
@Autowired
private TipoEmpaqueRepository tipoempaqueRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable<TipoEmpaque> getAll(){
    return tipoempaqueRepository.findAll();
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public ResponseEntity<TipoEmpaque> getTipoEmpaque(@PathVariable("id") Long id){
    return  new ResponseEntity<TipoEmpaque>(tipoempaqueRepository.findOne(id),HttpStatus.OK);
  }
  @RequestMapping(method= RequestMethod.POST)
  public Object save(@RequestBody(required = true) TipoEmpaque registro){
    return tipoempaqueRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<TipoEmpaque> update(@PathVariable("id") Long id, @RequestBody TipoEmpaque registro) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (tipoempaqueRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TipoEmpaque tipoempaque = tipoempaqueRepository.findOne(id);
    tipoempaque.setDescripcion(registro.getDescripcion());
    tipoempaqueRepository.save(tipoempaque);
    return new ResponseEntity<TipoEmpaque>(tipoempaque, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(tipoempaqueRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TipoEmpaque tipoempaque = tipoempaqueRepository.findOne(id);
    tipoempaqueRepository.delete(tipoempaque);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }


}


