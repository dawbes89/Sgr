package sgr.app.core.account;

/**
 * Created by leonzio on 2016-12-09.
 */
public class AccountPasswordUpdateRequest
{
	private Long id;
	private String newPassword;

	public Account updatePassword(AccountGetterForUpdate accountGetterForUpdate, PasswordEncoder passwordEncoder)
	{
		// TODO validate

		Account account = accountGetterForUpdate.get(id);

		String encodedPassword = passwordEncoder.encode(newPassword);
		account.setPassword(encodedPassword);
		return account;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
}
