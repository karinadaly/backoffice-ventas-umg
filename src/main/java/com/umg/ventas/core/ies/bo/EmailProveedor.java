package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "email_proveedores")
@EqualsAndHashCode(exclude = {"proveedor"})
public class EmailProveedor implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_email")
  private Long codigoEmail;
  @Column(name = "email")
  private String email;
  @Column(name = "descripcion")
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "codigo_proveedor",referencedColumnName = "codigo_proveedor")
  //@JsonIgnore
  private Proveedor proveedor;



}
