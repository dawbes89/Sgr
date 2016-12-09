package sgr.app.core.account;

import sgr.app.core.QueryBuilder;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author dawbes
 */
// TODO remove serializable
public class AccountQuery implements Serializable
{
	private static final long serialVersionUID = 3742970813731527612L;

	private Optional<String> login = Optional.empty();

	// TODO
	public void apply(AccountQuery.Applier applier)
	{
		login.ifPresent(applier::withLogin);
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder extends QueryBuilder<AccountQuery>
	{
		private Builder()
		{
			super(new AccountQuery());
		}

		public Builder withLogin(String login)
		{
			query.login = Optional.of(login);
			return this;
		}
	}

	// TODO javadoc
	public interface Applier
	{
		void withLogin(String login);
	}
}
