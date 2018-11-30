package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Factura;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FacturaRepository extends PagingAndSortingRepository<Factura,Long> {
}
