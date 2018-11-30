package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="detalle_compras")
@EqualsAndHashCode(exclude = {"compra", "producto"})
public class DetalleCompra implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_detalle_compra")
  private Long codigoDetalleCompra;
  @Column(name = "cantidad")
  private int cantidad;
  @Column(name = "precio")
  private Double precio;
  @Column(name = "sub_total")
  private Double subTotal;
  @ManyToOne
  @JoinColumn(name = "numero_documento",referencedColumnName = "numero_documento")
  @JsonIgnore
  private Compra compra;
  @ManyToOne
  @JoinColumn(name= "codigo_producto",referencedColumnName = "codigo_producto")
  private Producto producto;

}
