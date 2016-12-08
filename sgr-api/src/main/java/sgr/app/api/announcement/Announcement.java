package sgr.app.api.announcement;

import sgr.app.api.admin.Admin;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity for announcements.
 *
 * @author dawbes89
 */
@Entity
@Table(name = "announcement")
public class Announcement
{

	public final static String PROPERTY_DATE = "date";

	private final static SimpleDateFormat MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "content", nullable = false)
	private String content;

	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "admin_id", nullable = false, referencedColumnName = "id",
			foreignKey = @ForeignKey(name = "announcement_admin_id_fk"))
	private Admin admin;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getFormattedDate()
	{
		return MINUTE_FORMAT.format(date);
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public void setAdmin(Admin admin)
	{
		this.admin = admin;
	}

}
