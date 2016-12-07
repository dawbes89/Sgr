package sgr.app.api.notification;

import java.io.Serializable;

public class NotificationQuery implements Serializable
{

	private static final long serialVersionUID = 6489537389725836368L;

	private Long studentId;

	public Long getStudentId()
	{
		return studentId;
	}

	public void setStudentId(Long studentId)
	{
		this.studentId = studentId;
	}

}
