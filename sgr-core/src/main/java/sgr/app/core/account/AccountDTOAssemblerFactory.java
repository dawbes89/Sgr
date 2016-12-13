package sgr.app.core.account;

import sgr.app.api.account.AccountDTO;
import sgr.app.core.util.AbstractAssemblerFactory;
import sgr.app.core.util.Assembler;

/**
 * Created by leonzio on 2016-12-13.
 */
class AccountDTOAssemblerFactory extends AbstractAssemblerFactory<Account, AccountDTO>
{
	@Override
	protected Assembler<Account, AccountDTO> createInstance()
	{
		return MapperBuilder.builder(Account.class, AccountDTO.class).from(Account::getId).to(
				AccountDTO::setId).build();
	}
}
