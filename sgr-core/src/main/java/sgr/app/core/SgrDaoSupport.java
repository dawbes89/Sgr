package sgr.app.core;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author leonzio
 */
@Transactional
public abstract class SgrDaoSupport
{
	private static final String ALIAS_PROPERTY = "%s.%s";

	private SessionFactory sessionFactory;

	protected static String nest(String property, String secondProperty)
	{
		return String.format(ALIAS_PROPERTY, property, secondProperty);
	}

	final Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	protected <T> Criteria createCriteria(Class<T> persistentClass)
	{
		return getSession().createCriteria(persistentClass);
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> search(Criteria criteria)
	{
		Collection<?> result = criteria.list();
		List<T> resultList = new ArrayList<>(result.size());
		for (Object object : result)
		{
			resultList.add((T) object);
		}
		return resultList;
	}

	protected <T> T createEntity(T entity)
	{
		getSession().save(entity);
		return entity;
	}

	protected <T> T updateEntity(T entity)
	{
		getSession().update(entity);
		return entity;
	}

	protected <T> void removeEntity(Class<T> clazz, Long id)
	{
		final T entity = getEntity(clazz, id);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getEntity(Class<T> clazz, Long id)
	{
		Object entity = getSession().get(clazz, id);
		return (T) entity;
	}

	@SuppressWarnings("unchecked")
	protected <T> Optional<T> find(Criteria criteria)
	{
		return Optional.of((T) criteria.uniqueResult());
	}

	protected <T> LockMode getEntityLockMode(T entity)
	{
		return getSession().getCurrentLockMode(entity);
	}

	@Required
	public final void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}