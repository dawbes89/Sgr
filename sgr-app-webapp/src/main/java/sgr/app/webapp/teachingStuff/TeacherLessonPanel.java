package sgr.app.webapp.teachingStuff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sgr.app.api.assessment.AssessmentService;
import sgr.app.api.authentication.AuthenticationService;
import sgr.app.api.classgroup.ClassGroup;
import sgr.app.api.classgroup.ClassGroupQuery;
import sgr.app.api.classgroup.ClassGroupService;
import sgr.app.api.lesson.Lesson;
import sgr.app.api.lesson.LessonQuery;
import sgr.app.api.lesson.LessonService;
import sgr.app.api.presence.Presence;
import sgr.app.api.student.Student;
import sgr.app.api.student.StudentQuery;
import sgr.app.api.student.StudentService;
import sgr.app.api.teachingStuff.TeachingStuff;
import sgr.app.api.translation.TranslationService;
import sgr.app.frontend.panels.AbstractPanel;

/**
 * @author dawbes
 */
@Controller
public class TeacherLessonPanel extends AbstractPanel<Lesson>
{

   private static final long serialVersionUID = 6551034627669299579L;

   @Autowired
   private TranslationService translationService;

   @Autowired
   private AssessmentService assessmentService;

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

   public void handleClassChange()
   {
      searchLessons();
   }

   private void searchLessons()
   {
      LessonQuery query = new LessonQuery();
      if(classGroup.getId() != null)
      {
         query.setClassGroupId(classGroup.getId());
      }
      if(currentLoggedTeacher.getSchoolSubject() != null)
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

   public void create()
   {
      List<Presence> presences = new ArrayList<>();
      for (Student student : students)
      {
         Presence presence = new Presence();
         for (Student selectedStudent : selectedStudent)
         {
            if (student.getId().equals(selectedStudent.getId()))
            {
               presence.setPresence(true);
               presence.setStudentId(student.getId());
               presences.add(presence);

            }
            else
            {
               presence.setPresence(false);
               presence.setStudentId(student.getId());
               presences.add(presence);

            }
            break;
         }
      }
      presences.size();
      entity.setClassGroup(classGroup);
      entity.setDate(new Date());
      entity.setSchoolSubject(currentLoggedTeacher.getSchoolSubject());
      entity.setIssuerName(currentLoggedTeacher.getFullName());
      entity = lessonService.create(entity, presences);
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

}
