package sgr.app.core.account;

import java.util.Optional;

/**
 * @author dawbes
 */
public interface AccountService
{

	Account create(AccountCreateRequest request);

	void updateAccountPassword(AccountPasswordUpdateRequest request);

	Optional<Account> findByLogin(String login);

	<T> Optional<T> findUserByAccount(Account account);

}