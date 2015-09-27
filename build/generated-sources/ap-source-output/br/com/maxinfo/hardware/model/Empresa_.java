package br.com.maxinfo.hardware.model;

import br.com.maxinfo.hardware.model.Cliente;
import br.com.maxinfo.hardware.model.Servico;
import br.com.maxinfo.hardware.model.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2015-09-22T18:44:24")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> email;
    public static volatile SingularAttribute<Empresa, String> nomeEmpresa;
    public static volatile ListAttribute<Empresa, Usuario> usuarioList;
    public static volatile ListAttribute<Empresa, Cliente> clienteList;
    public static volatile SingularAttribute<Empresa, Integer> blocked;
    public static volatile SingularAttribute<Empresa, String> cnpj;
    public static volatile SingularAttribute<Empresa, String> senha;
    public static volatile ListAttribute<Empresa, Servico> servicoList;

}