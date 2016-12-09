package sgr.app.frontend.panels;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sgr.app.api.translation.TranslationService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Panel extender for all panels in application. For using entites list in xhtml part use {@link #entities} and for
 * single use{@link #entity}. Sometimes with panel name eq. <code>panelName.entity.entityProperty</code>.
 *
 * @param <T>
 * 		data type used in panel
 * @author leonzio
 */
public abstract class AbstractPanel<T> implements Serializable
{

	private static final long serialVersionUID = 6754605828742536669L;

	@Autowired
	protected TranslationService translationService;

	protected List<T> entities = new ArrayList<>();
	protected T entity;

	public AbstractPanel()
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * Initializing data.
	 */
	@PostConstruct
	public abstract void init();

	/**
	 * On load panel action.
	 */
	public abstract void onLoad();

	protected final void showValidationMessage(String formName, String messageKey, Severity severity)
	{
		final String messageContent = translationService.translate(messageKey);
		final FacesMessage message = new FacesMessage(messageContent);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(formName, message);
	}

	protected final void requestContextExecute(String pfAction)
	{
		final RequestContext context = RequestContext.getCurrentInstance();
		context.execute(pfAction);
	}

	public List<T> getEntities()
	{
		return entities;
	}

	public void setEntities(List<T> entities)
	{
		Collections.copy(this.entities, entities);
	}

	public T getEntity()
	{
		return entity;
	}

	public void setEntity(T entity)
	{
		this.entity = entity;
	}

}
