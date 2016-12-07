package sgr.app.api.account;

import java.io.Serializable;

/**
 * @author dawbes
 */
public class AccountQuery implements Serializable
{
	private static final long serialVersionUID = 3742970813731527612L;

	private String login;

	private String password;

	public static final AccountQuery withLoginAndPassword(String login, String password)
	{
		AccountQuery query = new AccountQuery();
		query.setLogin(login);
		query.setPassword(password);
		return query;
	}

	public static final AccountQuery withLogin(String login)
	{
		AccountQuery query = new AccountQuery();
		query.setLogin(login);
		return query;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
