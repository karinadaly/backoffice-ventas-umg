package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Producto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long> {
    Iterable<Producto> findAllByDescripcionIsContaining(String termino);
}
