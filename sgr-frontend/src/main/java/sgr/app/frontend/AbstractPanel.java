package sgr.app.frontend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Panel extender for all panels in application.<br>
 * For using entites list in xhtml part use {@link #entities} and for single use
 * {@link #entity}. Somethimes with panel name
 * <code>panelName.entity.entityProperty</code>.
 *
 * @author leonzio
 * @param <T>
 *           data type used in panel
 */
public abstract class AbstractPanel<T> implements Serializable
{

   private static final long serialVersionUID = 6754605828742536669L;

   protected List<T> entities;

   protected T entity;

   public AbstractPanel()
   {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
      entities = new ArrayList<T>();
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

   public List<T> getEntities()
   {
      return entities;
   }

   public void setEntities(List<T> entities)
   {
      this.entities = entities;
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
