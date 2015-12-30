/*
 * Program: FOSS Quiz
 * Author: Owen Leong
 * Date: 30/12/2015
 * Description: Quiz on Free and Open source Software, created using javafx, 
 * 				for the Google Code-in 2015-2016
 */
package application.model;

import java.util.Random;

public class Question {
	
	private String question;
	private String[] options;
	private String[] explanations;
	private String answer;
	//stores question, 4 options and answer as strings
	
	public Question(String question, String[] choices, String[] exps, boolean shuffle)
	{
		options = new String[4];
		explanations = new String[4];
		this.question = question;
		if (choices.length < 4 || exps.length < 4)
		{
			System.out.println("Error: Options not correctly initialised");
			return;
		}
		answer = choices[0];
		for (int i = 0; i < 4; i++)
		{
			options[i] = choices[i];
			explanations[i] = exps[i];
		}
		if (shuffle)
			shuffleOptions();
	}
	
	private void shuffleOptions()
	{
		Random r = new Random();
		for (int i = 0; i < 28; i++)
		{
			int a = r.nextInt(4), b = r.nextInt(4);
			String temp = options[a];
			options[a] = options[b];
			options[b] = temp;
			temp = explanations[a];
			explanations[a] = explanations[b];
			explanations[b] = temp;
		}
	}
	
	public boolean checkAnswer(int ans)
	{
		return options[ans].equals(answer);
	}
	
	public boolean checkAnswer(String ans)
	{
		return answer.equals(ans);
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String[] getOptions()
	{
		return options;
	}
	
	public String[] getExps()
	{
		return explanations;
	}
	
	
}
