package sgr.app.core;

/**
 * @param <QUERY>
 * 		type of query to build
 * @author dawbes
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
