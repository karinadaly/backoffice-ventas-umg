package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Table(name = "clientes")
@Entity
@EqualsAndHashCode(exclude = {"emailClientes"})
public class Cliente implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_cliente")
  private Long codigocliente;
  @Column(name = "nit")
  private Long nit;
  @Column(name = "dpi")
  private Long dpi;
  @Column(name = "nombre")
  private String nombre;
  @OneToMany(mappedBy = "cliente")
  @JsonIgnore
  private Set<EmailCliente> emailClientes;
  @OneToMany(mappedBy = "cliente")
  @JsonIgnore
  private Set<Factura> facturas;
}
