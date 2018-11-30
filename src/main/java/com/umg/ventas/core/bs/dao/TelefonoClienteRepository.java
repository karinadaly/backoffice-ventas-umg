package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Proveedor;
import com.umg.ventas.core.ies.bo.TelefonoCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TelefonoClienteRepository extends PagingAndSortingRepository<TelefonoCliente,Long> {
}
