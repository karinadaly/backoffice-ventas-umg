package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="compras")
@EqualsAndHashCode(exclude = {"proveedor","detalleCompras"})
public class Compra implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "numero_documento")
  private Long numeroDocumento;
  @Temporal(TemporalType.DATE)
  @Column(name = "fecha")
  private Date fecha;
  @Column(name = "total_compra")
  private Double totalCompra;
  @Column(name = "nombre_usuario")
  private String nombreUsuario;
  @ManyToOne
  @JoinColumn(name = "codigo_proveedor",referencedColumnName = "codigo_proveedor")
  private Proveedor proveedor;
  @OneToMany(mappedBy = "compra")
  private Set<DetalleCompra> detalleCompras;



}
