package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.EmailCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmailClienteRepository extends PagingAndSortingRepository<EmailCliente,Long> {
}
