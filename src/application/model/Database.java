/*
 * Program: FOSS Quiz
 * Author: Owen Leong
 * Date: 30/12/2015
 * Description: Quiz on Free and Open source Software, created using javafx, 
 * 				for the Google Code-in 2015-2016
 */
package application.model;

import java.util.ArrayList;
import java.util.Random;

public class Database 
{
	private ArrayList<Question> questions;
	//List of all questions
 	
	public Database()
	{
		questions = new ArrayList<Question>(15);
		generateQuestions();
	}
	
	public Question getQuestion(int index)
	{
		return questions.get(index);
	}
	
	public ArrayList<Question> getList()
	{
		return questions;
	}
	
	public void shuffleQuestions()
	{
		shuffleQuestions(questions.size() * questions.size()); 
		//arbitrary number, must ensure that questions appear randomized
	}
	//function may not be used to ensure questions appear in order of difficulty and importance
	
	public void shuffleQuestions(int times)
	{
		Random r = new Random();
		r.setSeed(times);
		for (int i = 0; i < times; i++)
		{
			int a = r.nextInt(questions.size()), b = r.nextInt(questions.size());
			Question temp = questions.get(a);
			questions.set(a, questions.get(b));
			questions.set(b, temp);
		}
	}
	
	private void generateQuestions()
	{
		//Hardcoded
		
		//Resources taken from the following websites
		//www.webopedia.com
		//opensource.org
		//opensource.com
		//http://www.gnu.org/philosophy/free-software-for-freedom.en.html
		//and other reference sites
		
		Question q;
		//0
		q = new Question("What does FOSS stand for?", 
				new String[]{"Free and Open Source Software", "Free Option Science System", 
				"Free of Salt and Sugar", "Fully-Operational and Safe Software"}, 
				new String[]{"Answer", "", "", ""}, true);
		questions.add(q);
		//1
		q = new Question("What does Open-Source mean? Software that can be freely...\n" + 
				"a) Accessed\nb) Used\nc) Modified\nd) Shared\ne) Shared, but restricted to certain users", 
				new String[]{"a, b, c and d", "a and d", "a, b and c", "a, b, c and e"}, 
				new String[]{"Answer", "", "", "Open source licenses do not discriminate against fields of endeavor."},true);
		questions.add(q);
		//2
		q = new Question("Read this scenerio. A man downloads some FOSS software, and 1hr after using it," + 
		" his computer blows up in his face. Can he sue the developers? Why?", 
				new String[]{"No. You are free to use open source software for any purposes, and almost all open source licenses state that there is no warranty.", 
				"Yes. The developers have an obligation to ensure the safety of their software, hence he has a right to sue them for negligence.", 
				"Yes. Open source code must be thoroughly tested before distribution.", 
				"No. Open source licenses state that programs must be operational for at least 1 minute. After the minute, anything can happen to the program."}, 
				new String[]{"Answer", "", "Which developers should he sue, anyway? Still, it is the developers' duty to ensure that programs perform well", 
				"Nonsense"}, true);
		questions.add(q);
		//3
		q = new Question("Since anyone can modify and distribute open source code, is it possible to make money from open source code? How or why?", 
				new String[]{"Yes, sell warranties and maintenance work. ie. Sell your services", 
				"No, open source code can only be distributed for free.", 
				"Yes, earn through royalties (an amount of money that is paid to the original creator based on how many copies have been sold)", 
				"No, open source work is non-profitable."}, 
				new String[]{"Answer", "", "This is not allowed under the license and defeats the purpose of making open source software", ""}, true);
		questions.add(q);
		//4
		q = new Question("How many of the following are benefits of open source software above Proprietary software?\n" + 
				"a) Allows for peer review to more effectively find and fix bugs in code.\n" + 
				"b) Allows for faster software evolution.\n" + 
				"c) Allows for greater software security.\n" + 
				"d) Allows for more collaboration between the programming community.\n" + 
				"e) Allows for more control for users over the software.", 
				new String[]{"5", "4", "2", "3"}, 
				new String[]{"Answer", "", "", ""}, true);
		questions.add(q);
		//5
		q = new Question("How many of the following are disadvantages of open source software as compared to commercially produced software?\n" + 
				"a) Open source software is generally not straightforward to use, and is especially difficult for new users to learn.\n" + 
				"b) A lot of the present hardware and applications are incompatible with open source software.\n" +
				"c) Vulnerable to malicious users, software might not be secure.", 
				new String[]{"3", "2", "1", "0"}, 
				new String[]{"Answer", "", "", ""}, true);
		questions.add(q);
		//6
		q = new Question("If you develop FOSS, does this mean that others can use your trademarks, logos, etc? Why?", 
				new String[]{"No. Open source is about the source code, not the identity of developers.",
				"Yes. It is stated in the Open Source Licenses for software.", 
				"Yes. All trademarks and logos are under Open Source licenses.", 
				"No. Trademarks and logos can only be used with consent from the company owning them, in all circumstances."}, 
				new String[]{"Answer", "", "", ""}, true);
		questions.add(q);
		//7
		q = new Question("Which of the following is not an open source license?", 
				new String[]{"Creative Commons Zero (CC0)", "Academic Free License 3.0 (AFL 3.0)", 
				" GNU General Public License (GPL)", "Fair License"}, 
				new String[]{"Answer. CC0 has not been approved as an open source license", "", "", ""}, true);
		questions.add(q);
		//8
		q = new Question("Which of the following is the most accurate definition of \"CopyLeft\"?", 
				new String[]{"CopyLeft signifies that the public retains the freedom to use, modify, extend and redistribute a creative work and all derivative works.", 
				"CopyLeft signifies that the software is in the public domain.", 
				"CopyLeft signifies that the software contains radical and reforming views.", 
				"CopyLeft signifies that the software cannot be copyrighted."}, 
				new String[]{"Answer", "CC0 signifies that it is in the public domain", "Unfortunately, not directly related to leftism", 
				"CopyLeft is not opposite of CopyRight, in fact, they are rather similar"}, true);
		questions.add(q);
		//9
		q = new Question("How many of the following are true about the free software movement and the open source movement?\n" + 
				"a) Free software is a subset of open source software\n" + 
				"b) Free software is an ethical change, Open source software is a practical change\n" + 
				"c) \"Free software\" has an issue of ambiguity in language used, \"Open source\" does not\n", 
				new String[]{"2", "0", "1", "3"}, 
				new String[]{"Answer. a and b are true, c is false.", "", "", ""}, true);
		questions.add(q);
		//10 questions altogether
	}
	
}
