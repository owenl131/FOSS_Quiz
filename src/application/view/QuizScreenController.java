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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class QuizScreenController 
{
	@FXML private RadioButton radio1;
	@FXML private RadioButton radio2;
	@FXML private RadioButton radio3;
	@FXML private RadioButton radio4;
	
	@FXML private Text question;
	@FXML private Text option1;
	@FXML private Text option2;
	@FXML private Text option3;
	@FXML private Text option4;
	
	@FXML private Text exp1;
	@FXML private Text exp2;
	@FXML private Text exp3;
	@FXML private Text exp4;
	
	@FXML private Button submit;
	@FXML private ProgressIndicator progress;
	@FXML private Label errorMessage;
	@FXML private Label scoreBoard;
	@FXML private ProgressBar questionProgress;
	
	private Text[] options;
	private RadioButton[] buttons;
	private Text[] exps;//explanations
	private ToggleGroup group;
	private int qNumber;
	private Database database;
	private boolean paused = false;
	private Question currentQuestion;
	private int timeSeconds = 20;
	private final int totalTime = 20;
	private Timeline timeline;
	
	@FXML
	private void initialize()
	{}
	
	@FXML
	private void submitClicked()
	{
		if (paused) return;
		if (group.selectedToggleProperty().getValue() == null)
		{
			errorMessage.setText("Error! Choose an option before submitting!");
			return;
		}
		for (int i = 0; i < 4; i++)
		{
			if (buttons[i].isSelected())
			{
				errorMessage.setText("Option " + i+1 + " chosen");
				timeline.stop();
				if (currentQuestion.checkAnswer(i))
				{
					errorMessage.setText("Correct!");
					MenuController.score++;
				}
				else
				{
					errorMessage.setText("Wrong answer...");
				}
				updateScore();
			}
		}
		toNextQuestion();
    }
	
	private void showExplanations()
	{
		for (int i = 0; i < 4; i++)
		{
			exps[i].setText(currentQuestion.getExps()[i]);
		}
	}
	
	private void toNextQuestion()
	{
		errorMessage.setText(errorMessage.getText() + " Next question coming in 5 seconds...");
		showExplanations();
		//code from http://stackoverflow.com/questions/26454149/make-javafx-wait-and-continue-with-code
		Task<Void> sleeper = new Task<Void>() 
		{
            @Override
            protected Void call() throws Exception 
            {
                try 
                {
                    paused = true;
                    int delay = 5000;
                	Thread.sleep(delay);
                } 
                catch (InterruptedException e) 
                {}
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() 
        {
            @Override
            public void handle(WorkerStateEvent event) 
            {
            	if (qNumber < 10)
            		initPage(qNumber+1, database);
            	else
            	{
            		try
            		{
	            		FXMLLoader loader = new FXMLLoader();
	            		loader.setLocation(Main.class.getResource("view/Summary.fxml"));
	            		//change to loading summary page
	            		AnchorPane pane = loader.load();
	            		Scene scene = new Scene(pane);
	            		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
	            		Main.MainStage.setScene(scene);
	            		paused = false;
            		}
            		catch(IOException e)
            		{
            			e.printStackTrace();
            		}
            	}
            }
        });
        Thread thread = new Thread(sleeper);
        thread.start();
	}
	
	public void initPage(int qNumber, Database d)
	{
		this.qNumber = qNumber;
		errorMessage.setText("");
		paused = false;
		group = new ToggleGroup();
		database = d;
		options = new Text[4];
		buttons = new RadioButton[4];
		exps = new Text[4];
		options[0] = option1;
		options[1] = option2;
		options[2] = option3;
		options[3] = option4;
		buttons[0] = radio1;
		buttons[1] = radio2;
		buttons[2] = radio3;
		buttons[3] = radio4;
		exps[0] = exp1;
		exps[1] = exp2;
		exps[2] = exp3;
		exps[3] = exp4;
		updateScore();
		group.getToggles().addAll(buttons);
		Question q = d.getQuestion(qNumber-1);
		question.setText(qNumber + ". " + q.getQuestion());
		for (int i = 0; i < 4; i++)
		{
			options[i].setText(q.getOptions()[i]);
			exps[i].setText("");
		}
		startCountdown();
		currentQuestion = q;
	}
	
	private void startCountdown()
	{
		timeSeconds = 20;
		//code from http://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/
		timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
        		new KeyFrame(Duration.seconds(1),
        				new EventHandler<ActionEvent>() 
        				{
        					// KeyFrame event handler
        					public void handle(ActionEvent event) 
        					{
		                        timeSeconds--;
		                        progress.setProgress((double)(totalTime - timeSeconds)/totalTime);
		                        // update timerLabel
		                        if (timeSeconds <= 0) 
		                        {
		                            
		                        	errorMessage.setText("Too bad, time exceeded...");
		                        	toNextQuestion();
		                        	timeline.stop();
		                        }
		                    }
        				}
        ));
        timeline.playFromStart();
	}
	
	private void updateScore()
	{
		questionProgress.setProgress((double)(qNumber-1)/Main.numberOfQuestions);
		scoreBoard.setText("Score: " + MenuController.score + " / " + Main.numberOfQuestions);
	}
	
}
