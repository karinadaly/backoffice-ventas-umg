package com.umg.ventas.core.ies.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "categorias")
@Entity
public class Categoria implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_categoria")
  private Long codigoCategoria;
  @Column(name = "descripcion")
  private String descripcion;

}
