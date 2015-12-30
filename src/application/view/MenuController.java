/*
 * Program: FOSS Quiz
 * Author: Owen Leong
 * Date: 30/12/2015
 * Description: Quiz on Free and Open source Software, created using javafx, 
 * 				for the Google Code-in 2015-2016
 */
package application.view;

import java.io.IOException;

import application.Main;
import application.model.Database;
import application.model.Question;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML private Button start;
	@FXML private Button moreInfo;
	
	@FXML private ImageView bitcoin;
	@FXML private ImageView cellprofiler;
	@FXML private ImageView chemdevkit;
	@FXML private ImageView firefox;
	@FXML private ImageView gimp;
	@FXML private ImageView imagej;
	@FXML private ImageView inkscape;
	@FXML private ImageView linux;
	@FXML private ImageView openx;
	@FXML private ImageView opera;
	@FXML private ImageView vlmc;
	
	private int questionNumber;
	private QuizScreenController questionController;
	private Database questionDatabase;
	public static int score;
	
	@FXML
	private void initialize()
	{
		//sets all images on the menu screen
		//for aesthetic purposes
		bitcoin.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/bitcoin.png")));
		cellprofiler.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/cellprofiler.png")));
		chemdevkit.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/chemdevkit.png")));
		firefox.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/firefox.png")));
		gimp.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/gimp.png")));
		imagej.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/imagej.png")));
		inkscape.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/inkscape.png")));
		linux.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/linux.png")));
		openx.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/openx.png")));
		opera.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/opera.png")));
		vlmc.setImage(new Image(ClassLoader.getSystemResourceAsStream("application/view/Images/vlmc.png")));
	}
	
	public MenuController()
	{
		//resets variables
		questionNumber = 1; //1 index
		score = 0;
		questionDatabase = new Database();
	}
	
	public Question getQuestion(int index)
	{
		return questionDatabase.getQuestion(index);
	}
	
	@FXML
	private void moreInfoPressed()
	{
		try
		{
			//opens the info page on a separate window
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Information.fxml"));
			AnchorPane pane = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e)
		{
			
		}
		
	}
	
	@FXML
	private void startPressed()
	{
		try
		{
			//changes current window to quiz screen
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/QuizScreen.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			Main.MainStage.setScene(scene);
			questionController = (QuizScreenController)loader.getController();
			questionController.initPage(questionNumber, questionDatabase);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
		}
	}
	
}
