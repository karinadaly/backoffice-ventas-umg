package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "email_clientes")
@EqualsAndHashCode(exclude = {"cliente"})
public class EmailCliente implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_email")
  private Long codigoEmail;
  @Column(name = "email")
  private String email;
  @Column(name = "descripcion")
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "codigo_cliente",referencedColumnName = "codigo_cliente")
  //@JsonIgnore
  private Cliente cliente;

}
