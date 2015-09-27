package br.com.maxinfo.hardware.jsf;

import br.com.maxinfo.hardware.facade.ClienteFacade;
import br.com.maxinfo.hardware.facade.ServicoFacade;
import br.com.maxinfo.hardware.jsf.util.JsfUtil;
import br.com.maxinfo.hardware.jsf.util.PaginationHelper;
import br.com.maxinfo.hardware.facade.UsuarioFacade;


import br.com.maxinfo.hardware.model.Empresa;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;


import br.com.maxinfo.hardware.model.Usuario;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private Usuario usuarioLogado;
    private DataModel items = null;
    @EJB
    private br.com.maxinfo.hardware.facade.UsuarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String cpfasString = "08380471490";
    private String retypeSenha;    
  
    
    public UsuarioController() {
        current = new Usuario();
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario(); 
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public void prepareCreate() {
        current = null;
        setCpfasString(null);
    }

    public String logar() {
        
        if (!getCpfasString().isEmpty() || getCpfasString() != null) {
            current.setCpf(getCpfasString().replaceAll("-", ""));
        }
        
        String cpfInformado = current.getCpf();
        String senhaInformada = current.getSenha();
        String tipoUsuario = current.getTipoUsuario().trim();
        
        current = getFacade().findUsuarioByCpfSenhaTipo(cpfInformado.trim(), senhaInformada.trim(), tipoUsuario);
        
        if (current != null){            
            setUsuarioLogado(current);     
            prepareCreate();
            return "/adm/usuario/home.xhtml?faces-redirect=true";
        } else {
            setUsuarioLogado(null);            
            getSelected();         
            current.setTipoUsuario(tipoUsuario);
            JsfUtil.addErrorMessage(" Usuário ou senha inválida, tente novamente!");            
            return "/index.xhtml?redirect=true";
        }      
        
        
    }

    public String logout() {
        setUsuarioLogado(null);
        JsfUtil.logOut();
        return "/index.xhtml?faces-redirect=true";
    }

    public String create() {
        try {
            current.setCpf(getCpfasString().replaceAll("-", ""));
            if (current.getSenha().equalsIgnoreCase(getRetypeSenha())) {
                current.setAtivo(1);
                current.setEmpresa(new Empresa(getUsuarioLogado().getEmpresa().getEmail()));
                getFacade().create(current);                
                prepareCreate();
                JsfUtil.addSuccessMessage("Usuário cadastrado com sucesso!");
                return "/adm/empresa/manageatendente.xhtml"; //apenas usuarios ADM podem criar outro usuários                
            }
            
            prepareCreate();
            JsfUtil.addErrorMessage("Erro ao criar usuario, tente novamente!");
            return "/adm/empresa/manageatendente.xhtml"; //apenas usuarios ADM podem criar outro usuários
        } catch (Exception e) {
           
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
           
            return "View";
        } catch (Exception e) {
           
            return null;
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            
        } catch (Exception e) {
            
        }
    }

    private void recreateModel() {
        items = null;
    }

//    @FacesConverter(forClass = Usuario.class)
//    public static class UsuarioControllerConverter implements Converter {
//
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "usuarioController");
//            return controller.ejbFacade.find(getKey(value));
//        }
//
//        java.lang.String getKey(String value) {
//            java.lang.String key;
//            key = value;
//            return key;
//        }
//
//        String getStringKey(java.lang.String value) {
//            StringBuffer sb = new StringBuffer();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Usuario) {
//                Usuario o = (Usuario) object;
//                String cpf = o.getCpf();              
//                return getStringKey(cpf);
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UsuarioController.class.getName());
//            }
//        }
//    }
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getCpfasString() {
        return cpfasString;
    }

    public void setCpfasString(String cpfasString) {
        this.cpfasString = cpfasString;
    }

    public boolean acessoAtendente() {
        boolean aux = false;
        if (current.getTipoUsuario().equalsIgnoreCase("ATENDENTE")) {
            aux = true;
        }
        return aux;
    }

    public boolean acessoADM() {
        boolean aux = false;
        if (current.getTipoUsuario().equalsIgnoreCase("ADM")) {
            aux = true;
        }
        return aux;
    }

    public String getRetypeSenha() {
        return retypeSenha;
    }

    public void setRetypeSenha(String retypeSenha) {
        this.retypeSenha = retypeSenha;
    }       

    public List<Usuario> usuariosDaEmpresaLogada() {
        List<Usuario> aux = getFacade().usuariosDaEmpresaLogada(getUsuarioLogado().getEmpresa().getEmail());
        aux.remove(getUsuarioLogado());
        return aux;
    }
    
    public void prepareToRemoveAt (Usuario u){  
        Usuario aux = u;
        setCurrent(aux);
    }
    
    public String removeAtendenteSelected (){
        getFacade().remove(getCurrent());
        setCurrent(null);
        JsfUtil.addSuccessMessage("Atendete removido com Sucesso!");
        return "/adm/empresa/deativarAtendente.xhtml";
    }
    
    

    public void setCurrent(Usuario current) {        
        this.current = current;
    }

    public Usuario getCurrent() {
        return current;
    }
    
    public boolean currentIsNotNull(){
        if (current == null){
            return false;
        }
        return true;
    }
    
   
    
    
    
    
}
