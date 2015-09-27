/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxinfo.hardware.converter;

import br.com.maxinfo.hardware.facade.UsuarioFacade;
import br.com.maxinfo.hardware.jsf.UsuarioController;
import br.com.maxinfo.hardware.model.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

public class CpfConverter implements Converter {

    public String getAsString(FacesContext facesContext, UIComponent component, String object) {
        if (object == null) {
            return null;
        }
        String cpf = (String) object;
        cpf.replaceAll("-", "");
        return cpf;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       UsuarioFacade usuario = new UsuarioFacade();
       String cpf = getAsString(context, component, value);
       return  usuario.find(cpf) ;
       
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
