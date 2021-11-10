import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class UIJavaFX {
    GridPane startview;
    Button exitBtn;

    public UIJavaFX(){
        startview=new GridPane();
        CreateView();
    }
    public Parent asParent() {return startview;}

    private void CreateView(){
        startview.setMinSize(300,200);// Settingsizeof the pane(width, height)
        startview.setPadding(new Insets(10,10,10,10)); //margins around the grid(top/right/bottom/left)setPadding(Insetsi)
        startview.setVgap(5);
        startview.setHgap(5);
        exitBtn=new Button("Exit");
        startview.add(exitBtn,20,20);

    }

    public void configure(){


    }
}
