package sgr.app.api;
/**
 * @author dawbes
 * @param <Q>
 */
public abstract class QueryBuilder<Q>
{
   protected final Q query;

   protected QueryBuilder(Q query)
   {
      this.query = query;
   }

   public Q build()
   {
      return query;
   }
}
