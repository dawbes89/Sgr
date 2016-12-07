package sgr.app.api.admin;

import sgr.app.api.QueryBuilder;

import java.io.Serializable;

/**
 * @author leonzio
 */
public class AdminQuery implements Serializable
{

	private static final long serialVersionUID = -9125547122341562767L;

	private Long excludedId;

	public static Builder all()
	{
		return new Builder();
	}

	public Long getExcludedId()
	{
		return excludedId;
	}

	public void setExcludedId(Long excludedId)
	{
		this.excludedId = excludedId;
	}

	public static class Builder extends QueryBuilder<AdminQuery>
	{

		protected Builder()
		{
			super(new AdminQuery());
		}

		public Builder withExcludedId(Long id)
		{
			query.setExcludedId(id);
			return this;
		}
	}

}
