package com.umg.ventas.core.bs.dao;

import com.umg.ventas.core.ies.bo.Categoria;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,Long> {
}
