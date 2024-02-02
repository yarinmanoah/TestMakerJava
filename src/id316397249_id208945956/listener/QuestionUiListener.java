package id316397249_id208945956.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import id316397249_id208945956.model.Answer;
import id316397249_id208945956.model.Question;

public interface QuestionUiListener {

	void addQuestionToModel(Question question);

	void editQuestionToModel(Question oldQuestion, String newQuestion);

	void editOpenAnswerToModel(Question question, String answer);

	void editMultiAnswerToModel(Question question, int oldAnswer, Answer newAnswer);

	void deleteAnswerToModel(Question question, Answer answer);

	void createManualTestToModel(ArrayList<Question> newTest);

	void createAutoTestToModel(int numOfQuestions);

	void cloneTestToModel() throws CloneNotSupportedException;

	void saveSourceToModel();
	
	void moveToSetToModel(List<Question> allQuestions);

	void moveToNonDuplicateSetToModel();

	void moveToMyArrayListToModel(Set<Question> nonDuplicateQuestions);

	void UndoQuestionText();

}
