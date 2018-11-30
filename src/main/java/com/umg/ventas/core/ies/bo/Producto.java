package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Set;


@Data
@Entity
@Table(name="productos")
@EqualsAndHashCode(exclude = {"tipoEmpaque", "categoria", "detalleFactura", "inventario"})
public class Producto implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_producto")
  private Long codigoProducto;
  @Column(name = "descripcion")
  private String descripcion;
  @Column(name = "precio_unitario")
  private Double precioUnitario;
  @Column(name = "precio_docena")
  private Double precioDocena;
  @Column(name = "precio_por_mayor")
  private Double precioPorMayor;
  @Column(name = "existencia")
  private int existencia;
  @Column(name = "imagen")
  private String imagen;

  @ManyToOne
  @JoinColumn(name = "codigo_tipo_empaque",referencedColumnName = "codigo_tipo_empaque")
  private TipoEmpaque tipoEmpaque;
  @ManyToOne
  @JoinColumn(name = "codigo_categoria",referencedColumnName = "codigo_categoria")
  private Categoria categoria;
  @OneToMany(mappedBy = "factura")
  @JsonIgnore
  private Set<DetalleFactura> detalleFactura;
  @OneToMany(mappedBy = "compra")
  @JsonIgnore
  private Set<DetalleCompra> detalleCompra;


}
