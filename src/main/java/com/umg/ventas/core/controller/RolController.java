package com.umg.ventas.core.controller;

import com.umg.ventas.core.bs.dao.RolRepository;
import com.umg.ventas.core.ies.bo.Rol;
import com.umg.ventas.core.ies.bo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/rol")
public class RolController {
    @Autowired
    private RolRepository rolRepository;

    @RequestMapping(method = RequestMethod.GET)
    public  Iterable<Rol> getAll(){
        return  rolRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Rol> getRol(@PathVariable("id") Long id){
        return  new ResponseEntity<Rol>(rolRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody(required = true) Rol rol){
        return rolRepository.save(rol);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Rol> update(@PathVariable("id") Long id, @RequestBody Rol rol){
        if(id == null || id < 0){
            return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
        }
        Rol rolUpdate = rolRepository.findOne(id);
        if(rolUpdate == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        rolUpdate.setDescripcion(rol.getDescripcion());
        rolUpdate.setNombre(rol.getNombre());
        rolRepository.save(rolUpdate);
        return  new ResponseEntity<Rol>(rolUpdate,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(id == null || id < 0){
            return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
        }
        Rol rol = rolRepository.findOne(id);
        if(rol == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        rolRepository.delete(rol);
        return  new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
    }

}
