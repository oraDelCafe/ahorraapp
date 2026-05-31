package entidades;

import java.io.Serializable;
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
@Table(name = "Transferencia")

@NamedQueries({
        @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t"),
        @NamedQuery(name = "Transferencia.findById", query = "SELECT t FROM Transferencia t WHERE t.codTransferencia = :id"),
        @NamedQuery(name = "Transferencia.findByTipo", query = "SELECT t FROM Transferencia t WHERE t.tipoTrans = :tipo"),
        @NamedQuery(name = "Transferencia.findByCodigoTransaccion", query = "SELECT t FROM Transferencia t WHERE t.codigoTransaccion = :codigo")
})

public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodTransferencia")
    private Integer codTransferencia;

    @Basic(optional = false)
    @Column(name = "fechaTrans")
    @Temporal(TemporalType.DATE)
    private Date fechaTrans;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoTrans")
    private TipoTransferencia tipoTrans;

    @Column(name = "cantidadTrans")
    private Double cantidadTrans;

    @Column(name = "conceptoTrans")
    private String conceptoTrans;

    @Column(name = "codigoTransaccion")
    private String codigoTransaccion;

    @JoinColumn(name = "CodCuentas_trans", referencedColumnName = "CodCuentasApp")
    @ManyToOne
    private CuentaApp codCuentasTrans;

    public Transferencia() {
    }

    public Transferencia(Integer codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public Transferencia(
            Date fechaTrans, TipoTransferencia tipoTrans,
            Double cantidadTrans, String conceptoTrans,
            String codigoTransaccion) {
        this.fechaTrans = fechaTrans;
        this.tipoTrans = tipoTrans;
        this.cantidadTrans = cantidadTrans;
        this.conceptoTrans = conceptoTrans;
        this.codigoTransaccion = codigoTransaccion;
    }

    public Integer getCodTransferencia() {
        return codTransferencia;
    }

    public void setCodTransferencia(Integer codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public Date getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaTrans(Date fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public TipoTransferencia getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(TipoTransferencia tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public Double getCantidadTrans() {
        return cantidadTrans;
    }

    public void setCantidadTrans(Double cantidadTrans) {
        this.cantidadTrans = cantidadTrans;
    }

    public String getConceptoTrans() {
        return conceptoTrans;
    }

    public void setConceptoTrans(String conceptoTrans) {
        this.conceptoTrans = conceptoTrans;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public CuentaApp getCodCuentasTrans() {
        return codCuentasTrans;
    }

    public void setCodCuentasTrans(CuentaApp codCuentasTrans) {
        this.codCuentasTrans = codCuentasTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTransferencia != null ? codTransferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        return !((this.codTransferencia == null && other.codTransferencia != null)
                || (this.codTransferencia != null
                        && !this.codTransferencia.equals(other.codTransferencia)));
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "codTransferencia=" + codTransferencia +
                ", fechaTrans=" + fechaTrans +
                ", tipoTrans=" + tipoTrans +
                ", cantidadTrans=" + cantidadTrans +
                ", conceptoTrans=" + conceptoTrans +
                ", codigoTransaccion=" + codigoTransaccion +
                '}';
    }
}