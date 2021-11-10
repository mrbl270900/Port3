import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

    public class Main extends Application {
        public void start(Stage primaryStage) {
            UIJavaFX view=new UIJavaFX();
            StudentModel model = new StudentModel("jdbc:sqlite:C:/Users/madsr/IdeaProjects/Port3/src/StudentDatabase");
            StudentController controller = null;

            try {
                controller = new StudentController(model, view);
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            primaryStage.setTitle("Student Admin");
            primaryStage.setScene(new Scene(view.asParent(), 600, 475));
            primaryStage.show();
        }
        public static void main(String[] args){
            launch(args);
        }
    }
