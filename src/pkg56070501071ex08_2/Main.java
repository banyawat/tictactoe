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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Main extends Application {
    ImageView[][] ticPos = new ImageView[3][3];
    ImageView playerSideImage = new ImageView();
    TicTac games = new TicTac();

    File circleImageFile = new File("circle.png");
    File crossImageFile = new File("cross.png");
    File squareImageFile = new File("square.png");

    Image circleImage;
    Image crossImage;
    Image squareImage;

    Alert annoucer = new Alert(Alert.AlertType.INFORMATION);
    Alert initMessage = new Alert(Alert.AlertType.INFORMATION);
    ButtonType tryAgainButton = new ButtonType("Try Again");
    ButtonType start1 = new ButtonType("Start as X");
    ButtonType start2 = new ButtonType("Start as O");
    ButtonType closeButton = new ButtonType("Close");
    
    private static final Integer STARTTIME = 8;
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME*100);
    
    @Override
    public void start(Stage primaryStage) {
        initMessage.setTitle("Welcome message");
        initMessage.setHeaderText("Welcome To TIC-TAC-TOE 2017");
        initMessage.setContentText("Instruction\nEach turn have a time limit.\n When ready, pick 1st player's side.");
        initMessage.getButtonTypes().setAll(start1, start2, closeButton);

        annoucer.setTitle("Congratulation!");
        annoucer.getButtonTypes().setAll(tryAgainButton, closeButton);
        
        SplitPane root = new SplitPane();
        GridPane leftView = new GridPane();

        circleImage = new Image(circleImageFile.toURI().toString());
        crossImage = new Image(crossImageFile.toURI().toString());
        squareImage = new Image(squareImageFile.toURI().toString());
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ticPos[i][j] = new ImageView(squareImage);
                ticPos[i][j].setPickOnBounds(true);
                leftView.add(ticPos[i][j], i, j);
            }
        }
        
        for(Node node : leftView.getChildren()){
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int col = GridPane.getColumnIndex(node);
                    int row = GridPane.getRowIndex(node);
                    if(games.setHole(games.getCurrentPlayer(), col, row)){
                        int winner = games.checkIfWin();
                        games.swapCurrentPlayer();
                        updateImage();
                        if(winner!=-1){
                            String winnerAnnounce = "Player " + winner + " has won";
                            annoucer.setHeaderText(winnerAnnounce);
                            annoucer.setContentText("Dear Loser...\nTRY AGAIN OR SURRENDER(Close)");
                            Optional<ButtonType> loserResult = annoucer.showAndWait();
                            if(loserResult.get()==tryAgainButton){
                                restartPane(primaryStage);
                            }
                            else{
                                primaryStage.close();
                            }
                        }
                        else{
                            if(games.checkIfDraw()){
                            Alert drawAlert = new Alert(Alert.AlertType.INFORMATION);
                            drawAlert.setHeaderText("DRAW");
                            drawAlert.setContentText("Try again");
                            drawAlert.showAndWait();
                            restartPane(primaryStage);

                        }
                        }
                    }
                }
            });
        }
        
        VBox rightView = new VBox();
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(
                timeSeconds.divide(STARTTIME*100.0).subtract(1).multiply(-1));
        
        rightView.getChildren().add(progressBar);
        
        Label playerTurnLabel = new Label("Player's turn");
        rightView.getChildren().add(playerTurnLabel);
        rightView.getChildren().add(playerSideImage);
        root.setDividerPositions(0.7f, 0.3f);
        root.getItems().add(leftView);
        root.getItems().add(rightView);
        Scene scene = new Scene(root, 600, 450);
        
        primaryStage.setTitle("Tic Tac Toe 2017 - By Banyawat");
        primaryStage.setScene(scene);
        Optional<ButtonType> welcomeResult = initMessage.showAndWait();
        primaryStage.show();
        
        if(welcomeResult.get() == start1){
            games.setCurrentPlayer(0);
            playerSideImage.setImage(crossImage);
            startTimer();
        }
        else if(welcomeResult.get() == start2){
            games.setCurrentPlayer(1);
            playerSideImage.setImage(circleImage);
        }
        else{
            primaryStage.close();
        }
    }

    private void updateImage(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int val = games.getHole(i,j);
                if(val==0){
                    ticPos[i][j].setImage(crossImage);
                }
                else if(val==1){
                    ticPos[i][j].setImage(circleImage);
                }
                else{
                    ticPos[i][j].setImage(squareImage);
                }
            }
        }

        if(games.getCurrentPlayer()==0)
            playerSideImage.setImage(crossImage);
        else
            playerSideImage.setImage(circleImage);
    }

    private void restartPane(Stage primaryStage){
        primaryStage.close();
        initMessage.setTitle("Welcome message");
        initMessage.setHeaderText("Welcome To TIC-TAC-TOE 2017");
        initMessage.setContentText("Instruction\nEach turn have a time limit.\n When ready, pick 1st player's side.");
        games = new TicTac();
        Optional<ButtonType> welcomeResult = initMessage.showAndWait();
        updateImage();
        primaryStage.show();
        if(welcomeResult.get() == start1){
            games.setCurrentPlayer(0);
            playerSideImage.setImage(crossImage);
        }
        else if(welcomeResult.get() == start2){
            games.setCurrentPlayer(1);
            playerSideImage.setImage(circleImage);
        }
        else{
            primaryStage.close();
        }
    }
    
    private void startTimer(){
        if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set((STARTTIME+1)*100);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Time's up");
                    }
                });
                timeline.playFromStart();
    }

    public static void main(String[] args) {
        launch(args);
    }    
}
