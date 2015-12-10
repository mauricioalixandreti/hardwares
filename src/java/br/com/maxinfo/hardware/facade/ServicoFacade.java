/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.facade;

import br.com.maxinfo.hardware.model.Servico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
/**
 *
 * @author maxpc
 */
@Stateless
public class ServicoFacade extends AbstractFacade<Servico> {
    @PersistenceContext(unitName = "hardwaresPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicoFacade() {
        super(Servico.class);
    }
    
    public List<Servico> servicosDaEmpresaLogada (String emailEmpresa){        
        Query createQuery = getEntityManager().createNamedQuery("Servico.findByEmpresa");
        createQuery.setParameter("empresa",emailEmpresa);
        List<Servico> resultList = createQuery.getResultList();
        return resultList;
   }
    
    public List<Servico> listarServicosByStatus (int status, String emailEmpresa){        
        Query createQuery = getEntityManager().createNamedQuery("Servico.findByStatus");
        createQuery.setParameter("status",status);
        createQuery.setParameter("emailEmpresa",emailEmpresa);        
        List<Servico> resultList = createQuery.getResultList();
        return resultList;
   }
    
  
    
}
