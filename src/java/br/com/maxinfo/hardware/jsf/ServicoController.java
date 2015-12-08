package br.com.maxinfo.hardware.jsf;

import br.com.maxinfo.hardware.facade.ClienteFacade;
import br.com.maxinfo.hardware.model.Servico;
import br.com.maxinfo.hardware.jsf.util.JsfUtil;
import br.com.maxinfo.hardware.jsf.util.PaginationHelper;
import br.com.maxinfo.hardware.facade.ServicoFacade;

import br.com.maxinfo.hardware.model.Cliente;
import br.com.maxinfo.hardware.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "servicoController")
@SessionScoped
public class ServicoController implements Serializable {

    private Servico current;
    private Servico servicoToImprimir;
    private DataModel items = null;
    @EJB
    private br.com.maxinfo.hardware.facade.ServicoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Cliente clienteSelected;
    private Cliente clienteToImprimir;
    
    @EJB
    ClienteFacade clienteFacade;

    public ServicoController() {
    }

    public Servico getSelected() {
        if (current == null) {
            current = new Servico();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ServicoFacade getFacade() {
        return ejbFacade;
    }

//    public PaginationHelper getPagination() {
//        if (pagination == null) {
//            pagination = new PaginationHelper(10) {
//
//                @Override
//                public int getItemsCount() {
//                    return getFacade().count();
//                }
//
//                @Override
//                public DataModel createPageDataModel() {
//                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
//                }
//            };
//        }
//        return pagination;
//    }

//    public String prepareList() {
//        recreateModel();
//        return "List";
//    }
//
//    public String prepareView() {
//        current = (Servico) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "View";
//    }

    public String prepareCreate() {
        current = new Servico();
        selectedItemIndex = -1;
        return "/adm/usuario/imprimirServico.xhtml?faces-redirect=true";
    }

    public String create() {
        try {
            // converter valor e pagamento e gerar codigo antes do create ();
            current.converterPago(getPagoAsString());
            current.converterValor(getValorAsSring());
            generateCodigo(current);
            current.setEmpresa(JsfUtil.getEmpresaDaSessao());          
            getFacade().create(current);
            setClienteToImprimir(current.getCliente());
            setServicoToImprimir(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ServicoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

//    public String prepareEdit() {
//        current = (Servico) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        return "Edit";
//    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ServicoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

//    public String destroy() {
//        current = (Servico) getItems().getRowData();
//        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
//        performDestroy();
//        recreateModel();
//        return "List";
//    }
//
//    public String destroyAndView() {
//        performDestroy();
//        recreateModel();
//        updateCurrentItem();
//        if (selectedItemIndex >= 0) {
//            return "View";
//        } else {
//            // all items were removed - go back to list
//            recreateModel();
//            return "List";
//        }
//    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ServicoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
//
//    private void updateCurrentItem() {
//        int count = getFacade().count();
//        if (selectedItemIndex >= count) {
//            // selected index cannot be bigger than number of items:
//            selectedItemIndex = count - 1;
//            // go to previous page if last page disappeared:
//            if (pagination.getPageFirstItem() >= count) {
//                pagination.previousPage();
//            }
//        }
//        if (selectedItemIndex >= 0) {
//            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
//        }
//    }

//    public DataModel getItems() {
//        if (items == null) {
//            items = getPagination().createPageDataModel();
//        }
//        return items;
//    }
//
//    private void recreateModel() {
//        items = null;
//    }
//
//    public String next() {
//        getPagination().nextPage();
//        recreateModel();
//        return "List";
//    }
//
//    public String previous() {
//        getPagination().previousPage();
//        recreateModel();
//        return "List";
//    }
//
//    public SelectItem[] getItemsAvailableSelectMany() {
//        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
//    }
//


    @FacesConverter(forClass = Servico.class)
    public static class ServicoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServicoController controller = (ServicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "servicoController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Servico) {
                Servico o = (Servico) object;
                return getStringKey(o.getCodigo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ServicoController.class.getName());
            }
        }
    }
    
    private Usuario getUsuarioLogado(){
         HttpServletRequest req = null;
         UsuarioController aux = null;
         req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         aux = (UsuarioController) req.getSession().getAttribute("usuarioController");        
         return aux.getUsuarioLogado();        
    }
    
    public List<Servico> getAllServicos(){    
        String empresa = JsfUtil.getEmpresaLogada();
        List<Servico> lista = getFacade().servicosDaEmpresaLogada(empresa);       
        return lista;          
    }
    
    
    public List<Servico> getAllServicosFinalizados (){        
        // Flata filtrar por empresa logada e estatus 
        return getFacade().listarServicosByStatus(1);
    }
    public List<Servico> getAllServicosPendentes (){
        //Fala filtrar por empresa logada e status 
        return getFacade().listarServicosByStatus(0);
    }
    
    public List<Cliente> getAllClientesDaEmpresaLogada (){       
       String empresa = JsfUtil.getEmpresaLogada();
       return  getClienteFacade().clientesDaEmpresaLogada(empresa);
    }

    public Cliente getClienteSelected() {
        return clienteSelected;
    }

    public void setClienteSelected(Cliente clienteSelected) {
        this.clienteSelected = clienteSelected;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }
  
    
    private String valorAsSring;
    private String pagoAsString;

    public String getValorAsSring() {
        return valorAsSring;
    }

    public void setValorAsSring(String valorAsSring) {
        this.valorAsSring = valorAsSring;
    }

    public String getPagoAsString() {
        return pagoAsString;
    }

    public void setPagoAsString(String tipoAsString) {
        this.pagoAsString = tipoAsString;
    }
    
    public void generateCodigo (Servico geraracodigo){
        GregorianCalendar gr = (GregorianCalendar) GregorianCalendar.getInstance();
        gr.setGregorianChange(new Date ());
        Usuario aux = getUsuarioLogado();
        String cpf = aux.getCpf();        
        String codigo = cpf.substring(0,3)+gr.getTimeInMillis()+cpf.substring(7);
        geraracodigo.setCodigo(codigo.trim());
    }
    private int flagEdit = 0; 

    public void setFlagEdit(int flagEdit) {
        this.flagEdit = flagEdit;
    }

    public int getFlagEdit() {
        return flagEdit;
    }
    
    public String prepareToEdit (Servico s){
        if(s==null){
            setCurrent(s);            
            return "/adm/usuario/home.xhtml?faces-redirect=true";
        }
        setFlagEdit(1);
        setCurrent(s);
        return "/adm/usuario/detalhar-servico.xhtml?faces-redirect=true";
    }
    
    public String updateServicoSelecionado (){  
        Servico aux = getCurrent();
        getFacade().edit(aux);
        setCurrent(null);
        JsfUtil.addSuccessMessage("Dados atualizados com Sucesso.!");
        return "/adm/usuario/home.xhtml?faces-redirect=true";
    }
    
   

    public Servico getCurrent() {
        return current;
    }

    public void setCurrent(Servico current) {
        this.current = current;
    }
    
   
    
     public void prepareToRemove (Servico remove ){
        Servico aux = remove;
        setCurrent(aux);
    }
    
    public String deleteServicoSelecionado (){
        Servico aux = getCurrent();
        getFacade().remove(aux);
        setCurrent(null);
        JsfUtil.addSuccessMessage("Serviço removido com Sucesso!");
        return "/adm/usuario/home.xhtml?faces-redirect=true";
    }
    
    
    public Cliente getClienteToImprimir() {
        return clienteToImprimir;
    }

    public void setClienteToImprimir(Cliente clienteToImprimir) {
        this.clienteToImprimir = clienteToImprimir;
    }

    public Servico getServicoToImprimir() {
        return servicoToImprimir;
    }

    public void setServicoToImprimir(Servico servicoToImprimir) {
        this.servicoToImprimir = servicoToImprimir;
    }
    
    
    
    
    
    
}
