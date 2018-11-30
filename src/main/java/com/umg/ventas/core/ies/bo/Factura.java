package com.umg.ventas.core.ies.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="facturas")
@EqualsAndHashCode(exclude = {"cliente","detalleFactura"})
public class Factura implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo_factura")
  private Long codigoFactura;
  @Column(name = "numero_factura")
  private Long numeroFactura;
  @Column(name = "serie")
  private String serie;
  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_factura")
  private Date fechaFactura;
  @Column(name = "total")
  private Double total;
  @ManyToOne
  @JoinColumn(name = "codigo_cliente",referencedColumnName = "codigo_cliente")
  private Cliente cliente;
  @OneToMany(mappedBy = "factura")
  private Set<DetalleFactura> detalleFactura;
}
