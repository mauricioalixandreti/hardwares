    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.converter;

import br.com.maxinfo.hardware.facade.ClienteFacade;
import br.com.maxinfo.hardware.facade.ServicoFacade;
import br.com.maxinfo.hardware.jsf.ServicoController;
import br.com.maxinfo.hardware.model.Cliente;
import br.com.maxinfo.hardware.model.Servico;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author maxpc
 */
    @FacesConverter(forClass = Cliente.class)
    public class ClienteConverter implements Converter {        
              
      
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServicoController controller = (ServicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "servicoController");
           
            return controller.getClienteFacade().find(getKey(value));
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
            String cpf = object.toString();
            if (object  != null && !cpf.isEmpty()) {
                cpf.replace(".","");
                return cpf.replaceAll("-", "");
            }
            return "";
        }
        
        
    }
