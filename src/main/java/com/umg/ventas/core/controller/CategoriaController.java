package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.CategoriaRepository;
import com.umg.ventas.core.ies.bo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/categoria")
public class CategoriaController {
  @Autowired
  private CategoriaRepository categoriaRepository;

  @RequestMapping(method = RequestMethod.GET)
  public Iterable<Categoria> getAll(){
    return categoriaRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Categoria> getCategoria(@PathVariable("id") Long id){
    return new ResponseEntity<Categoria>(categoriaRepository.findOne(id), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public Object save(@RequestBody(required = true) Categoria registro){
    return categoriaRepository.save(registro);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Categoria> update(@PathVariable("id") Long id, @RequestBody Categoria registro){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(categoriaRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Categoria categoria =categoriaRepository.findOne(id);
    categoria.setDescripcion(registro.getDescripcion());
    categoriaRepository.save(categoria);
    return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
  }
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id){
    if(id == null || id < 0 ){
      return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
    }
    if(categoriaRepository.findOne(id) == null){
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    Categoria categoria = categoriaRepository.findOne(id);
    categoriaRepository.delete(categoria);
    return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
  }



}
