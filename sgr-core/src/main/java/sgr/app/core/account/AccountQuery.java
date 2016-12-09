package sgr.app.core.account;

import sgr.app.core.QueryBuilder;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author dawbes
 */
// TODO remove serializable
class AccountQuery implements Serializable
{
	private static final long serialVersionUID = 3742970813731527612L;

	private Optional<String> login = Optional.empty();

	// TODO
	void apply(AccountQuery.Applier applier)
	{
		login.ifPresent(applier::withLogin);
	}

	static Builder builder()
	{
		return new Builder();
	}

	static final class Builder extends QueryBuilder<AccountQuery>
	{
		private Builder()
		{
			super(new AccountQuery());
		}

		Builder withLogin(String login)
		{
			query.login = Optional.of(login);
			return this;
		}
	}

	// TODO javadoc
	interface Applier
	{
		void withLogin(String login);
	}
}
