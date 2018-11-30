package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="detalle_factura")
@EqualsAndHashCode(exclude = {"factura","producto"})
public class DetalleFactura implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_factura_detalle")
  private Long codigoFacturaDetalle;
  @Column(name = "precio")
  private Double precio;
  @Column(name = "cantidad")
  private int cantidad;
  @Column(name = "sub_total")
  private Double subTotal;
  @ManyToOne
  @JoinColumn(name = "codigo_factura",referencedColumnName = "codigo_factura")
  @JsonIgnore
  private Factura factura;
  @ManyToOne
  @JoinColumn(name = "codigo_producto",referencedColumnName = "codigo_producto")
  private Producto producto;

}
