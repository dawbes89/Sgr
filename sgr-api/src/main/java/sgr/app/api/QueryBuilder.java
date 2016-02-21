package sgr.app.api;

/**
 * @author dawbes
 *
 * @param <QUERY>
 *           type of query to build
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
