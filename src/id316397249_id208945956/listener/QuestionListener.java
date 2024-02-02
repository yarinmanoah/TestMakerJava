package id316397249_id208945956.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import id316397249_id208945956.model.MyArrayList;
import id316397249_id208945956.model.Question;

public interface QuestionListener {

	public void questionModifiedToUI(Question oldQuestion, Question newQuestion);

	public void questionAddToUI(Question question);

	public void createdTestToUI(List<Question> questions);

	public void showMessage(String state);

	public void loadSourceToUI(ArrayList<Question> allQuestions);

	public void moveSetToUI(Collection<Question> questions);

	public void moveNonDuplicateSetToUI(Collection<Question> allQuestionsSet);

	public void moveMyArrayListToUI(MyArrayList<Question> temp);

}
