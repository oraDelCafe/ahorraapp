package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "detalleObjetivoProgramados")

@NamedQueries({
        @NamedQuery(name = "DetalleObjetivoProgramado.findAll", query = "SELECT d FROM DetalleObjetivoProgramado d"),
        @NamedQuery(name = "DetalleObjetivoProgramado.findById", query = "SELECT d FROM DetalleObjetivoProgramado d WHERE d.codDetalleObjetivosDetprog = :id")
})

public class DetalleObjetivoProgramado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodDetalleObjetivos_detprog")
    private Integer codDetalleObjetivosDetprog;

    @Column(name = "fechaini_detprog")
    @Temporal(TemporalType.DATE)
    private Date fechaIniDetprog;

    @Column(name = "cantidadFija_detprog")
    private Double cantidadFijaDetprog;

    @Column(name = "fechainactividad_detprog")
    @Temporal(TemporalType.DATE)
    private Date fechaInactividadDetprog;

    @Column(name = "fechafin_detprog")
    @Temporal(TemporalType.DATE)
    private Date fechaFinDetprog;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicidad_detprog")
    private TipoPeriodicidad periodicidadDetprog;
    @JoinColumn(name = "CodCuentasApp_detprog", referencedColumnName = "CodCuentasApp")
    @ManyToOne(optional = false)
    private CuentaApp codCuentasAppDetprog;

    @JoinColumn(name = "CodObjetivos_detprog", referencedColumnName = "CodObjetivos")
    @ManyToOne(optional = false)
    private Objetivo codObjetivosDetprog;

    public DetalleObjetivoProgramado() {
    }

    public DetalleObjetivoProgramado(CuentaApp codCuentasAppDetprog, Integer codDetalleObjetivosDetprog,
            Objetivo codObjetivosDetprog) {
        this.codCuentasAppDetprog = codCuentasAppDetprog;
        this.codDetalleObjetivosDetprog = codDetalleObjetivosDetprog;
        this.codObjetivosDetprog = codObjetivosDetprog;
    }

    public DetalleObjetivoProgramado(Integer codDetalleObjetivosDetprog) {
        this.codDetalleObjetivosDetprog = codDetalleObjetivosDetprog;
    }

    public DetalleObjetivoProgramado(
            Integer codDetalleObjetivosDetprog, LocalDate fechaIniDetprog,
            Double cantidadFijaDetprog,  LocalDate fechaFinDetprog,
            TipoPeriodicidad periodicidadDetprog, CuentaApp codCuentasAppDetprog, 
            Objetivo codObjetivosDetprog) {
        this.codDetalleObjetivosDetprog = codDetalleObjetivosDetprog;
        this.fechaIniDetprog = java.sql.Date.valueOf(fechaIniDetprog);
        this.cantidadFijaDetprog = cantidadFijaDetprog;
        this.fechaInactividadDetprog = null;
        this.fechaFinDetprog = java.sql.Date.valueOf(fechaFinDetprog);
        this.periodicidadDetprog = periodicidadDetprog;
        this.codCuentasAppDetprog = codCuentasAppDetprog;
        this.codObjetivosDetprog = codObjetivosDetprog;
    }

    public DetalleObjetivoProgramado(
            LocalDate fechaIniDetprog, Double cantidadFijaDetprog, LocalDate fechaFinDetprog,
            TipoPeriodicidad periodicidadDetprog, CuentaApp codCuentasAppDetprog,
            Objetivo codObjetivosDetprog) {
        this.fechaIniDetprog = java.sql.Date.valueOf(fechaIniDetprog);
        this.cantidadFijaDetprog = cantidadFijaDetprog;
        this.fechaInactividadDetprog = null;
        this.fechaFinDetprog = java.sql.Date.valueOf(fechaFinDetprog);
        this.periodicidadDetprog = periodicidadDetprog;
        this.codCuentasAppDetprog = codCuentasAppDetprog;
        this.codObjetivosDetprog = codObjetivosDetprog;
    }

    

    public Integer getCodDetalleObjetivosDetprog() {
        return codDetalleObjetivosDetprog;
    }

    public void setCodDetalleObjetivosDetprog(Integer codDetalleObjetivosDetprog) {
        this.codDetalleObjetivosDetprog = codDetalleObjetivosDetprog;
    }

    public Date getFechaIniDetprog() {
        return fechaIniDetprog;
    }

    public void setFechaIniDetprog(Date fechaIniDetprog) {
        this.fechaIniDetprog = fechaIniDetprog;
    }

    public Double getCantidadFijaDetprog() {
        return cantidadFijaDetprog;
    }

    public void setCantidadFijaDetprog(Double cantidadFijaDetprog) {
        this.cantidadFijaDetprog = cantidadFijaDetprog;
    }

    public Date getFechaInactividadDetprog() {
        return fechaInactividadDetprog;
    }

    public void setFechaInactividadDetprog(LocalDate fechaInactividadDetprog) {
        this.fechaInactividadDetprog = java.sql.Date.valueOf(fechaInactividadDetprog); 
    }

    public Date getFechaFinDetprog() {
        return fechaFinDetprog;
    }

    public void setFechaFinDetprog(LocalDate fechaFinDetprog) {
        this.fechaFinDetprog = java.sql.Date.valueOf(fechaFinDetprog);
    }

    public TipoPeriodicidad getPeriodicidadDetprog() {
        return periodicidadDetprog;
    }

    public void setPeriodicidadDetprog(TipoPeriodicidad periodicidadDetprog) {
        this.periodicidadDetprog = periodicidadDetprog;
    }

    public CuentaApp getCodCuentasAppDetprog() {
        return codCuentasAppDetprog;
    }

    public void setCodCuentasAppDetprog(CuentaApp codCuentasAppDetprog) {
        this.codCuentasAppDetprog = codCuentasAppDetprog;
    }

    public Objetivo getCodObjetivosDetprog() {
        return codObjetivosDetprog;
    }

    public void setCodObjetivosDetprog(Objetivo codObjetivosDetprog) {
        this.codObjetivosDetprog = codObjetivosDetprog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDetalleObjetivosDetprog != null
                ? codDetalleObjetivosDetprog.hashCode()
                : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetalleObjetivoProgramado)) {
            return false;
        }
        DetalleObjetivoProgramado other = (DetalleObjetivoProgramado) object;
        return !((this.codDetalleObjetivosDetprog == null
                && other.codDetalleObjetivosDetprog != null)
                || (this.codDetalleObjetivosDetprog != null
                        && !this.codDetalleObjetivosDetprog.equals(other.codDetalleObjetivosDetprog)));
    }

    @Override
    public String toString() {
        return "DetalleObjetivoProgramado{" +
                "codDetalleObjetivosDetprog=" + codDetalleObjetivosDetprog +
                ", fechaIniDetprog=" + fechaIniDetprog +
                ", cantidadFijaDetprog=" + cantidadFijaDetprog +
                ", fechaInactividadDetprog=" + fechaInactividadDetprog +
                ", fechaFinDetprog=" + fechaFinDetprog +
                ", periodicidadDetprog=" + periodicidadDetprog +
                '}';
    }
}