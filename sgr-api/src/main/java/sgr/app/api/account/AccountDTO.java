package sgr.app.api.account;

import java.io.Serializable;

/**
 * Created by leonzio on 2016-12-09.
 */
public class AccountDTO implements Serializable
{
	private Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
