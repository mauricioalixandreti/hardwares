package br.com.maxinfo.hardware.model;

import br.com.maxinfo.hardware.model.Empresa;
import br.com.maxinfo.hardware.model.Servico;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2015-09-22T18:44:24")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> cidade;
    public static volatile SingularAttribute<Cliente, String> nomeCompleto;
    public static volatile SingularAttribute<Cliente, String> emailcpf;
    public static volatile SingularAttribute<Cliente, String> rua;
    public static volatile SingularAttribute<Cliente, Integer> numeroRua;
    public static volatile SingularAttribute<Cliente, Empresa> empresa;
    public static volatile SingularAttribute<Cliente, Date> aniversario;
    public static volatile ListAttribute<Cliente, Servico> servicoList;
    public static volatile SingularAttribute<Cliente, Date> dataCadastro;
    public static volatile SingularAttribute<Cliente, String> celular;

}