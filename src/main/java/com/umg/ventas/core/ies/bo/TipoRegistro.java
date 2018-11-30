package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Table(name = "tipo_registro")
@EqualsAndHashCode(exclude = {"inventario"})
@Entity
public class TipoRegistro implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column (name ="codigo_tipo_registro" )
  private Long codigoTipoRegistro;
  @Column (name = "descripcion")
  private String descripcion;


}
