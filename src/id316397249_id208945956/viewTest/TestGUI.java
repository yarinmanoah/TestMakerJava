package id316397249_id208945956.viewTest;

import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import id316397249_id208945956.Command.ArrayListToTreeSetConcreteCommand;
import id316397249_id208945956.Command.Command;
import id316397249_id208945956.Command.LinkedHsahSetToMyArrayListComcreteCommand;
import id316397249_id208945956.Command.TreeSetToLinkedHashSetConcreteCommand;
import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.Answer;
import id316397249_id208945956.model.MultiChoiceQuestion;
import id316397249_id208945956.model.MyArrayList;
import id316397249_id208945956.model.OpenQuestion;
import id316397249_id208945956.model.Question;
import id316397249_id208945956.observer.MyButton;
import id316397249_id208945956.observer.MyLabel;
import id316397249_id208945956.model.ManualSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestGUI implements Testable {

	private List<QuestionUiListener> listeners = new ArrayList<QuestionUiListener>();
	private List<Question> allQuestions = new ArrayList<Question>();
	private Set<Question> NonDuplicateQuestions = new LinkedHashSet<Question>();
	private MyArrayList<Question> myArrayListQuestions = new MyArrayList<Question>();
	private TreeSet<Question> questionsTreeSet = new TreeSet<Question>();
	private Stack<Command> stack = new Stack<>();

	private ComboBox<Question> cmbAllQuestions = new ComboBox<Question>();

	public TestGUI(Stage theStage) {
		theStage.setTitle("Test Bulider");
		BorderPane bp = new BorderPane();
		VBox vb = new VBox();
		bp.setCenter(vb);
		Label headLine = new Label();
		headLine.setText(" Test Bulider by: \n Yarin Manoah 208945956 \n Itay Ostraich 316397249");
		bp.setBottom(headLine);
		Scene sc = new Scene(bp, 400, 450);
		sc.setFill(Color.AQUA);
		cmbAllQuestions.setPromptText("All questions are here:");
		
		MyLabel observeLabel = new MyLabel();
		observeLabel.setText("Iterator Created Successfully");
		observeLabel.setVisible(false);
		myArrayListQuestions.attach(observeLabel);
		
		MyButton observeButton = new MyButton();
		observeButton.setText("Print MyArrayList. Observer Button");
		observeButton.setVisible(false);
		observeButton.setOnAction(e-> {
				moveToMyArrayList(myArrayListQuestions);
		});
		

		Button addQuestionBTN = new Button();
		addQuestionBTN.setText("Add a question");
		myArrayListQuestions.attach(observeButton);
		addQuestionBTN.setOnAction(e-> {
				addQuestion();
				cmbAllQuestions.getSelectionModel().clearSelection();

				cmbAllQuestions.setPromptText("All questions are here");
		});

		Button createClone = new Button();
		createClone.setText("Create Cloned source");
		createClone.setOnAction(e -> {
			createCloneTest();
			cmbAllQuestions.getSelectionModel().clearSelection();
		});

		Button autoTest = new Button();
		autoTest.setText("Create automatic test");
		autoTest.setOnAction(e -> {
			if (allQuestions.size() > 0) {
				createAutoTest();
			} else {
				showMessageToUi("There are no questions in the source. Please add some");
			}
		});

		Button save = new Button();
		save.setText("Save source");
		save.setOnAction(e -> {
			saveSource();
		});

		Button editQuestion = new Button();
		editQuestion.setText("Edit Question");
		editQuestion.setOnAction(e -> {
			editQuestion();
			cmbAllQuestions.getSelectionModel().clearSelection();
		});

		Button deleteAnswer = new Button();
		deleteAnswer.setText("Delete answer");
		deleteAnswer.setOnAction(e -> {
			deleteAnswer();
			cmbAllQuestions.getSelectionModel().clearSelection();
		});

		Button manualTest = new Button();
		manualTest.setText("Create Manual test");
		manualTest.setOnAction(e -> {
			cmbAllQuestions.getSelectionModel().clearSelection();
			if (allQuestions.size() > 0) {
				createManualtest();
			} else {
				showMessageToUi("There are no questions in the source. Please add some");
			}
		});
		
		Button RemoveQuestionFromMyArrayList = new Button();
		RemoveQuestionFromMyArrayList.setVisible(false);
		RemoveQuestionFromMyArrayList.setText("Remove a question");
		RemoveQuestionFromMyArrayList.setOnAction(e -> {
			cmbAllQuestions.getSelectionModel().clearSelection();
			if (allQuestions.size() > 0) {
				Iterator i = myArrayListQuestions.iterator();
				if (i.hasNext()) {
					removeFromArrayList(myArrayListQuestions);
					i.remove();
					moveToMyArrayList(myArrayListQuestions);
				}
			} else {
				showMessageToUi("There are no questions in the source. Please add some");
			}
		});

		Button moveToMyArrayList = new Button();
		moveToMyArrayList.setVisible(false);
		moveToMyArrayList.setText("Move Questions from non-duplicate set to My Array List");
		moveToMyArrayList.setOnAction(e -> {
			cmbAllQuestions.getSelectionModel().clearSelection();
			LinkedHsahSetToMyArrayListComcreteCommand cmd = new LinkedHsahSetToMyArrayListComcreteCommand(NonDuplicateQuestions, listeners);
			stack.push(cmd);
			cmd.execute();
			RemoveQuestionFromMyArrayList.setVisible(true);
		});

		Button moveToNonDuplicateSetbtn = new Button();
		moveToNonDuplicateSetbtn.setVisible(false);
		moveToNonDuplicateSetbtn.setText("Move Questions from Collection to non-duplicate set");
		moveToNonDuplicateSetbtn.setOnAction(e -> {
			cmbAllQuestions.getSelectionModel().clearSelection();
			TreeSetToLinkedHashSetConcreteCommand cmd = new TreeSetToLinkedHashSetConcreteCommand(questionsTreeSet, listeners);
			stack.push(cmd);
			cmd.execute();
			moveToMyArrayList.setVisible(true);
		});

		Button moveToSetbtn = new Button();
		moveToSetbtn.setText("Move Questions from list to TreeSet");
		moveToSetbtn.setOnAction(e -> {
			cmbAllQuestions.getSelectionModel().clearSelection();
			if (allQuestions.size() > 0) {
				ArrayListToTreeSetConcreteCommand cmd = new ArrayListToTreeSetConcreteCommand(allQuestions, listeners);
				stack.push(cmd);
				cmd.execute();
				moveToNonDuplicateSetbtn.setVisible(true);
			} else {
				showMessageToUi("There are no questions in the source. Please add some");
			}
		});		
		
		Button undoMemento = new Button();
		undoMemento.setText("Undo Edit Question");
		undoMemento.setOnAction(e -> {
			showQuestions("Before Undo");
			for (QuestionUiListener listener : listeners) {
				listener.UndoQuestionText();
			}
			showQuestions("After Undo");
		});

		vb.getChildren().addAll(cmbAllQuestions, autoTest, manualTest, createClone, addQuestionBTN, editQuestion,
				deleteAnswer, save, moveToSetbtn, moveToNonDuplicateSetbtn, moveToMyArrayList, RemoveQuestionFromMyArrayList, observeLabel, observeButton, undoMemento);
		vb.setAlignment(Pos.CENTER);

		theStage.setScene(sc);
		theStage.show();
	}

	@Override
	public void addQuestionToUI(Question question) {
		allQuestions.add(question);
		cmbAllQuestions.getItems().add(question);
	}

	@Override
	public void modifiedQuestionToUI(Question question, Question newQuestion) {
		int oldQuestionPlace = cmbAllQuestions.getItems().indexOf(question);
		cmbAllQuestions.getItems().set(oldQuestionPlace, newQuestion);
	}

	@Override
	public void createManualtest() {
		Stage manualTestBuilder = new Stage();
		ScrollPane showItAll = new ScrollPane();
		manualTestBuilder.setTitle("Manual test. you better choose auto test");
		VBox vb = new VBox();

		for (int i = 0; i < allQuestions.size(); i++) {
			HBox oneQuestion = new HBox();
			Label number = new Label();
			number.setText(Integer.toString(i + 1) + ") ");
			oneQuestion.getChildren().add(number);
			if (allQuestions.get(i) instanceof MultiChoiceQuestion) {
				oneQuestion.getChildren().add(createManualMultiQuestion((MultiChoiceQuestion) allQuestions.get(i)));
			} else {
				oneQuestion.getChildren().add(createManualOpenQuestion((OpenQuestion) allQuestions.get(i)));
			}
			vb.getChildren().add(oneQuestion);
		}

		Button apply = new Button();
		apply.setText("Confirm");
		apply.setOnAction(e -> {
			List<Question> newTest = new ArrayList<Question>();
			for (int i = 0; i < vb.getChildren().size(); i++) {
				if (vb.getChildren().get(i) instanceof HBox) {
					VBox questionChecks = ((VBox) ((HBox) vb.getChildren().get(i)).getChildren().get(1));
					if (((CheckBox) questionChecks.getChildren().get(1)).isSelected()) {
						String questionText = ((CheckBox) questionChecks.getChildren().get(1)).getText();
						for (Question question : allQuestions) {
							if (questionText.equals(question.getText())
									&& (((Label) questionChecks.getChildren().get(0)).getText().equals("Open Question")
											&& question instanceof OpenQuestion)) {
								newTest.add(question);
							} else if (questionText.equals(question.getText())
									&& (((Label) questionChecks.getChildren().get(0)).getText().equals(
											"Multi Choice Question") && question instanceof MultiChoiceQuestion)) {
								MultiChoiceQuestion temp = new MultiChoiceQuestion(question.getText());
								for (int j = 2; j < questionChecks.getChildren().size(); j++) {
									if (((CheckBox) questionChecks.getChildren().get(j)).isSelected()) {
										for (int k = 0; k < ((MultiChoiceQuestion) question).getNumOfAnswers(); k++) {
											if (((MultiChoiceQuestion) question).getAnswer(k).getText().equals(
													((CheckBox) questionChecks.getChildren().get(j)).getText())) {
												temp.addAnswer(((MultiChoiceQuestion) question).getAnswer(k));
											}
										}
									}
								}
								newTest.add(temp);
							}
						}
					}
				}
			}
			for (QuestionUiListener listener : listeners) {
				listener.createManualTestToModel((ArrayList<Question>) newTest);
			}
		});

		vb.getChildren().add(apply);
		vb.setSpacing(10);
		showItAll.setContent(vb);
		manualTestBuilder.setScene(new Scene(showItAll, 350, 350));
		manualTestBuilder.show();

	}

	public VBox createManualOpenQuestion(OpenQuestion question) {
		VBox vb = new VBox();
		Label typeOfQuestion = new Label();
		typeOfQuestion.setText("Open Question");
		CheckBox questionText = new CheckBox();
		questionText.setText(question.getText());
		vb.getChildren().addAll(typeOfQuestion, questionText);
		return vb;
	}

	public VBox createManualMultiQuestion(MultiChoiceQuestion question) {
		VBox vb = new VBox();
		Label typeOfQuestion = new Label();
		typeOfQuestion.setText("Multi Choice Question");
		CheckBox questionText = new CheckBox();
		questionText.setText(question.getText());
		vb.getChildren().addAll(typeOfQuestion, questionText);

		List<CheckBox> answersBoxs = new ArrayList<CheckBox>();
		for (int i = 0; i < question.getNumOfAnswers(); i++) {
			CheckBox tempAns = new CheckBox();
			tempAns.setDisable(true);
			tempAns.setText(question.getAnswer(i).getText());
			answersBoxs.add(tempAns);
			vb.getChildren().add(tempAns);
		}

		questionText.setOnAction(e -> {

			if (questionText.isSelected()) {
				for (CheckBox checkBox : answersBoxs) {
					checkBox.setDisable(false);
				}
			} else {
				for (CheckBox checkBox : answersBoxs) {
					checkBox.setDisable(true);
					checkBox.setSelected(false);
				}
			}
		});
		return vb;
	}

	@Override
	public void createAutoTest() {
		Stage createAutoTest = new Stage();
		createAutoTest.setTitle("Create Automatic Test");

		VBox vb = new VBox();

		Label instructions = new Label();
		instructions.setText("How many questions are you desire to apear in your test?");
		vb.getChildren().add(instructions);

		int numOfQuestions = allQuestions.size();
		List<RadioButton> questionsButtons = new ArrayList<RadioButton>();
		ToggleGroup allRadios = new ToggleGroup();
		for (int i = 1; i <= numOfQuestions; i++) {
			RadioButton tempRadio = new RadioButton();
			tempRadio.setText(Integer.toString(i));
			questionsButtons.add(tempRadio);
			tempRadio.setToggleGroup(allRadios);
			vb.getChildren().add(tempRadio);
		}

		Button apply = new Button();
		apply.setText("Confirm");
		apply.setOnAction(e -> {

			if (allRadios.getSelectedToggle() != null) {
				for (QuestionUiListener listener : listeners) {
					listener.createAutoTestToModel(
							questionsButtons.indexOf(((RadioButton) allRadios.getSelectedToggle())) + 1);
					createAutoTest.close();
				}
			} else {
				showMessageToUi("You must choose how many questions to apear in the test.");
			}
		});

		vb.getChildren().addAll(apply);
		createAutoTest.setScene(new Scene(vb, 350, 350));
		createAutoTest.show();
	}

	@Override
	public void testCreated(List<Question> questions) {
		Stage showTest = new Stage();
		ScrollPane thisTest = new ScrollPane();

		VBox allOfTheQuestions = new VBox();
		allOfTheQuestions.setSpacing(10);

		showTest.setTitle("your Test");
		for (int i = 1; i <= questions.size(); i++) {
			HBox oneQuestion = new HBox();
			Label questionNumber = new Label();
			questionNumber.setText(Integer.toString(i) + ") ");
			oneQuestion.getChildren().addAll(questionNumber, showQuestion(questions.get(i - 1)));
			allOfTheQuestions.getChildren().add(oneQuestion);
		}

		Button checkTest = new Button();
		checkTest.setText("Finish");
		checkTest.setOnAction(e -> {

			Stage gradewindow = new Stage();
			gradewindow.setTitle("Grade");
			Label gradeLabel = new Label();
			Label gradeNumber = new Label();
			gradeLabel.setText("Your grade is:  \n");
			VBox allGrade = new VBox();

			int numOfCorrectQuestions = 0;
			Question toCheck = null;
			for (int i = 0; i < allOfTheQuestions.getChildren().size(); i++) {
				if (allOfTheQuestions.getChildren().get(i) instanceof HBox) {
					HBox questionBox = (HBox) allOfTheQuestions.getChildren().get(i);
					VBox questionOnly = (VBox) questionBox.getChildren().get(1);
					Label questionText = (Label) questionOnly.getChildren().get(0);
					for (int j = 0; j < questions.size(); j++) {
						if (questions.get(j).getText() == questionText.getText()) {
							toCheck = questions.get(j);
						}
					}
					if (toCheck instanceof MultiChoiceQuestion) {
						String answer = ((ComboBox<String>) questionOnly.getChildren().get(1)).getValue();
						if (checkMultiQuestion((MultiChoiceQuestion) toCheck, answer))
							numOfCorrectQuestions++;
					} else {
						String answer = ((TextField) questionOnly.getChildren().get(1)).getText();
						if (checkOpenQuestion((OpenQuestion) toCheck, answer))
							numOfCorrectQuestions++;
					}
				}
			}
			double grade = ((double) numOfCorrectQuestions / ((double) allOfTheQuestions.getChildren().size() - 1))
					* 100;
			gradeNumber.setText(Integer.toString((int) grade));
			allGrade.setAlignment(Pos.CENTER);
			allGrade.getChildren().addAll(gradeLabel, gradeNumber);
			gradewindow.setScene(new Scene(allGrade, 250, 250));
			gradewindow.show();
		});

		allOfTheQuestions.getChildren().add(checkTest);

		thisTest.setContent(allOfTheQuestions);
		showTest.setScene(new Scene(thisTest, 350, 350));
		showTest.show();
	}

	public boolean checkMultiQuestion(MultiChoiceQuestion question, String answer) {
		for (int i = 0; i < question.getNumOfAnswers(); i++) {
			if (question.getAnswer(i).isTrue()) {
				if (question.getAnswer(i).getText().equals(answer))
					return true;
			}
		}
		return false;
	}

	public boolean checkOpenQuestion(OpenQuestion question, String answer) {
		return question.getAnsText().equals(answer);
	}

	public VBox showQuestion(Question question) {
		VBox vb = new VBox();
		Label questionText = new Label();
		questionText.setText(question.getText());
		vb.getChildren().add(questionText);

		if (question instanceof OpenQuestion) {
			TextField answer = new TextField();
			vb.getChildren().add(answer);
		} else {
			int numOfAnswers = ((MultiChoiceQuestion) question).getNumOfAnswers();
			ComboBox<String> answersButtons = new ComboBox<String>();
			for (int i = 0; i < numOfAnswers; i++) {
				String ans = ((MultiChoiceQuestion) question).getAnswer(i).getText();
				answersButtons.getItems().add(ans);
			}
			vb.getChildren().add(answersButtons);
		}
		return vb;
	}

	public void saveSource() {
		for (QuestionUiListener listener : listeners) {
			listener.saveSourceToModel();
		}

	}

	@Override
	public void registerListener(QuestionUiListener listener) {
		listeners.add(listener);
	}

	public void addOpenQuestion() {
		Stage openQuestionStage = new Stage();
		openQuestionStage.setTitle("Add open question");
		Label workingLabel = new Label();
		TextField question = new TextField();
		TextField answer = new TextField();
		VBox vb = new VBox();
		question.setPromptText("Enter question here");
		answer.setPromptText("Enter answer here");
		Button add = new Button();
		add.setText("Click to add");
		add.setOnAction(e -> {
			if (question.getText() != "" && answer.getText() != "") {
				Answer ansTemp = new Answer(answer.getText(), true);
				OpenQuestion quesTemp = new OpenQuestion(question.getText(), ansTemp);
				for (QuestionUiListener listener : listeners) {
					listener.addQuestionToModel(quesTemp);
					openQuestionStage.close();
				}
			} else
				workingLabel.setText("Question didn't add. Fill all the blanks");
		});
		vb.getChildren().addAll(question, answer, add, workingLabel);
		vb.setSpacing(30);
		openQuestionStage.setScene(new Scene(vb, 300, 300));
		openQuestionStage.show();
	}

	public void addMultiQuestion() {
		ManualSet<Answer> answers = new ManualSet<Answer>();
		Stage MultiQuestionStage = new Stage();
		MultiQuestionStage.setTitle("Add MultiChoice question");
		VBox vb = new VBox();

		Label questionLabel = new Label();
		questionLabel.setText("Enter the question");

		TextField questionText = new TextField();

		addAnswerHandler addIt = new addAnswerHandler(answers);
		Button addAns = new Button();
		addAns.setText("Add an answer. Number of answers: " + answers.getSize());
		addAns.setOnAction(addIt);

		Button addQuestion = new Button();
		addQuestion.setText("Confirm");
		addQuestion.setOnAction(e -> {
			if (!questionText.getText().equals("") && answers.getSize() > 0) {
				MultiChoiceQuestion question = new MultiChoiceQuestion(questionText.getText(), answers);
				for (QuestionUiListener listener : listeners) {
					listener.addQuestionToModel(question);
					MultiQuestionStage.close();
				}
			} else {
				showMessageToUi("You must enter a question or add some answers.");
			}
		});

		vb.getChildren().addAll(questionLabel, questionText, addAns, addQuestion);
		MultiQuestionStage.setScene(new Scene(vb, 300, 300));
		MultiQuestionStage.show();
	}

	private class addAnswerHandler implements EventHandler<ActionEvent> {

		private ManualSet<Answer> answers;

		public addAnswerHandler(ManualSet<Answer> answers) {
			this.answers = answers;
		}

		public void handle(ActionEvent arg0) {
			addAnswer(answers);
			((Button) arg0.getSource()).setText("Add an answer. Number of answers: " + answers.getSize());
		}

		private void addAnswer(ManualSet<Answer> answers) {
			Stage answerStage = new Stage();
			answerStage.setTitle("Answer");
			VBox vb = new VBox();

			Label answerLabel = new Label();
			answerLabel.setText("Enter your answer here");

			TextField answer = new TextField();

			RadioButton tr = new RadioButton();
			RadioButton fl = new RadioButton();
			ToggleGroup choose = new ToggleGroup();
			tr.setToggleGroup(choose);
			tr.setText("True");
			fl.setToggleGroup(choose);
			fl.setText("False");

			Button confirm = new Button();
			confirm.setText("Add answer");
			confirm.setOnAction(e -> {
				if (answer.getText() != "" && choose.getSelectedToggle() != null) {
					Answer ans = new Answer(answer.getText(), choose.getSelectedToggle() == tr);
					answers.add(ans);
					answerStage.close();
				} else {
					showMessageToUi("You must enter an answer or check the true/false buttons");
				}
			});

			vb.getChildren().addAll(answerLabel, answer, tr, fl, confirm);
			answerStage.setScene(new Scene(vb, 300, 300));
			answerStage.showAndWait();
		}

	}

	public void loadSource(ArrayList<Question> newSource) {
		for (Question question : newSource) {
			addQuestionToUI(question);
		}

	}

	@Override
	public void deleteAnswer() {
		Stage deleteStage = new Stage();
		deleteStage.setTitle("Delete answer");
		VBox vb = new VBox();

		if (cmbAllQuestions.getItems().indexOf(cmbAllQuestions.getValue()) != -1) {
			Question temp = allQuestions.get(cmbAllQuestions.getItems().indexOf(cmbAllQuestions.getValue()));
			if (temp instanceof MultiChoiceQuestion && ((MultiChoiceQuestion) temp).getNumOfAnswers() > 0) {
				ToggleGroup answers = new ToggleGroup();
				List<RadioButton> allAns = new ArrayList<RadioButton>();
				for (int i = 0; i < ((MultiChoiceQuestion) temp).getNumOfAnswers(); i++) {
					RadioButton ansbtn = new RadioButton();
					ansbtn.setText(((MultiChoiceQuestion) temp).getAnswer(i).getText());
					ansbtn.setToggleGroup(answers);
					allAns.add(ansbtn);
					vb.getChildren().add(ansbtn);
				}
				Button apply = new Button();
				apply.setText("confirm");
				apply.setOnAction(e -> {
					if (answers.getSelectedToggle() != null) {
						Answer ans = ((MultiChoiceQuestion) temp)
								.getAnswer(allAns.indexOf(answers.getSelectedToggle()));
						for (QuestionUiListener listener : listeners) {
							listener.deleteAnswerToModel(temp, ans);
						}
						deleteStage.close();
					} else {
						showMessageToUi("You must choose an answer to delete");
					}
				});

				vb.getChildren().add(apply);
				deleteStage.setScene(new Scene(vb, 350, 350));
				deleteStage.show();
			} else {
				showMessageToUi("You must choose a multi choice question with answers to delete an answer");
			}
		} else {
			showMessageToUi("You must choose a question to delete an answer");
		}

	}

	@Override
	public void addQuestion() {
		Stage addQuestionStage = new Stage();
		addQuestionStage.setTitle("Add question");
		HBox hb = new HBox();

		Button multi = new Button();
		multi.setText("Add MultiChoice Question");
		multi.setOnAction(e -> {
			addMultiQuestion();
		});

		Button open = new Button();
		open.setText("Add Open Question");
		open.setOnAction(e -> {
			addOpenQuestion();
		});

		hb.getChildren().addAll(multi, open);
		addQuestionStage.setScene(new Scene(hb, 350, 350));
		addQuestionStage.show();
	}

	@Override
	public void editQuestion() {
		if (cmbAllQuestions.getItems().indexOf(cmbAllQuestions.getValue()) != -1) {
			Stage editQuestion = new Stage();
			editQuestion.setTitle("Edit Question");
			VBox vb = new VBox();

			Label oldQuestLabel = new Label();

			TextField newQuestion = new TextField();
			newQuestion.setPromptText("Enter new question here");

			Button edit = new Button();
			edit.setText("Edit it");

			Question temp = allQuestions.get(cmbAllQuestions.getItems().indexOf(cmbAllQuestions.getValue()));
			oldQuestLabel.setText("old Question is: " + temp.getText());
			edit.setOnAction(e -> {
				if (newQuestion.getText() != "") {
					for (QuestionUiListener listener : listeners) {
						listener.editQuestionToModel(temp, newQuestion.getText());
					}
					editQuestion.close();
				} else {
					showMessageToUi("You must fill the new question edit");
				}
			});
			vb.getChildren().addAll(oldQuestLabel, newQuestion, edit);
			editQuestion.setScene(new Scene(vb, 350, 350));
			editQuestion.show();
		}

		else {
			showMessageToUi("You must choose a question to edit");
		}

	}

	public void moveToSet(Collection<Question> allQuestions) {
		questionsTreeSet =(TreeSet<Question>) allQuestions;
		ScrollPane pane = new ScrollPane();
		Stage showCollection = new Stage();
		showCollection.setTitle("Collection of Questions");

		VBox vb = new VBox();
		Label questions = new Label();
		Iterator<Question> it = allQuestions.iterator();
		while (it.hasNext()) {
			questions.setText(questions.getText() + it.next().toString() + "\n");
		}
		vb.getChildren().addAll(questions);
		pane.setContent(vb);
		showCollection.setScene(new Scene(pane, 400, 400));
		showCollection.show();

	}

	@Override
	public void showMessageToUi(String state) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(state);
		alert.setHeaderText(null);
		alert.setContentText(state);
		alert.showAndWait();

	}

	@Override
	public void createCloneTest() {
		for (QuestionUiListener listener : listeners) {
			try {
				listener.cloneTestToModel();
			} catch (CloneNotSupportedException e) {
				showMessageToUi("Something went wrong");
			}
		}

	}

	@Override
	public void moveToNonDuplicateSet(Collection<Question> allQuestions) {
		NonDuplicateQuestions = (LinkedHashSet<Question>)allQuestions;
		Stage showCollection = new Stage();
		ScrollPane pane = new ScrollPane();
		showCollection.setTitle("Collection of Non-Duplicate Questions");

		VBox vb = new VBox();
		Label questions = new Label();
		Iterator<Question> it = allQuestions.iterator();
		while (it.hasNext()) {
			questions.setText(questions.getText() + it.next().toString() + "\n");
		}
		vb.getChildren().addAll(questions);
		pane.setContent(vb);
		showCollection.setScene(new Scene(pane, 400, 400));
		showCollection.show();

	}

	
	
	public void removeFromArrayList(MyArrayList<Question> temp) {
		ArrayList<Question> al = new ArrayList<Question>();
		Iterator<Question> it = temp.iterator();
		while(it.hasNext()) {
			al.add(it.next());
		}
		al.remove(0);
		Stage showCollection = new Stage();
		ScrollPane pane = new ScrollPane();
		showCollection.setTitle("Collection of ArrayList Questions");

		VBox vb = new VBox();
		Label questions = new Label();
		Iterator<Question> itAL = al.iterator();
		while (itAL.hasNext()) {
			questions.setText(questions.getText() + itAL.next().toString() + "\n");
		}
		vb.getChildren().addAll(questions);
		pane.setContent(vb);
		showCollection.setScene(new Scene(pane, 400, 400));
		showCollection.show();
	}

	@Override
	public void moveToMyArrayList(MyArrayList<Question> temp) {
		myArrayListQuestions = temp;
		Stage showCollection = new Stage();
		ScrollPane pane = new ScrollPane();
		showCollection.setTitle("Collection of MyArrayList Questions");

		VBox vb = new VBox();
		Label questions = new Label();
		Iterator<Question> it = temp.iterator();
		while (it.hasNext()) {
			questions.setText(questions.getText() + it.next().toString() + "\n");
		}
		vb.getChildren().addAll(questions);
		pane.setContent(vb);
		showCollection.setScene(new Scene(pane, 400, 400));
		showCollection.show();
		
	}
	
	public void showQuestions(String msg) {
		Stage showCollection = new Stage();
		ScrollPane pane = new ScrollPane();
		showCollection.setTitle(msg);

		VBox vb = new VBox();
		Label questions = new Label();
		Iterator<Question> it = allQuestions.iterator();
		while (it.hasNext()) {
			questions.setText(questions.getText() + it.next().toString() + "\n");
		}
		vb.getChildren().addAll(questions);
		pane.setContent(vb);
		showCollection.setScene(new Scene(pane, 400, 400));
		showCollection.show();
	}

}
