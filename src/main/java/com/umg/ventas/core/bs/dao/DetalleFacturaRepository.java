package com.umg.ventas.core.bs.dao;


import com.umg.ventas.core.ies.bo.DetalleFactura;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DetalleFacturaRepository extends PagingAndSortingRepository<DetalleFactura, Long> {
}
