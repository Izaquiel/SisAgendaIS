/**
 *
 */
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pojos.Pessoa;

/**
 * @author Izaquiel Cruz
 *
 */
@FacesConverter(value="pessoaConverter")
public class PessoaConverter implements Converter{

	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Pessoa) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Pessoa) {
            Pessoa entity= (Pessoa) value;
            if (entity != null && entity instanceof Pessoa && entity.getId() != null) {
                uiComponent.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return null;
    }

}
