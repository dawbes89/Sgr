package sgr.app.api.admin;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.person.Person;
import sgr.app.api.person.PersonName;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing administrators.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "admin", uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id"},
		name = "admin_person_id_uk"), @UniqueConstraint(columnNames = {"account_id"}, name = "admin_account_id_uk")})
public class Admin implements PersonName, AccountEntity, Serializable
{

	public static final String PROPERTY_ID = "id";

	private static final long serialVersionUID = -3977084256453665930L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "admin_person_id_fk"))
	private Person person = new Person();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "admin_account_id_fk"))
	private Account account = new Account();

	@Column(name = "is_superuser", nullable = false)
	private boolean superuser = false;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	@Override
	public Account getAccount()
	{
		return account;
	}

	public void setAccount(Account account)
	{
		this.account = account;
	}

	public boolean isSuperuser()
	{
		return superuser;
	}

	public void setSuperuser(boolean superuser)
	{
		this.superuser = superuser;
	}

	//TODO remove from here
	@Transient
	@Override
	public String getFullName()
	{
		// TODO refaktor
		if (person == null)
		{
			return null;
		}
		return String.format("%s %s", person.getFirstName(), person.getLastName());
	}

}
