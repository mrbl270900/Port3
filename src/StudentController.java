import javafx.application.Platform;

import java.sql.SQLException;

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
        this.view.configure();
    }


    public void HandleGetStudentsGrades(){

    }
}
