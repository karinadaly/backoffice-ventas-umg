package com.umg.ventas.core.ies.bo;


import lombok.Data;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tipo_empaques")
@Entity
public class TipoEmpaque implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column (name ="codigo_tipo_empaque" )
  private Long codigoTipoEmpaque;
  @Column (name = "descripcion")
  private String descripcion;

}




