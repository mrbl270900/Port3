import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    UIJavaFX view;
    StudentModel model;

    public StudentController (StudentModel m, UIJavaFX v) throws SQLException {
        this.view = v;
        this.model = m;

        this.view.exitBtn.setOnAction(e -> {
            Platform.exit();
            try {
                this.model.closeStudentDataConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.model.connectToStudentData();
        this.model.CreateStatement();
        this.view.Courses = GetCourses();
        this.view.Students = GetStudents();
        view.GetStudentsInfo.setOnAction(e->{
            try {
                HandleGetStudentsInfo(String.valueOf(view.StudentComB.getValue()), view.StudentGrade);
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        });

        view.GetGradesCourse.setOnAction(e->{
            try {
                HandleGetCourseAVGGrade(String.valueOf(view.CourseComB.getValue()), view.StudentGrade);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        view.GetGradesStudent.setOnAction(e->{
            try {
                HandleGetStudentAVGGrade(String.valueOf(view.StudentComB.getValue()), view.StudentGrade);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        this.view.configure();
    }
    public ObservableList<String> GetStudents() throws SQLException{
        ArrayList<String> Students = model.SQLQueryStudents();
        ObservableList<String> StudentsNames = FXCollections.observableArrayList(Students);
        return StudentsNames;
    }

    public ObservableList<String> GetCourses() throws SQLException{
        ArrayList<String> Course = model.SQLQueryCourses();
        ObservableList<String> CoursesNames = FXCollections.observableArrayList(Course);
        return CoursesNames;
    }

    public void HandleGetStudentsInfo(String Student, TextArea studentGrade) throws SQLException{
        studentGrade.clear();
        studentGrade.appendText("Student Info: ");
        ArrayList<StudentInfo> Info= model.PstmtGetCoursesAndGradesFromStudentName(Student);
        for (int i = 0; i < Info.size(); i++) {
            String Course = String.format(Info.get(i).CourseName);
            Integer Grade = Info.get(i).Grade;
            studentGrade.appendText(Student + " on course " + Course + " has gotten " + Grade + " as their grade\n");
        }
    }

    public void HandleGetStudentAVGGrade(String Student, TextArea studentGrade) throws SQLException{
        studentGrade.clear();
        studentGrade.appendText(Student + " has an AVG grade of ");
        String Info = model.PstmtAVGGradeFromStudentName(Student);
        studentGrade.appendText(Info + "\n");
    }

    public void HandleGetCourseAVGGrade(String Course, TextArea CourseGrade) throws SQLException{
        CourseGrade.clear();
        CourseGrade.appendText(Course + " has an AVG grade of ");
        String Info = model.PstmtAVGGradeFromCourse(Course);
        CourseGrade.appendText(Info + "\n");
    }
}
