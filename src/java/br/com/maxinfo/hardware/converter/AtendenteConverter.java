/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.converter;

import br.com.maxinfo.hardware.jsf.UsuarioController;
import br.com.maxinfo.hardware.model.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author maxpc
 */
    
    @FacesConverter(forClass = Usuario.class)
    public class AtendenteConverter implements Converter {        
              
      
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");           
            for (Usuario u : controller.usuariosDaEmpresaLogada()){
                return u.getCpf().equalsIgnoreCase(value)?u:null;
            }
            return null;
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
