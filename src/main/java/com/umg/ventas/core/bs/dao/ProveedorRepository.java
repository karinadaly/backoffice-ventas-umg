package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Proveedor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProveedorRepository extends PagingAndSortingRepository<Proveedor,Long> {
}
