package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.DetalleCompra;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DetalleCompraRepository extends PagingAndSortingRepository<DetalleCompra, Long> {
}
