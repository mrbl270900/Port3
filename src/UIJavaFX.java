import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class UIJavaFX {
    GridPane startview;
    Button exitBtn;
    Button GetGradesStudent;
    Button GetGradesCourse;
    Button GetStudentsInfo;
    Button SetGrade;
    ComboBox StudentComB;
    ComboBox CourseComB;
    ComboBox GradeSelComB;
    ObservableList<String> Students;
    ObservableList<String> Courses;
    ObservableList<String> Grades;
    TextArea StudentGrade;

    public UIJavaFX(){
        startview=new GridPane();
        CreateView();
    }
    public Parent asParent() {return startview;}

    private void CreateView(){
        startview.setMinSize(300,200);// Setting size of the pane(width, height)
        startview.setPadding(new Insets(10,10,10,10)); //margins around the grid(top/right/bottom/left)setPadding(Insetsi)
        startview.setVgap(5);
        startview.setHgap(5);
        exitBtn = new Button("Exit");
        GetGradesCourse = new Button("Get Grades From Course");
        GetGradesStudent = new Button("Get Grades From Student");
        GetStudentsInfo = new Button("Get Students Info");
        SetGrade = new Button("Set Grade");
        StudentComB = new ComboBox();
        CourseComB = new ComboBox();
        GradeSelComB = new ComboBox();
        StudentGrade = new TextArea();
        startview.add(exitBtn,20,20);
        startview.add(StudentComB,1,1);
        startview.add(CourseComB,2,1);
        startview.add(GradeSelComB, 3,1);
        startview.add(GetGradesCourse,2,2);
        startview.add(GetGradesStudent,1,2);
        startview.add(GetStudentsInfo,1,3);
        startview.add(SetGrade,3,2);
        startview.add(StudentGrade,1,4,3,4);

    }

    public void configure(){
        StudentComB.setItems(Students);
        StudentComB.getSelectionModel().selectFirst();
        CourseComB.setItems(Courses);
        CourseComB.getSelectionModel().selectFirst();
        GradeSelComB.setItems(Grades);
        GradeSelComB.getSelectionModel().selectFirst();

    }
}
