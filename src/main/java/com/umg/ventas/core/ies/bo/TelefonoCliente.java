package com.umg.ventas.core.ies.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "telefono_clientes")
@EqualsAndHashCode(exclude = {"cliente"})
public class TelefonoCliente implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_telefono")
  private Long codigoTelefono;
  @Column(name = "numero_telefono")
  private String numeroTelefono;
  @Column(name = "descripcion")
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "codigo_cliente",referencedColumnName = "codigo_cliente")
  //@JsonIgnore
  private Cliente cliente;

}

