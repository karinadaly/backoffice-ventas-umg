package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "telefono_proveedores")
@EqualsAndHashCode(exclude = {"proveedores"})
public class TelefonoProveedor implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_telefono")
  private Long codigoTelefono;
  @Column(name = "numero_telefono")
  private String numeroTelefono;
  @Column(name = "descripcion")
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "codigo_proveedor",referencedColumnName = "codigo_proveedor")
  //@JsonIgnore
  private Proveedor proveedor;



}
