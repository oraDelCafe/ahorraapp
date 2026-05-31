package entidades;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    private LocalDate fechaCuenta;

    @Column(name = "CuentaBancoExterna")
    private String cuentaBancoExterna;

    @JoinColumn(name = "CodUsuarios_cuentasapp", referencedColumnName = "CodUsuarios")
    @ManyToOne
    private Usuario codUsuariosCuentasapp;

    @OneToMany(mappedBy = "codCuentasTrans", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<Transferencia> transferenciaCollection;

    @OneToMany(mappedBy = "codCuentasAppDetprog", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<DetalleObjetivoProgramado> detalleObjetivoProgramadoCollection;

    public CuentaApp() {
    }

    public CuentaApp(Integer codCuentasApp) {
        this.codCuentasApp = codCuentasApp;
    }

    public CuentaApp(
            Integer codCuentasApp,
            Double saldoCuentas,
            LocalDate fechaCuenta,
            String cuentaBancoExterna) {
        this.codCuentasApp = codCuentasApp;
        this.saldoCuentas = saldoCuentas;
        this.fechaCuenta = fechaCuenta;
        this.cuentaBancoExterna = cuentaBancoExterna;

        this.transferenciaCollection = new ArrayList<>();
        this.detalleObjetivoProgramadoCollection = new ArrayList<>();
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
        this.saldoCuentas = saldoCuentas;
    }

    public LocalDate getFechaCuenta() {
        return fechaCuenta;
    }

    public void setFechaCuenta(LocalDate fechaCuenta) {
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
