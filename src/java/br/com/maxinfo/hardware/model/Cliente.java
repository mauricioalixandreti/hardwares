/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author maxpc
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findByEmpresa", query = "SELECT c FROM Cliente c WHERE c.empresa.email = :empresa"),
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByEmailcpf", query = "SELECT c FROM Cliente c WHERE c.emailcpf = :emailcpf"),
    @NamedQuery(name = "Cliente.findByNomeCompleto", query = "SELECT c FROM Cliente c WHERE c.nomeCompleto = :nomeCompleto"),
    @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular"),
    @NamedQuery(name = "Cliente.findByRua", query = "SELECT c FROM Cliente c WHERE c.rua = :rua"),
    @NamedQuery(name = "Cliente.findByCidade", query = "SELECT c FROM Cliente c WHERE c.cidade = :cidade"),
    @NamedQuery(name = "Cliente.findByNumeroRua", query = "SELECT c FROM Cliente c WHERE c.numeroRua = :numeroRua"),
    @NamedQuery(name = "Cliente.findByAniversario", query = "SELECT c FROM Cliente c WHERE c.aniversario = :aniversario"),
    @NamedQuery(name = "Cliente.findByDataCadastro", query = "SELECT c FROM Cliente c WHERE c.dataCadastro = :dataCadastro")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "emailcpf")
    private String emailcpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomeCompleto")
    private String nomeCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "celular")
    private String celular;
    @Size(max = 100)
    @Column(name = "rua")
    private String rua;
    @Size(max = 100)
    @Column(name = "Cidade")
    private String cidade;
    @Column(name = "numeroRua")
    private Integer numeroRua;
    @Column(name = "aniversario")
    @Temporal(TemporalType.DATE)
    private Date aniversario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Servico> servicoList;
    @JoinColumn(name = "empresa", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Cliente() {
    }

    public Cliente(String emailcpf) {
        this.emailcpf = emailcpf;
    }

    public Cliente(String emailcpf, String nomeCompleto, String celular, Date dataCadastro) {
        this.emailcpf = emailcpf;
        this.nomeCompleto = nomeCompleto;
        this.celular = celular;
        this.dataCadastro = dataCadastro;
    }

    public String getEmailcpf() {
        return emailcpf;
    }

    public void setEmailcpf(String emailcpf) {
        this.emailcpf = emailcpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(Integer numeroRua) {
        this.numeroRua = numeroRua;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailcpf != null ? emailcpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.emailcpf == null && other.emailcpf != null) || (this.emailcpf != null && !this.emailcpf.equals(other.emailcpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.maxinfo.hardware.model.Cliente[ emailcpf=" + emailcpf + " ]";
    }
    
}
