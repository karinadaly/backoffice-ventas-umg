package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario,Long> {
    Usuario findByUsername(String name);
}
