/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.facade;

import br.com.maxinfo.hardware.jsf.util.JsfUtil;
import br.com.maxinfo.hardware.model.Usuario;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author maxpc
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "hardwaresPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }    
  
   public List<Usuario> usuariosDaEmpresaLogada (String emailEmpresa){
        Query createQuery = getEntityManager().createNamedQuery("Usuario.findAllByEmpresaLogada");
        createQuery.setParameter("empresa",  emailEmpresa);        
        return createQuery.getResultList();
   }
   
   public Usuario findUsuarioByCpfSenhaTipo (String emailEmpresa, String senha, String tipoUsuario){
        Query createQuery = getEntityManager().createNamedQuery("Usuario.findByCpfSenhaTipo");
        createQuery.setParameter("senha", senha);        
        createQuery.setParameter("cpf", emailEmpresa); 
        createQuery.setParameter("tipo", tipoUsuario);
        Usuario aux = null;
        try{
            aux = (Usuario) createQuery.getSingleResult();            
        }catch (NoResultException ex){
           return null;
        }
        return aux;
   }
   
   
   
   
   
   
    
    
}
