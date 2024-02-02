package id316397249_id208945956.viewTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.MyArrayList;
import id316397249_id208945956.model.Question;

public interface Testable {
	void addQuestionToUI(Question question);

	void modifiedQuestionToUI(Question oldQuestion, Question newQuestion);

	void addQuestion();

	void deleteAnswer();

	void editQuestion();

	void createManualtest();

	void createAutoTest();

	void createCloneTest();

	void testCreated(List<Question> questions);

	void saveSource();

	void showMessageToUi(String state);

	void registerListener(QuestionUiListener listener);

	void loadSource(ArrayList<Question> newSource);
	
	void moveToSet(Collection<Question> allQuestions);
	
	void moveToNonDuplicateSet(Collection<Question> allQuestionsSet);

	void moveToMyArrayList(MyArrayList<Question> temp);

}