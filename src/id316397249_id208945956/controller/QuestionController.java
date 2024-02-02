package id316397249_id208945956.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import id316397249_id208945956.listener.QuestionListener;
import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.Answer;
import id316397249_id208945956.model.Manager;
import id316397249_id208945956.model.MyArrayList;
import id316397249_id208945956.model.Question;
import id316397249_id208945956.viewTest.Testable;

public class QuestionController implements QuestionListener, QuestionUiListener {

	private Manager modelManager;
	private Testable view;

	public QuestionController(Manager modelManger, Testable view) {
		this.modelManager = modelManger;
		this.view = view;
		this.modelManager.registerListener(this);
		this.view.registerListener(this);
	}

	@Override
	public void questionModifiedToUI(Question oldQuestion, Question newQuestion) {
		view.modifiedQuestionToUI(oldQuestion, newQuestion);
	}

	@Override
	public void questionAddToUI(Question question) {
		view.addQuestionToUI(question);

	}

	public void createdTestToUI(List<Question> questions) {
		view.testCreated(questions);
	}

	@Override
	public void showMessage(String state) {
		view.showMessageToUi(state);
	}

	@Override
	public void addQuestionToModel(Question question) {
		modelManager.addQuestion(question);

	}

	@Override
	public void editQuestionToModel(Question oldQuestion, String newQuestion) {
		modelManager.editQuestion(oldQuestion, newQuestion);

	}

	@Override
	public void editOpenAnswerToModel(Question question, String answer) {
		modelManager.editOpenAnswer(question, answer);

	}

	@Override
	public void editMultiAnswerToModel(Question question, int oldAnswer, Answer newAnswer) {
		modelManager.editMultiAnswer(question, oldAnswer, newAnswer);

	}

	@Override
	public void deleteAnswerToModel(Question question, Answer answer) {
		modelManager.deleteAnswer(question, answer);

	}

	@Override
	public void createAutoTestToModel(int numOfQuestions) {
		modelManager.autoTestBuilder(numOfQuestions);

	}

	@Override
	public void cloneTestToModel() throws CloneNotSupportedException {
		modelManager.clone();

	}

	@Override
	public void saveSourceToModel() {
		try {
			modelManager.outFile();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Didn't save");
			e.printStackTrace();
		}

	}

	@Override
	public void loadSourceToUI(ArrayList<Question> allQuestions) {
		view.loadSource(allQuestions);
	}

	@Override
	public void createManualTestToModel(ArrayList<Question> newTest) {
		modelManager.createManualTest(newTest);

	}

	@Override
	public void moveToSetToModel(List<Question> allQuestions) {
		modelManager.moveQuestionsToSet(allQuestions);
		
	}
	
	public void moveSetToUI(Collection<Question> allQuestions) {
		view.moveToSet(allQuestions);
	}

	@Override
	public void moveNonDuplicateSetToUI(Collection<Question> allQuestionsSet) {
		view.moveToNonDuplicateSet(allQuestionsSet);
		
	}

	@Override
	public void moveToNonDuplicateSetToModel() {
		modelManager.moveQuestionsToNonDuplicateSet();
		
	}

	@Override
	public void moveToMyArrayListToModel(Set<Question> nonDuplicateQuestions) {
		modelManager.moveQuestionsToMyArrayList(nonDuplicateQuestions);
		
	}

	@Override
	public void moveMyArrayListToUI(MyArrayList<Question> temp) {
		view.moveToMyArrayList(temp);
		
	}

	@Override
	public void UndoQuestionText() {
		modelManager.undoQuestion();
		
	}

}
