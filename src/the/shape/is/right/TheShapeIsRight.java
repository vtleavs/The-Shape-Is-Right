/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.shape.is.right;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ben
 */
public class TheShapeIsRight extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Button toGameButton = new Button("Go To Game Pane");
        Button toResultsButton = new Button("Go To Results Pane");
        
        VBox optionsPane = new VBox();
        VBox gamePane = new VBox();
        VBox resultsPane = new VBox();
        
        Scene scene = new Scene(optionsPane, 600, 600);
        
        // Add nodes to optionsPane
        optionsPane.getChildren().add(new Label("This is the Options Pane"));
        optionsPane.getChildren().add(toGameButton);
        
        // Add nodes to gamePane
        gamePane.getChildren().add(new Label("This is the Game Pane"));
        gamePane.getChildren().add(toResultsButton);
        
        // Add nodes to resultsPane
        resultsPane.getChildren().add(new Label("This is the Results Pane"));
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        toGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                scene.setRoot(gamePane);
            }
        });
        
        toResultsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                scene.setRoot(resultsPane);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
