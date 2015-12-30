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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SummaryController 
{
	@FXML
	private Text score;
	@FXML
	private Text conclusion;
	
	@FXML
	private void initialize()
	{
		String message = "";
		message += "To conclude...\n";
		message += "Think about FOSS as Free as in Speech, not as in free beer.\n";
		message += "What does free beer mean? \n";
		message += "Free beer is a gift given to you. \nIt is a gift, and does not involve anything for the receiver to do on his part.\n";
		message += "Free Beer = No Cost\n\n";
		message += "Free speech brings it one step deeper. \n";
		message += "You have the liberty to express your opinions. \nYou have the right to use and run the software however you like. \n";
		message += "You have the right to see how the software works (source code). On the other hand, you are not told the secret ingredients used in your free beer.\n";
		message += "You have the right to redistribute the software. The bartender wouldn't like that.\n";
		message += "You have the right to improve the program.\n";
		message += "FOSS is not just about the price. The key thing is the Freedom and Unrestrictedness.";
		conclusion.setText(message);
		score.setText("Your final score is " + MenuController.score + " / " + Main.numberOfQuestions + ". (" + ((double)MenuController.score*100/Main.numberOfQuestions) + "%)");
	}
	
	@FXML
	private void toMenu()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Menu.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			Main.MainStage.setScene(scene);
		}
		catch(IOException e)
		{
			
		}
	}
	
}
