package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "direccion_proveedores")
@EqualsAndHashCode(exclude = {"proveedor"})
public class DireccionProveedor implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_direccion")
  private Long codigoDireccion;
  @Column(name = "direccion")
  private String direccion;
  @Column(name = "descripcion")
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "codigo_proveedor",referencedColumnName = "codigo_proveedor")
  //@JsonIgnore
  private Proveedor proveedor;


}
