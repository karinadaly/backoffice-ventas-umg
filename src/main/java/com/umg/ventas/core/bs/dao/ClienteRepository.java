package com.umg.ventas.core.bs.dao;


import com.umg.ventas.core.ies.bo.Cliente;
import com.umg.ventas.core.ies.bo.DireccionCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Long> {
}
