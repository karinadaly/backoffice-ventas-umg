package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.TipoEmpaqueRepository;
import com.umg.ventas.core.bs.dao.TipoRegistroRepository;
import com.umg.ventas.core.ies.bo.Compra;
import com.umg.ventas.core.ies.bo.TipoEmpaque;
import com.umg.ventas.core.ies.bo.TipoRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/tipo-registro")
public class TipoRegistroController {
  @Autowired
  private TipoRegistroRepository tipoRegistroRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable<TipoRegistro> getAll(){
    return tipoRegistroRepository.findAll();
  }

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  public ResponseEntity<TipoRegistro> getRegistro(@PathVariable("id") Long id){
    return new ResponseEntity<TipoRegistro>(tipoRegistroRepository.findOne(id),HttpStatus.OK);
  }
  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) TipoRegistro registro){
    return tipoRegistroRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<TipoRegistro> update(@PathVariable("id") Long id, @RequestBody TipoRegistro registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(tipoRegistroRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TipoRegistro tipoRegistro =tipoRegistroRepository.findOne(id);
    tipoRegistro.setCodigoTipoRegistro(registro.getCodigoTipoRegistro());
    tipoRegistro.setDescripcion(registro.getDescripcion());
    tipoRegistroRepository.save(tipoRegistro);
    return new ResponseEntity<TipoRegistro>(tipoRegistro,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    if (id == null || id < 0) {
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if (tipoRegistroRepository.findOne(id) == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    TipoRegistro tipoRegistro = tipoRegistroRepository.findOne(id);
    tipoRegistroRepository.delete(tipoRegistro);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }

}
