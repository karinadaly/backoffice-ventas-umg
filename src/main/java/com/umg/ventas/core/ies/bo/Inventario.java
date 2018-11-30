package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="inventarios")
@EqualsAndHashCode(exclude = {"producto","tipoRegistro","codigoTipoRegistro"})
public class Inventario implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "correlativo")
  private Long corelativoa;
  @Column(name = "salidas")
  private int salida;
  @Column(name = "entrada")
  private int entrada;
  @Column(name = "precio")
  private Double precio;
  @Column(name = "fecha")
  private Date fecha;

  @ManyToOne
  @JoinColumn(name = "codigo_producto",referencedColumnName = "codigo_producto")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "codigo_tipo_registro",referencedColumnName = "codigo_tipo_registro")
  private TipoRegistro codigoTipoRegistro;




}
