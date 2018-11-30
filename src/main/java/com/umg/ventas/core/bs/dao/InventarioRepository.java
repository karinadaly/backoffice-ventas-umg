package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Inventario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InventarioRepository extends PagingAndSortingRepository<Inventario, Long> {
}
