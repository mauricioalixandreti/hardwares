package br.com.maxinfo.hardware.model;

import br.com.maxinfo.hardware.model.Cliente;
import br.com.maxinfo.hardware.model.Empresa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2015-09-22T18:44:24")
@StaticMetamodel(Servico.class)
public class Servico_ { 

    public static volatile SingularAttribute<Servico, String> codigo;
    public static volatile SingularAttribute<Servico, Integer> pago;
    public static volatile SingularAttribute<Servico, Integer> status;
    public static volatile SingularAttribute<Servico, Double> valor;
    public static volatile SingularAttribute<Servico, Cliente> cliente;
    public static volatile SingularAttribute<Servico, Date> dataSaida;
    public static volatile SingularAttribute<Servico, Empresa> empresa;
    public static volatile SingularAttribute<Servico, String> descricao;
    public static volatile SingularAttribute<Servico, String> nomeServico;
    public static volatile SingularAttribute<Servico, Date> dataEntrada;

}