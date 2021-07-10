/*
 * Copyright (c) 2021 Usuario.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Usuario - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.matricula.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Representa a la clave primaria de la tabla MATRICULA
 * @author Usuario
 */
@Entity
@Table(name = "matricula", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cod_estudiante", "cod_periodo"})})

public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "cod_matricula", nullable = false)
    private Integer codigo;
 
    @Column(name = "cod_estudiante", nullable = false)
    private Integer codigoEstudiante;
    
    @Column(name = "cod_periodo", nullable = false)
    private Integer codigoPeriodo;
    
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
 
    @Column(name = "creditos_totales", nullable = false, precision = 8, scale = 2)
    private BigDecimal creditosTotales;
    
    @OneToMany(mappedBy = "matricula")
    private List<DetalleMatricula> detalle;
    
    @JoinColumn(name = "cod_estudiante", referencedColumnName = "cod_estudiante", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Estudiante estudiante;
    
    @JoinColumn(name = "cod_periodo", referencedColumnName = "cod_periodo", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Periodo periodo;

    public Matricula() {
    }

    public Matricula(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(Integer codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public Integer getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(Integer codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCreditosTotales() {
        return creditosTotales;
    }

    public void setCreditosTotales(BigDecimal creditosTotales) {
        this.creditosTotales = creditosTotales;
    }

    public List<DetalleMatricula> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleMatricula> detalle) {
        this.detalle = detalle;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Matricula[ codigoMatricula=" + codigo + " ]";
    }
    
}
