package sgr.app.webapp.teachingStuff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;
import sgr.app.api.presence.Presence;
import sgr.app.api.presence.PresenceStatus;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
public class TeacherLessonPanel extends AbstractPanel<Lesson>
{

   private static final long serialVersionUID = 6551034627669299579L;

   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassGroupService classGroupService;

   @Autowired
   private AuthenticationService authenticationService;

   @Autowired
   private LessonService lessonService;

   private TeachingStuff currentLoggedTeacher;

   private List<Student> students;

   private Student student;

   private ClassGroup classGroup;

   private List<ClassGroup> classes;

   private List<Student> selectedStudent = new ArrayList<>();

   @Override
   public void init()
   {
      classGroup = new ClassGroup();
      entity = new Lesson();
      entities = new ArrayList<>();
      students = new ArrayList<>();
      student = new Student();
      classes = classGroupService.search(ClassGroupQuery.EMPTY);
   }

   @Override
   public void onLoad()
   {
      init();
      currentLoggedTeacher = authenticationService.getCurrentUser();
      if (currentLoggedTeacher == null)
      {
         return;
      }
      final ClassGroup preceptorClass = currentLoggedTeacher.getPreceptorClass();

      if (preceptorClass == null)
      {
         searchLessons();
         return;
      }
      classGroup = preceptorClass;
      searchLessons();
   }

   public void searchLessons()
   {
      LessonQuery query = new LessonQuery();
      if (classGroup.getId() != null)
      {
         query.setClassGroupId(classGroup.getId());
      }
      if (currentLoggedTeacher.getSchoolSubject() != null)
      {
         query.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      }
      entities = lessonService.search(query);
   }

   public void searchStudents()
   {
      StudentQuery query = new StudentQuery();
      if (classGroup != null)
      {
         query.setClassGroupId(classGroup.getId());
      }
      students = studentService.search(query);
   }

   public void create() throws ParseException
   {
      List<Presence> presences = createPressences();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      List<Lesson> lessonsForClass = lessonService.search(LessonQuery.all()
            .withClassGroupId(classGroup.getId())
            .withSchoolSubject(currentLoggedTeacher.getSchoolSubject()).build());

      entity.setLessonNumber(lessonsForClass.size() + 1);
      entity.setClassGroup(classGroup);
      entity.setDate(dateFormat.parse(dateFormat.format(new Date())));
      entity.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      entity.setIssuerName(currentLoggedTeacher.getFullName());
      entity = lessonService.create(entity, presences);
      searchStudents();
      searchLessons();
   }

   public List<Student> getStudents()
   {
      return students;
   }

   public void setStudents(List<Student> students)
   {
      this.students = students;
   }

   public Student getStudent()
   {
      return student;
   }

   public void setStudent(Student student)
   {
      this.student = student;
   }

   public ClassGroup getClassGroup()
   {
      return classGroup;
   }

   public void setClassGroup(ClassGroup classGroup)
   {
      this.classGroup = classGroup;
   }

   public List<ClassGroup> getClasses()
   {
      return classes;
   }

   public void setClasses(List<ClassGroup> classes)
   {
      this.classes = classes;
   }

   public TeachingStuff getCurrentLoggedTeacher()
   {
      return currentLoggedTeacher;
   }

   public void setCurrentLoggedTeacher(TeachingStuff currentLoggedTeacher)
   {
      this.currentLoggedTeacher = currentLoggedTeacher;
   }

   public List<Student> getSelectedStudent()
   {
      return selectedStudent;
   }

   public void setSelectedStudent(List<Student> selectedStudent)
   {
      this.selectedStudent = selectedStudent;
   }

   private List<Presence> createPressences()
   {
      List<Presence> presences = new ArrayList<>();
      for (Student student : students)
      {
         presences.add(createPresence(student));
      }
      for (Student student : selectedStudent)
      {
         for (Presence presence : presences)
         {
            if (presence.getStudent().getId().equals(student.getId()))
            {
               presence.setStatus(PresenceStatus.PRESENT);
            }
         }
      }
      return presences;
   }

   private static Presence createPresence(Student student)
   {
      Presence presence = new Presence();
      presence.setStudent(student);
      presence.setStatus(PresenceStatus.ABSENT);
      return presence;
   }

}
