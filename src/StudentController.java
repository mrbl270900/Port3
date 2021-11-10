import javafx.application.Platform;

import java.sql.SQLException;

public class StudentController {
    UIJavaFX view;
    StudentModel model;

    public StudentController (StudentModel m, UIJavaFX v) throws SQLException {
        this.view = v;
        this.model = m;
        this.view.exitBtn.setOnAction(e -> Platform.exit());
        this.view.configure();
    }
}
