package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "detalleObjetivoPuntual")
@NamedQueries({
    @NamedQuery(name = "DetalleObjetivoPuntual.findAll",
            query = "SELECT d FROM DetalleObjetivoPuntual d"),
    @NamedQuery(name = "DetalleObjetivoPuntual.findById",
            query = "SELECT d FROM DetalleObjetivoPuntual d WHERE d.codDetalleObjetivoPuntual = :id")
})
public class DetalleObjetivoPuntual implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodDetalleObjetivoPuntual")
    private Integer codDetalleObjetivoPuntual;

    @Basic(optional = false)
    @Column(name = "cantidad_detpunt")
    private Double cantidadDetpunt;

    @Basic(optional = false)
    @Column(name = "fecha_detpunt")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaDetpunt;

    @JoinColumn(name = "CodCuentasApp_detpunt", referencedColumnName = "CodCuentasApp")
    @ManyToOne(optional = false)
    private CuentaApp codCuentasAppDetpunt;

    @JoinColumn(name = "CodObjetivos_detpunt", referencedColumnName = "CodObjetivos")
    @ManyToOne(optional = false)
    private Objetivo codObjetivosDetpunt;

    public DetalleObjetivoPuntual() {
    }

    public DetalleObjetivoPuntual(Integer codDetalleObjetivoPuntual) {
        this.codDetalleObjetivoPuntual = codDetalleObjetivoPuntual;
    }

    public DetalleObjetivoPuntual(
            Double cantidadDetpunt,
            LocalDateTime fechaDetpunt,
            CuentaApp codCuentasAppDetpunt,
            Objetivo codObjetivosDetpunt) {

        this.cantidadDetpunt = cantidadDetpunt;
        this.fechaDetpunt = java.util.Date.from(
                fechaDetpunt.atZone(ZoneId.systemDefault()).toInstant());
        this.codCuentasAppDetpunt = codCuentasAppDetpunt;
        this.codObjetivosDetpunt = codObjetivosDetpunt;
    }

    public Integer getCodDetalleObjetivoPuntual() {
        return codDetalleObjetivoPuntual;
    }

    public void setCodDetalleObjetivoPuntual(Integer codDetalleObjetivoPuntual) {
        this.codDetalleObjetivoPuntual = codDetalleObjetivoPuntual;
    }

    public Double getCantidadDetpunt() {
        return cantidadDetpunt;
    }

    public void setCantidadDetpunt(Double cantidadDetpunt) {
        this.cantidadDetpunt = cantidadDetpunt;
    }

    public java.util.Date getFechaDetpunt() {
        return fechaDetpunt;
    }

    public void setFechaDetpunt(java.util.Date fechaDetpunt) {
        
        this.fechaDetpunt = fechaDetpunt;
    }

    public void setFechaDetpunt(LocalDateTime fechaDetpunt) {
        this.fechaDetpunt = java.util.Date.from(
                fechaDetpunt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public CuentaApp getCodCuentasAppDetpunt() {
        return codCuentasAppDetpunt;
    }

    public void setCodCuentasAppDetpunt(CuentaApp codCuentasAppDetpunt) {
        this.codCuentasAppDetpunt = codCuentasAppDetpunt;
    }

    public Objetivo getCodObjetivosDetpunt() {
        return codObjetivosDetpunt;
    }

    public void setCodObjetivosDetpunt(Objetivo codObjetivosDetpunt) {
        this.codObjetivosDetpunt = codObjetivosDetpunt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDetalleObjetivoPuntual != null
                ? codDetalleObjetivoPuntual.hashCode()
                : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof DetalleObjetivoPuntual)) {
            return false;
        }

        DetalleObjetivoPuntual other = (DetalleObjetivoPuntual) object;

        return !((this.codDetalleObjetivoPuntual == null
                && other.codDetalleObjetivoPuntual != null)
                || (this.codDetalleObjetivoPuntual != null
                && !this.codDetalleObjetivoPuntual.equals(
                        other.codDetalleObjetivoPuntual)));
    }

    @Override
    public String toString() {
        return "DetalleObjetivoPuntual{"
                + "codDetalleObjetivoPuntual=" + codDetalleObjetivoPuntual
                + ", cantidadDetpunt=" + cantidadDetpunt
                + ", fechaDetpunt=" + fechaDetpunt
                + ", codCuentasAppDetpunt=" + codCuentasAppDetpunt.getCodCuentasApp()
                + ", codObjetivosDetpunt=" + codObjetivosDetpunt.getCodObjetivos()
                + '}';
    }
}
