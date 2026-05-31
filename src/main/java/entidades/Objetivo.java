package entidades;

import java.io.Serializable;
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
@Table(name = "Objetivos")
@NamedQueries({
        @NamedQuery(name = "Objetivo.findAll", query = "SELECT o FROM Objetivo o"),
        @NamedQuery(name = "Objetivo.findById", query = "SELECT o FROM Objetivo o WHERE o.codObjetivos = :id"),
        @NamedQuery(name = "Objetivo.findByNombre", query = "SELECT o FROM Objetivo o WHERE o.nombreObj = :nombre")
})
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodObjetivos")
    private Integer codObjetivos;

    @Column(name = "nombreObj")
    private String nombreObj;

    @Column(name = "cantidadObj")
    private Double cantidadObj;

    @Column(name = "descipObj")
    private String descipObj;

    @Column(name = "cantConseObj")
    private Double cantConseObj;

    @JoinColumn(name = "CodUsuarios_obj", referencedColumnName = "CodUsuarios")
    @ManyToOne
    private Usuario codUsuariosObj;

    @OneToMany(mappedBy = "codObjetivosDetprog", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<DetalleObjetivoProgramado> detalleObjetivoProgramadoCollection;

    public Objetivo() {
    }

    public Objetivo(Integer codObjetivos) {
        this.codObjetivos = codObjetivos;
    }

    public Objetivo(
            String nombreObj, Double cantidadObj,
            String descipObj, Double cantConseObj) {

        this.nombreObj = nombreObj;
        this.cantidadObj = cantidadObj;
        this.descipObj = descipObj;
        this.cantConseObj = cantConseObj;
        this.detalleObjetivoProgramadoCollection = new ArrayList<>();
    }

    public Integer getCodObjetivos() {
        return codObjetivos;
    }

    public void setCodObjetivos(Integer codObjetivos) {
        this.codObjetivos = codObjetivos;
    }

    public String getNombreObj() {
        return nombreObj;
    }

    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    public Double getCantidadObj() {
        return cantidadObj;
    }

    public void setCantidadObj(Double cantidadObj) {
        this.cantidadObj = cantidadObj;
    }

    public String getDescipObj() {
        return descipObj;
    }

    public void setDescipObj(String descipObj) {
        this.descipObj = descipObj;
    }

    public Double getCantConseObj() {
        return cantConseObj;
    }

    public void setCantConseObj(Double cantConseObj) {
        this.cantConseObj = cantConseObj;
    }

    public Usuario getCodUsuariosObj() {
        return codUsuariosObj;
    }

    public void setCodUsuariosObj(Usuario codUsuariosObj) {
        this.codUsuariosObj = codUsuariosObj;
    }

    public Collection<DetalleObjetivoProgramado> getDetalleObjetivoProgramadoCollection() {
        return detalleObjetivoProgramadoCollection;
    }

    public void setDetalleObjetivoProgramadoCollection(
            Collection<DetalleObjetivoProgramado> detalleObjetivoProgramadoCollection) {

        this.detalleObjetivoProgramadoCollection = detalleObjetivoProgramadoCollection;

        for (DetalleObjetivoProgramado detalle : detalleObjetivoProgramadoCollection) {
            detalle.setCodObjetivosDetprog(this);
        }
    }

    public void addDetalleObjetivoProgramado(
            DetalleObjetivoProgramado detalleObjetivoProgramado) {

        this.detalleObjetivoProgramadoCollection.add(detalleObjetivoProgramado);
        detalleObjetivoProgramado.setCodObjetivosDetprog(this);
    }

    public void removeDetalleObjetivoProgramado(
            DetalleObjetivoProgramado detalleObjetivoProgramado) {

        this.detalleObjetivoProgramadoCollection.remove(detalleObjetivoProgramado);
        detalleObjetivoProgramado.setCodObjetivosDetprog(null);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codObjetivos != null ? codObjetivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Objetivo)) {
            return false;
        }

        Objetivo other = (Objetivo) object;

        return !((this.codObjetivos == null && other.codObjetivos != null)
                || (this.codObjetivos != null
                        && !this.codObjetivos.equals(other.codObjetivos)));
    }

    @Override
    public String toString() {
        return "Objetivo{" +
                "codObjetivos=" + codObjetivos +
                ", nombreObj=" + nombreObj +
                ", cantidadObj=" + cantidadObj +
                ", descipObj=" + descipObj +
                ", cantConseObj=" + cantConseObj +
                '}';
    }
}