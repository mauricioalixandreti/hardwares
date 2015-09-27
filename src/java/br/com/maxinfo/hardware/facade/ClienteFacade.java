/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.facade;



import br.com.maxinfo.hardware.model.Cliente;
import br.com.maxinfo.hardware.model.Empresa;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author maxpc
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "hardwaresPU")
    private EntityManager em;
   

    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public List<Cliente> clientesDaEmpresaLogada (String emailEmpresa){        
        Query createQuery = getEntityManager().createNamedQuery("Cliente.findByEmpresa");
        createQuery.setParameter("empresa",emailEmpresa);        
        List<Cliente> lista = createQuery.getResultList();
        return lista;
   }

       
      
    
   
    
}
