/*
 * Program: FOSS Quiz
 * Author: Owen Leong
 * Date: 30/12/2015
 * Description: Quiz on Free and Open source Software, created using javafx, 
 * 				for the Google Code-in 2015-2016
 */
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application 
{
	public static Stage MainStage;
	//stage for all parts of the application, except the information page
	public final static int numberOfQuestions = 10;
	//should be changed if more questions are added
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			//Loads the menu screen from fxml and shows it on the main stage
			MainStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("FOSS Quiz");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Menu.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainStage.setScene(scene);
			MainStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
