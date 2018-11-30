package com.umg.ventas.core.ies.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Table(name = "proveedores")
@Entity
public class Proveedor implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_proveedor")
  private Long codigoProveedor;
  @Column(name = "nit")
  private String nit;
  @Column(name = "razon_social")
  private String razonSocial;
  @Column(name = "paginaWeb")
  private String paginaWeb;
  @Column(name = "contacto_principal")
  private String contactoPrincipal;

}
