package entidades;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CuentasApp")
@NamedQueries({
        @NamedQuery(name = "CuentaApp.findAll", query = "SELECT c FROM CuentaApp c"),
        @NamedQuery(name = "CuentaApp.findById", query = "SELECT c FROM CuentaApp c WHERE c.codCuentasApp = :id")
})
public class CuentaApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodCuentasApp")
    private Integer codCuentasApp;

    @Column(name = "SaldoCuentas")
    private Double saldoCuentas;

    @Column(name = "FechaCuenta")
    private Date fechaCuenta;

    @Column(name = "CuentaBancoExterna")
    private String cuentaBancoExterna;

    @OneToOne
    @JoinColumn(name = "CodUsuarios_cuentasapp", referencedColumnName = "CodUsuarios")
    private Usuario codUsuariosCuentasapp;

    @OneToMany(mappedBy = "codCuentasTrans", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<Transferencia> transferenciaCollection;

    @OneToMany(mappedBy = "codCuentasAppDetprog", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<DetalleObjetivoProgramado> detalleObjetivoProgramadoCollection;

    @OneToMany(mappedBy = "codCuentasAppDetpunt", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<DetalleObjetivoPuntual> detalleObjetivoPuntualCollection;

    public CuentaApp() {
    }

    public CuentaApp(Integer codCuentasApp) {
        this.codCuentasApp = codCuentasApp;
    }

    public CuentaApp(
            Usuario codUsuariosCuentasapp,
            Double saldoCuentas,
            LocalDate fechaCuenta,
            String cuentaBancoExterna) {

        if (saldoCuentas < 0) {

            throw new IllegalArgumentException(
                    "El saldo no puede ser negativo");
        }

        this.saldoCuentas = 0.0;
        this.fechaCuenta = Date.valueOf(fechaCuenta);
        this.cuentaBancoExterna = cuentaBancoExterna;
        this.codUsuariosCuentasapp = codUsuariosCuentasapp;

        this.transferenciaCollection = new ArrayList<>();
        this.detalleObjetivoProgramadoCollection = new ArrayList<>();
        this.detalleObjetivoPuntualCollection = new ArrayList<>();
    }

    public CuentaApp(
            Integer codCuentasApp,
            Double saldoCuentas,
            LocalDate fechaCuenta,
            String cuentaBancoExterna) {

        if (saldoCuentas < 0) {

            throw new IllegalArgumentException(
                    "El saldo no puede ser 0 o negativo");
        }

        this.codCuentasApp = codCuentasApp;
        this.saldoCuentas = 0.0;
        this.fechaCuenta = Date.valueOf(fechaCuenta);
        this.cuentaBancoExterna = cuentaBancoExterna;

        this.transferenciaCollection = new ArrayList<>();
        this.detalleObjetivoProgramadoCollection = new ArrayList<>();
        this.detalleObjetivoPuntualCollection = new ArrayList<>();
    }

    public Integer getCodCuentasApp() {
        return codCuentasApp;
    }

    public void setCodCuentasApp(Integer codCuentasApp) {
        this.codCuentasApp = codCuentasApp;
    }

    public Double getSaldoCuentas() {
        return saldoCuentas;
    }

    public void setSaldoCuentas(Double saldoCuentas) {

        if (saldoCuentas < 0) {

            throw new IllegalArgumentException(
                    "El saldo no puede ser negativo");
        }

        this.saldoCuentas = saldoCuentas;
    }

    /**
     * devuelve Date no LocalDate
     * 
     */
    public Date getFechaCuenta() {
        return fechaCuenta;
    }

    public void setFechaCuenta(Date fechaCuenta) {
        this.fechaCuenta = fechaCuenta;
    }

    public String getCuentaBancoExterna() {
        return cuentaBancoExterna;
    }

    public void setCuentaBancoExterna(String cuentaBancoExterna) {
        this.cuentaBancoExterna = cuentaBancoExterna;
    }

    public Usuario getCodUsuariosCuentasapp() {
        return codUsuariosCuentasapp;
    }

    public void setCodUsuariosCuentasapp(Usuario codUsuariosCuentasapp) {
        this.codUsuariosCuentasapp = codUsuariosCuentasapp;
    }

    public Collection<Transferencia> getTransferenciaCollection() {
        return transferenciaCollection;
    }

    public void setTransferenciaCollection(
            Collection<Transferencia> transferenciaCollection) {

        this.transferenciaCollection = transferenciaCollection;

        for (Transferencia transferencia : transferenciaCollection) {
            transferencia.setCodCuentasTrans(this);
        }
    }

    public Collection<DetalleObjetivoProgramado> getDetalleObjetivoProgramadoCollection() {
        return detalleObjetivoProgramadoCollection;
    }

    public void setDetalleObjetivoProgramadoCollection(
            Collection<DetalleObjetivoProgramado> detalleObjetivoProgramadoCollection) {

        this.detalleObjetivoProgramadoCollection = detalleObjetivoProgramadoCollection;

        for (DetalleObjetivoProgramado detalle : detalleObjetivoProgramadoCollection) {
            detalle.setCodCuentasAppDetprog(this);
        }
    }

    public void addTransferencia(Transferencia transferencia) {
        this.transferenciaCollection.add(transferencia);
        transferencia.setCodCuentasTrans(this);
    }

    public void removeTransferencia(Transferencia transferencia) {
        this.transferenciaCollection.remove(transferencia);
        transferencia.setCodCuentasTrans(null);
    }

    public void addDetalleObjetivoProgramado(
            DetalleObjetivoProgramado detalle) {

        this.detalleObjetivoProgramadoCollection.add(detalle);
        detalle.setCodCuentasAppDetprog(this);
    }

    public void removeDetalleObjetivoProgramado(
            DetalleObjetivoProgramado detalle) {

        this.detalleObjetivoProgramadoCollection.remove(detalle);
        detalle.setCodCuentasAppDetprog(null);
    }

    public Collection<DetalleObjetivoPuntual> getDetalleObjetivoPuntualCollection() {
        return detalleObjetivoPuntualCollection;
    }

    public void setDetalleObjetivoPuntualCollection(
            Collection<DetalleObjetivoPuntual> detalleObjetivoPuntualCollection) {

        this.detalleObjetivoPuntualCollection = detalleObjetivoPuntualCollection;

        for (DetalleObjetivoPuntual detalle : detalleObjetivoPuntualCollection) {
            detalle.setCodCuentasAppDetpunt(this);
        }
    }

    public void addDetalleObjetivoPuntual(
            DetalleObjetivoPuntual detalle) {

        this.detalleObjetivoPuntualCollection.add(detalle);
        detalle.setCodCuentasAppDetpunt(this);
    }

    public void removeDetalleObjetivoPuntual(
            DetalleObjetivoPuntual detalle) {

        this.detalleObjetivoPuntualCollection.remove(detalle);
        detalle.setCodCuentasAppDetpunt(null);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCuentasApp != null ? codCuentasApp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CuentaApp)) {
            return false;
        }

        CuentaApp other = (CuentaApp) object;

        return !((this.codCuentasApp == null && other.codCuentasApp != null)
                || (this.codCuentasApp != null
                        && !this.codCuentasApp.equals(other.codCuentasApp)));
    }

    @Override
    public String toString() {
        return "CuentaApp{" +
                "codCuentasApp=" + codCuentasApp +
                ", saldoCuentas=" + saldoCuentas +
                ", fechaCuenta=" + fechaCuenta +
                ", cuentaBancoExterna=" + cuentaBancoExterna +
                '}';
    }

}
