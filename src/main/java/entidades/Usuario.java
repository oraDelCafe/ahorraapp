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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")

@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),

        @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.codUsuarios = :id"),

        @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombreUsu = :nombre"),

        @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dniUsu = :dni"),

        @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.emailUsu = :email")
})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodUsuarios")
    private Integer codUsuarios;

    @Column(name = "nombreUsu")
    private String nombreUsu;

    @Column(name = "Ape1Usu")
    private String ape1Usu;

    @Column(name = "Ape2Usu")
    private String ape2Usu;

    @Column(name = "emailUsu")
    private String emailUsu;

    @Column(name = "dniUsu")
    private String dniUsu;

    @Column(name = "dirUsu")
    private String dirUsu;

    @Column(name = "tlfUsu")
    private String tlfUsu;

    @OneToMany(mappedBy = "codUsuariosCuentasapp", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<CuentaApp> cuentaAppCollection;

    @OneToMany(mappedBy = "codUsuariosObj", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<Objetivo> objetivoCollection;

    public Usuario() {

    }

    public Usuario(Integer codUsuarios) {
        this.codUsuarios = codUsuarios;
    }

    
    public Usuario(
            String nombreUsu, String ape1Usu, String ape2Usu,
            String emailUsu, String dniUsu, String dirUsu,
            String tlfUsu) {

        this.nombreUsu = nombreUsu;
        this.ape1Usu = ape1Usu;
        this.ape2Usu = ape2Usu;
        this.emailUsu = emailUsu;
        this.dniUsu = dniUsu;
        this.dirUsu = dirUsu;
        this.tlfUsu = tlfUsu;

        this.cuentaAppCollection = new ArrayList<>();
        this.objetivoCollection = new ArrayList<>();
    }

    public Integer getCodUsuarios() {
        return codUsuarios;
    }

    public void setCodUsuarios(Integer codUsuarios) {
        this.codUsuarios = codUsuarios;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getApe1Usu() {
        return ape1Usu;
    }

    public void setApe1Usu(String ape1Usu) {
        this.ape1Usu = ape1Usu;
    }

    public String getApe2Usu() {
        return ape2Usu;
    }

    public void setApe2Usu(String ape2Usu) {
        this.ape2Usu = ape2Usu;
    }

    public String getEmailUsu() {
        return emailUsu;
    }

    public void setEmailUsu(String emailUsu) {
        this.emailUsu = emailUsu;
    }

    public String getDniUsu() {
        return dniUsu;
    }

    public void setDniUsu(String dniUsu) {
        this.dniUsu = dniUsu;
    }

    public String getDirUsu() {
        return dirUsu;
    }

    public void setDirUsu(String dirUsu) {
        this.dirUsu = dirUsu;
    }

    public String getTlfUsu() {
        return tlfUsu;
    }

    public void setTlfUsu(String tlfUsu) {
        this.tlfUsu = tlfUsu;
    }

    public Collection<CuentaApp> getCuentaAppCollection() {
        return cuentaAppCollection;
    }

    public void setCuentaAppCollection(
            Collection<CuentaApp> cuentaAppCollection) {

        this.cuentaAppCollection = cuentaAppCollection;

        for (CuentaApp cuenta : cuentaAppCollection) {
            cuenta.setCodUsuariosCuentasapp(this);
        }
    }

    public Collection<Objetivo> getObjetivoCollection() {
        return objetivoCollection;
    }

    public void setObjetivoCollection(
            Collection<Objetivo> objetivoCollection) {

        this.objetivoCollection = objetivoCollection;

        for (Objetivo objetivo : objetivoCollection) {
            objetivo.setCodUsuariosObj(this);
        }
    }

    public void addCuentaApp(CuentaApp cuenta) {
        this.cuentaAppCollection.add(cuenta);
        cuenta.setCodUsuariosCuentasapp(this);
    }

    public void removeCuentaApp(CuentaApp cuenta) {
        this.cuentaAppCollection.remove(cuenta);
        cuenta.setCodUsuariosCuentasapp(null);
    }

    public void addObjetivo(Objetivo objetivo) {
        this.objetivoCollection.add(objetivo);
        objetivo.setCodUsuariosObj(this);
    }

    public void removeObjetivo(Objetivo objetivo) {
        this.objetivoCollection.remove(objetivo);
        objetivo.setCodUsuariosObj(null);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuarios != null ? codUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Usuario)) {
            return false;
        }

        Usuario other = (Usuario) object;

        return !((this.codUsuarios == null && other.codUsuarios != null)
                || (this.codUsuarios != null
                        && !this.codUsuarios.equals(other.codUsuarios)));
    }

    @Override
    public String toString() {

        String cuentas = "";

        if (cuentaAppCollection != null) {
            for (CuentaApp c : cuentaAppCollection) {
                cuentas += c + "\n";
            }
        }

        String objetivos = "";

        if (objetivoCollection != null) {
            for (Objetivo o : objetivoCollection) {
                objetivos += o + "\n";
            }
        }

        return "Usuario{"
                + "codUsuarios=" + codUsuarios
                + ", nombreUsu=" + nombreUsu
                + ", ape1Usu=" + ape1Usu
                + ", ape2Usu=" + ape2Usu
                + ", emailUsu=" + emailUsu
                + ", dniUsu=" + dniUsu
                + ", dirUsu=" + dirUsu
                + ", tlfUsu=" + tlfUsu
                + ", cuentas=\n" + cuentas
                + ", objetivos=\n" + objetivos
                + '}';
    }
}