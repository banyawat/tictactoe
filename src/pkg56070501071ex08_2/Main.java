package pkg56070501071ex08_2;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    ImageView[][] ticPos = new ImageView[3][3];
    
    @Override
    public void start(Stage primaryStage) {
        Alert initMessage = new Alert(Alert.AlertType.INFORMATION);
        ButtonType start1 = new ButtonType("Start as X");
        ButtonType start2 = new ButtonType("Start as O");
        ButtonType cancel = new ButtonType("Cancel");
        initMessage.setTitle("Welcome message");
        initMessage.setHeaderText("Welcome To TIC-TAC-TOE 2017");
        initMessage.setContentText("Instruction\nEach turn have a time limit. hen ready, pick your side.");
        initMessage.getButtonTypes().setAll(start1, start2, cancel);
        
        SplitPane root = new SplitPane();
        GridPane leftView = new GridPane();
        
        File circleImage= new File("circle.png");
        Image image = new Image(circleImage.toURI().toString());
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ticPos[i][j] = new ImageView(image);
                ticPos[i][j].setPickOnBounds(true);
                leftView.add(ticPos[i][j], i, j);
            }
        }
        
        for(Node node : leftView.getChildren()){
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Clicked On (" + GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node)+ ")");
                }
            });
        }
        
        VBox rightView = new VBox();
        root.getItems().add(leftView);
        root.getItems().add(rightView);
       
       
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 900, 500);
        
        primaryStage.setTitle("Tic Tac Toe 2017 - By Banyawat");
        primaryStage.setScene(scene);
        Optional<ButtonType> welcomeResult = initMessage.showAndWait();
        primaryStage.show();
        
        if(welcomeResult.get() == start1){
            System.out.println("Start as X");
            TicTac games = new TicTac();
            games.checkIfWin();
        }
        else if(welcomeResult.get() == start2){
            
        }
        else{
            primaryStage.close();
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
