package id316397249_id208945956.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	/* To support Multiple subjects' tests, we need to do the following changes\additions:
	 * Create new class that features an array of managers. Each manager will be responsible for specific subject.
	 * In addition, for each case in the main program, the program will ask the user for which subject does he wants to apply the change.
	 */
		
		
		static Manager test = new Manager();
		static Scanner input = new Scanner(System.in);
		

	public static void main(String[] args) {
		List <Manager> allTests = new ArrayList<Manager>();
			try {
				test.inFile();
			} catch (ClassNotFoundException | IOException e1) {
				System.out.println("Didn't load the questions source. starts with default questions. \n");
				test.initial();
			}
		int choice = 0;
		while (choice != 10) {
			System.out.println("Welcome \nThis is a Quiz Bulider.\nChoose number from the menu:");
			System.out.println("1) Show all the questions and the answers of each question.");
			System.out.println("2) Add a question and an answer.");
			System.out.println("3) Edit a question text.");
			System.out.println("4) Edit an answer for a specific question.");
			System.out.println("5) Delete an answer for a specific question.");
			System.out.println("6) Create a manual test.");
			System.out.println("7) Create an automatic test. Sorted by alphabetic order.");
			System.out.println("8) Create an automatic test. Sorted by question's length.");
			System.out.println("9) Create a test as a copy of existing one.");
			System.out.println("10) Save & Exit");

			choice = inputBetween(1, 10);
			switch (choice) {
			case 1: {
				System.out.println(test.toString());
				break;
			}

			case 2: {
				int typeOfQuestion = 0;
				System.out.println("for open question press 1.\nfor multi Choice question press 2.");
				typeOfQuestion = inputBetween(1, 2);
				if (typeOfQuestion == 1) {
					if (test.addQuestion(addOpenQuestion())) {
						System.out.println("Question added succesfully");
					} else
						System.out.println("Question didn't add");
				} else if (typeOfQuestion == 2) {
					if (test.addQuestion(addMultiQuestion())) {
						System.out.println("Question added succesfully");
					} else
						System.out.println("Question didn't add");
				} else
					System.out.println("You chose incorrect number. Going back to menu");
				break;
			}
			case 3: {
				if (test.getNumOfQuestions() == 0) {
					System.out.println("There are no questions in the system");
					break;
				}
				int numberOfQuestion = 0;
				String newQuestion = "";
				System.out.println("What number of question do you want to edit? \nChoose from 1 to "
						+ test.getNumOfQuestions() + "\n");
				numberOfQuestion = (inputBetween(1, test.getNumOfQuestions()) - 1);
				System.out.println("The old question is: \n" + test.getQuestions().get(numberOfQuestion).getText() + "\n");
				System.out.println("What is the new Question? \n");
				newQuestion = input.nextLine();
				if (test.editQuestion(test.getQuestions().get(numberOfQuestion), newQuestion)) {
					System.out.println("Question edited succesfully. \n");
				} else
					System.out.println("Question didn't edit. \n");
				break;
			}
			case 4: {
				if (test.getNumOfQuestions() == 0) {
					System.out.println("There are no questions in the system");
					break;
				}
				int numberOfQuestion = 0;
				int numberOfAnswer = 0;
				int isAnsTrue = 0;
				String newAnswer = "";
				System.out.println("What number of question do you want to edit his answer? \nChoose from 1 to "
						+ test.getNumOfQuestions() + "\n");
				numberOfQuestion = (inputBetween(1, test.getNumOfQuestions()) - 1);
				System.out.println("Question chose is: \n" + test.getQuestions().get(numberOfQuestion).toString());
				if (test.getQuestions().get(numberOfQuestion) instanceof MultiChoiceQuestion) {
					if (((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion)).getNumOfAnswers() == 0) {
						System.out.println("There are no answers in the question");
						break;
					}
					System.out.println("What number of answer would you like to edit?\n");
					numberOfAnswer = (inputBetween(1,
							((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion)).getNumOfAnswers()) - 1);
					System.out.println("What is the new answer?\n");
					newAnswer = input.nextLine();
					System.out.println("If the new answer is true press 1, else press 0");
					isAnsTrue = inputBetween(0, 1);
					Answer ansTemp = new Answer(newAnswer, isAnsTrue == 1);
					if (test.editMultiAnswer(test.getQuestions().get(numberOfQuestion), numberOfAnswer, ansTemp)) {
						System.out.println("Answer edited succesfully.\n");
					} else
						System.out.println("Answer didn't edit.\n");
				} else {
					System.out.println("What is the new answer?\n");
					newAnswer = input.nextLine();
					if (test.editOpenAnswer(test.getQuestions().get(numberOfQuestion), newAnswer)) {
						System.out.println("Answer edited succesfully.\n");
					} else
						System.out.println("Answer didn't edit.\n");
				}
				break;
			}
			case 5: {
				if (test.getNumOfQuestions() == 0) {
					System.out.println("There are no questions in the system");
					break;
				}
				int numberOfQuestion = 0;
				int numberOfAnswer = 0;
				System.out.println("What number of question do you want to delete his answer? \nChoose from 1 to "
						+ test.getNumOfQuestions());
				numberOfQuestion = (inputBetween(1, test.getNumOfQuestions()) - 1);
				System.out.println("Question chose is: \n" + test.getQuestions().get(numberOfQuestion).toString());
				if (test.getQuestions().get(numberOfQuestion) instanceof MultiChoiceQuestion) {
					if (((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion)).getNumOfAnswers() == 0) {
						System.out.println("There are no answers in the question");
						break;
					}
					System.out.println("What number of answer would you like to delete? choose from 1 to "
							+ ((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion)).getNumOfAnswers() + "\n");
					numberOfAnswer = (inputBetween(1,
							((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion)).getNumOfAnswers()) - 1);
					if (test.getQuestions().get(numberOfQuestion) instanceof MultiChoiceQuestion) {
						if (test.deleteAnswer(test.getQuestions().get(numberOfQuestion),
								((MultiChoiceQuestion) test.getQuestions().get(numberOfQuestion))
										.getAnswer(numberOfAnswer))) {
							System.out.println("Answer deleted succesfully.\n");
						} else
							System.out.println("Answer didn't delete.\n");
					}
				} else
					System.out.println("You cannot delete answer from open question.\n");
				break;
			}
			case 6: {
				if (test.getNumOfQuestions() == 0) {
					System.out.println("There are no questions in the system");
					break;
				}
				Manager manualTest = new Manager();
				int questions = 0;
				int answers;
				int numOfQuestion = 0;
				int numOfAnswer = 0;
				System.out.println(
						"How many questions do you want in the test? choose between 1 to " + test.getNumOfQuestions());
				questions = inputBetween(1, test.getNumOfQuestions());
				while (questions > 0) {
					System.out.println("choose number of question to apear in the test. choose from 1 to "
							+ test.getNumOfQuestions());
					numOfQuestion = (inputBetween(1, test.getNumOfQuestions()) - 1);
					if (test.getQuestions().get(numOfQuestion) instanceof MultiChoiceQuestion) {
						if (((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion)).getNumOfAnswers() == 0) {
							System.out.println("There are no answers in the question");
						} else {
							MultiChoiceQuestion temp = new MultiChoiceQuestion(
									test.getQuestions().get(numOfQuestion).getText());
							System.out.println("How many answers do you want in the question? Choose between 1 to "
									+ ((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion)).getNumOfAnswers());
							answers = inputBetween(1,
									((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion)).getNumOfAnswers());
							while (answers > 0) {
								System.out.println("choose number of answer to apear in the question. choose from 1 to "
										+ ((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion)).getNumOfAnswers());
								numOfAnswer = (inputBetween(1,
										((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion)).getNumOfAnswers())
										- 1);
								if (temp.addAnswer(((MultiChoiceQuestion) test.getQuestions().get(numOfQuestion))
										.getAnswer(numOfAnswer))) {
									answers--;
								} else
									System.out.println("this answer already added");
							}
							if (manualTest.addQuestion(temp)) {
								questions--;
							}
						}
					} else if (manualTest.addQuestion(test.getQuestions().get(numOfQuestion)))
						questions--;
				}
				allTests.add(manualTest);
				System.out.println(manualTest.toTest().toString());
				try {
					manualTest.exportTest();
				} catch (IOException e) {
					System.out.println("Didn't export the test");
				}
				break;
			}
			case 7: {
				System.out.println(
						"Enter how many questions in test? Choose a number between 1 to " + test.getNumOfQuestions());
				int numOfQuestions = inputBetween(1, test.getNumOfQuestions());
				Manager autoTest = test.autoTestBuilder(numOfQuestions);
				autoTest.insertionSortByAlphabeticOrder();
				allTests.add(autoTest);
				System.out.println(autoTest.toTest().toString());
				try {
					autoTest.exportTest();
				} catch (IOException e) {
					System.out.println("Didn't export the test");
				}
				break;
			}
			
			case 8: {
				System.out.println(
						"Enter how many questions in test? Choose a number between 1 to " + test.getNumOfQuestions());
				int numOfQuestions = inputBetween(1, test.getNumOfQuestions());
				Manager autoTest = test.autoTestBuilder(numOfQuestions);
				autoTest.insertionSortByLength();
				allTests.add(autoTest);
				System.out.println(autoTest.toTest().toString());
				try {
					autoTest.exportTest();
				} catch (IOException e) {
					System.out.println("Didn't export the test");
				}
				break;
			}
			
			case 9: {
				if (allTests.isEmpty()) {
					System.out.println("There are no tests to copy.");
					break;
				}
				Manager clonedTest = new Manager();
				System.out.println("Enter the number of test you want to copy");
				System.out.println("Enter a number from 1 to " + allTests.size());
				int choose = (inputBetween(1 , allTests.size()) - 1);
				try {
					clonedTest = allTests.get(choose).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				System.out.println(clonedTest.toTest().toString());
				allTests.add(clonedTest);
				break;
			}

			case 10: {
				try {
				test.outFile();
				}
				catch(ClassNotFoundException e) {
					System.out.println("Didn't export the questions source. ClassNotFound Exception.");
				}
				catch(IOException e) {
					System.out.println("Didn't export the questions source. IO Exception.");
				} 
				System.out.println("Bye Bye");
				break;
			}

			}
		}
	}

	public static OpenQuestion addOpenQuestion() {
		String question = "";
		String answer = "";
		System.out.println("Enter the question text:");
		question = input.nextLine();
		System.out.println("Enter the answer text:");
		answer = input.nextLine();
		Answer ansTemp = new Answer(answer, true);
		OpenQuestion quesTemp = new OpenQuestion(question, ansTemp);
		return quesTemp;
	}

	public static MultiChoiceQuestion addMultiQuestion() {
		String question = "";
		String answer = "";
		int numOfAnswers = 0;
		int isAnswerTrue = 0;
		int moreAnswers = 1;
		System.out.println("Enter the question text:");
		question = input.nextLine();
		MultiChoiceQuestion newMultiChoice = new MultiChoiceQuestion(question);
		while (moreAnswers == 1 && numOfAnswers < 8) {
			System.out.println("Enter the answer text:");
			answer = input.nextLine();
			System.out.println("If the answer is true press 1, else press 0");
			isAnswerTrue = inputBetween(0, 1);
			if (test.addMultiChoiceAnswer(newMultiChoice, new Answer(answer, isAnswerTrue == 1))) {
				numOfAnswers++;
			} else
				System.out.println("Answer already exists");
			System.out.println("If you want to add another answer press 1, else press 0");
			moreAnswers = inputBetween(0, 1);
		}
		return newMultiChoice;
	}

	public static int getInt() {
		int check = 0;
		boolean isValidInput = false;
		while (!isValidInput) {
			try {
				check = input.nextInt();
				input.nextLine();
				isValidInput = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("You didnt enter a number. Try again");
			}
		}

		return check;
	}

	public static int inputBetween(int minVal, int maxVal) {
		int val = minVal - 1;
		while (val < minVal || val > maxVal) {
			val = getInt();
			if (val < minVal || val > maxVal) {
				System.out.println("You chose a wrong value of number. Try again");
			}
		}
		return val;
	}

}
