package assignment5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.*;


public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		draw(primaryStage);
	}
	public void draw(Stage primaryStage) throws Exception {
		try {
			/*
			 * Create the first window with the grid of shapes
			 */
			int gridx = 100;
			int gridy = 200;
			GridPane grid = new GridPane(); // Holds the painted grid
			// initial display
			Critter.displayWorld(grid);
			Scene scene = new Scene(grid, 12*Params.WORLD_WIDTH, 12*Params.WORLD_HEIGHT);
			primaryStage.setX(gridx);
			primaryStage.setY(gridy);
			primaryStage.setTitle("First Stage"); 		
			primaryStage.setScene(scene); // puts the scene onto the stage
			primaryStage.show(); // display the stage with the scene
			
			// second stage with tabs
			Stage secondStage = new Stage(); // creates a second stage for the button.
			// set coords
			secondStage.setX(gridx + 12 * Params.WORLD_WIDTH);
			secondStage.setTitle("Options"); 		
			// critter tab
			Tab critters = new Tab("Critters");
			HBox stackpane = new HBox();
			Button create = new Button("Create");
			ObservableList<String> crits =
				    FXCollections.observableArrayList(
				        "Goblin",
				        "Clover"
				    ); //TODO - fix hardcoding with method to get classes
			ComboBox<String> comboBox = new ComboBox<String>(crits);
			TextField text = new TextField("Amount");
			// ADD TO HBOX
			stackpane.getChildren().add(text);
			stackpane.getChildren().add(comboBox);
			stackpane.getChildren().add(create);
			critters.setContent(stackpane);
			// simulation tab
			Tab simulation = new Tab("Simulation");
			StackPane simpane = new StackPane();
			simulation.setContent(simpane);
			// world tab
			Tab world = new Tab("World");
			HBox worldpane = new HBox();
			Button display = new Button("Display World");	// create a Button object
			Button step = new Button("STEP");	// create a Button object
			Button clear = new Button("Clear");
			TextField steptext = new TextField("Steps:");
			
			worldpane.getChildren().add(clear);
			worldpane.getChildren().add(steptext);
			worldpane.getChildren().add(step);
			worldpane.getChildren().add(display);
			world.setContent(worldpane);
			// tabpane
			TabPane pane = new TabPane(world, critters,simulation);
			pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

			
			// SECOND SCENE
			Scene secondScene = new Scene(pane, 300, 300); 
			secondStage.setScene(secondScene); 
			secondStage.show(); 

			/*
			 * Action to be performed when display world button is pressed.
			 */
			display.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					Critter.displayWorld(grid);
				}
			});
			clear.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					Critter.clearWorld();
					Critter.displayWorld(grid);
				}
			});
			step.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
				@Override
				public void handle(ActionEvent event) {
					int num = 0;
					String input = steptext.getText();
					//TODO handle bad input
					if(input.length()>0) {
						try{
							num = Integer.parseInt(input);
						}
						catch(Exception e) {
							return;
						}
					}
					else num = 1;
					for(int i = 0; i< num; i++)
					{
						Critter.worldTimeStep();
					}
					Critter.displayWorld(grid);
				}
			});
			create.setOnAction(new EventHandler<ActionEvent>() { // create critter chosen
				//TODO - handle bad input
				@Override
				public void handle(ActionEvent event) {
					String input = text.getText();
					String crit = comboBox.getValue();
					
					int num = 0;
					try {
					num = Integer.parseInt(input);
					} catch(Exception e) {
						//return;
					}
					for(int i = 0; i < num; i++) {	
							try {
								Critter.createCritter(crit);
							} catch (InvalidCritterException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					Critter.displayWorld(grid);
				}
			});

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public ObservableList<String> getClasses(){
		
		
		
		
		return null;
	}
	
	
	
	
}
