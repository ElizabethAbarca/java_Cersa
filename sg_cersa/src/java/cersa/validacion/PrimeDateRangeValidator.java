/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cersa.validacion;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ELIZABETH-PC
 */
@FacesValidator("primeDateRangeValidator")
public class PrimeDateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("finicial");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Fecha Obtenida: " + startDateValue,
                "Fecha Obtenida: " + startDateValue));

        if (startDateValue == null) {
            return;
        }

        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "La fecha Final no puede ser anterior a la fecha Inicial.",
                    "La fecha Final no puede ser anterior a la fecha Inicial.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
