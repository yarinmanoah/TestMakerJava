package id316397249_id208945956.Command;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.Question;
import id316397249_id208945956.viewTest.TestGUI;

public class LinkedHsahSetToMyArrayListComcreteCommand implements Command {
	private Set<Question> NonDuplicateQuestions;
	private List<QuestionUiListener> listeners ;
	
	public LinkedHsahSetToMyArrayListComcreteCommand(Set<Question> nonDuplicateQuestions2,
			List<QuestionUiListener> listeners2) {
		NonDuplicateQuestions = nonDuplicateQuestions2;
		this.listeners = listeners2;
	}

	@Override
	public void execute() {
		for (QuestionUiListener listener : listeners) {
			listener.moveToMyArrayListToModel(NonDuplicateQuestions);
		}
	}

}
