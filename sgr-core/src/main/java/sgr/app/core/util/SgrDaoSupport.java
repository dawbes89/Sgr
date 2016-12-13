package sgr.app.core.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <code>DaoSupport</code> for managing operations on database.
 *
 * @author leonzio
 */
//TODO javadoc's on methods
@Transactional
public abstract class SgrDaoSupport
{
	private static final String ALIAS_PROPERTY = "%s.%s";

	private SessionFactory sessionFactory;

	protected static String nest(String property, String secondProperty)
	{
		return String.format(ALIAS_PROPERTY, property, secondProperty);
	}

	protected <T> Criteria createCriteria(Class<T> persistentClass)
	{
		return getSession().createCriteria(persistentClass);
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> search(Criteria criteria)
	{
		Collection<?> result = criteria.list();
		return result.stream().map(obj -> (T) obj).collect(Collectors.toList());
	}

	protected <T> T create(T entity)
	{
		getSession().save(entity);
		return entity;
	}

	protected <T> T update(T entity)
	{
		getSession().update(entity);
		return entity;
	}

	protected <T> void remove(Class<T> clazz, Long id)
	{
		final T entity = get(clazz, id);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	protected <T> T get(Class<T> clazz, Long id)
	{
		Object entity = getSession().get(clazz, id);
		return (T) entity;
	}

	@SuppressWarnings("unchecked")
	protected <T> Optional<T> find(Criteria criteria)
	{
		return Optional.of((T) criteria.uniqueResult());
	}

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Required
	public final void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}