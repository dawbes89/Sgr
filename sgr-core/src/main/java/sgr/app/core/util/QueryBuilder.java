package sgr.app.core.util;

/**
 * Base for <code>query</code> builders.
 *
 * @param <QUERY>
 * 		type of query to build
 * @author dawbes89
 */
public abstract class QueryBuilder<QUERY>
{
	protected final QUERY query;

	protected QueryBuilder(QUERY query)
	{
		this.query = query;
	}

	public QUERY build()
	{
		return query;
	}
}
