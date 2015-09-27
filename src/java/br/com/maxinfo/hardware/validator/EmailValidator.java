package br.com.maxinfo.hardware.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

	public static String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]"
			+ "{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
			+ "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	@Override
	public void validate(FacesContext context, UIComponent uic, Object o)
			throws ValidatorException {
		String valor = (String) o;
		if (!valor.matches(EMAIL_REGEX)){
			FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Email: usuario@dominio");
            throw new ValidatorException(message);
		}
	}
}
