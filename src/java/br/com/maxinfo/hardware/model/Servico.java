/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "servico")
@NamedQueries({
    @NamedQuery(name = "Servico.findByEmpresa", query = "SELECT s FROM Servico s WHERE s.empresa.email = :empresa"),
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s"),
    @NamedQuery(name = "Servico.findByCodigo", query = "SELECT s FROM Servico s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Servico.findByStatus", query = "SELECT s FROM Servico s WHERE s.status = :status"
            + " and s.empresa.email = :emailEmpresa order by s.dataEntrada DESC"),
    @NamedQuery(name = "Servico.findByDescricao", query = "SELECT s FROM Servico s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Servico.findByDataEntrada", query = "SELECT s FROM Servico s WHERE s.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "Servico.findByNomeServico", query = "SELECT s FROM Servico s WHERE s.nomeServico = :nomeServico"),
    @NamedQuery(name = "Servico.findByPago", query = "SELECT s FROM Servico s WHERE s.pago = :pago"),
    @NamedQuery(name = "Servico.findByValor", query = "SELECT s FROM Servico s WHERE s.valor = :valor"),
    @NamedQuery(name = "Servico.findByDataSaida", query = "SELECT s FROM Servico s WHERE s.dataSaida = :dataSaida")})
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    
//    @Size(max = 300)
//    @Column(name = "observacao")
//    private String observacao;    
//    
    @Size(max = 300)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataEntrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomeServico")
    private String nomeServico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago")
    private int pago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "dataSaida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    @JoinColumn(name = "empresa", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Empresa empresa;    
    @JoinColumn(name = "cliente", referencedColumnName = "emailcpf")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Servico() {
    }

    public Servico(String codigo) {
        this.codigo = codigo;
    }

    public Servico(String codigo, int status, Date dataEntrada, String nomeServico, int pago) {
        this.codigo = codigo;
        this.status = status;
        this.dataEntrada = dataEntrada;
        this.nomeServico = nomeServico;
        this.pago = pago;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servico)) {
            return false;
        }
        Servico other = (Servico) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.maxinfo.hardware.model.Servico[ codigo=" + codigo + " ]";
    }
    
    public void converterValor ( String valor ){        
         String aux = valor.replace(".","").replace(",", ".");       
         setValor(Double.valueOf(aux));        
    }
    
    public void converterPago (String pago){
        setPago( "Y".equals(pago.trim())?1:0 );
    }

//    public String getObservacao() {
//        return observacao;
//    }
//
//    public void setObservacao(String observacao) {
//        this.observacao = observacao;
//    }
    
    
    
    
    
    
   
    
    
    
    
    
}
