package com.umg.ventas.core.controller;
import com.umg.ventas.core.bs.dao.RolRepository;
import com.umg.ventas.core.bs.dao.UsuarioRepository;
import com.umg.ventas.core.ies.bo.Rol;
import com.umg.ventas.core.ies.bo.Usuario;
import org.hibernate.mapping.UnionSubclass;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @RequestMapping(method = RequestMethod.GET)
    public  Iterable<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id){
        return new ResponseEntity<Usuario>(usuarioRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody(required = true) Usuario usuario){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        if( id == null || id < 0){
            return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
        }
        Usuario usuarioUpdate = usuarioRepository.findOne(id);
        if(usuarioUpdate == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        usuarioUpdate.setNombre(usuario.getNombre());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuarioUpdate.setPassword(hashedPassword);
        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioUpdate.setUsername(usuario.getUsername());
        Rol rol = rolRepository.findOne(usuario.getRol().getCodigoRol());
        usuarioUpdate.setRol(rol);
        return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if( id == null || id < 0){
            return new ResponseEntity("{ \"message\" : \"Debe enviar un id valido\"}", HttpStatus.CONFLICT);
        }
        Usuario usuario = usuarioRepository.findOne(id);
        if(usuario == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        usuarioRepository.delete(id);
        return new ResponseEntity("{ \"message\" : \"Registro eliminado\"}", HttpStatus.OK);
    }
}
