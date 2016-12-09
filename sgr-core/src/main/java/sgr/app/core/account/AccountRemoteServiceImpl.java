package sgr.app.core.account;

import org.springframework.beans.factory.annotation.Required;
import sgr.app.api.account.AccountRemoteService;

/**
 * Created by leonzio on 2016-12-09.
 */
public class AccountRemoteServiceImpl implements AccountRemoteService
{
	private AccountService accountService;

	@Required
	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}
}
