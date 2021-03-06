package sgr.app.api.teachingstaff;

import sgr.app.api.account.Account;
import sgr.app.api.account.AccountEntity;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.person.Person;
import sgr.app.api.person.PersonName;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity represents teaching staff.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "teaching_staff", uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id"},
		name = "teaching_staff_person_id_uk"), @UniqueConstraint(columnNames = {"account_id"},
		name = "teaching_staff_account_id_uk"), @UniqueConstraint(columnNames = {"preceptor_class_id"},
		name = "teaching_staff_preceptor_class_id_uk")})
public class TeachingStaff implements PersonName, AccountEntity, Serializable
{

	public final static String PROPERTY_ID = "id";

	public final static String PROPERTY_PRECEPTOR_CLASS = "preceptorClass";

	private static final long serialVersionUID = 1452686849131351842L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "teaching_staff_person_id_fk"))
	private Person person = new Person();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "teaching_staff_account_id_fk"))
	private Account account = new Account();

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "preceptor_class_id", referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "teaching_staff_preceptor_class_id_fk"))
	private ClassGroup preceptorClass = new ClassGroup();

	@Enumerated(EnumType.STRING)
	@Column(name = "school_subject", length = 25, nullable = false)
	private SchoolSubject schoolSubject;

	@Column(name = "academic_title", length = 25, nullable = false)
	private String academicTitle;

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

	public SchoolSubject getSchoolSubject()
	{
		return schoolSubject;
	}

	public void setSchoolSubject(SchoolSubject schoolSubject)
	{
		this.schoolSubject = schoolSubject;
	}

	public String getAcademicTitle()
	{
		return academicTitle;
	}

	public void setAcademicTitle(String academicTitle)
	{
		this.academicTitle = academicTitle;
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

	public ClassGroup getPreceptorClass()
	{
		return preceptorClass;
	}

	public void setPreceptorClass(ClassGroup preceptorClass)
	{
		this.preceptorClass = preceptorClass;
	}

	@Transient
	@Override
	public String getFullName()
	{
		if (person == null)
		{
			return null;
		}
		return String.format("%s %s", person.getFirstName(), person.getLastName());
	}

	@Transient
	public String getTeacherFullName()
	{
		return String.format("%s %s %s", academicTitle, person.getFirstName(), person.getLastName());
	}
}
