package id316397249_id208945956.Command;

import java.util.List;
import java.util.TreeSet;

import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.Question;
import id316397249_id208945956.viewTest.TestGUI;

public class TreeSetToLinkedHashSetConcreteCommand implements Command{
	private TreeSet<Question> ConcreteTreeSet;
	private List<QuestionUiListener> listeners; 

	

	public TreeSetToLinkedHashSetConcreteCommand(TreeSet<Question> questionsTreeSet, List<QuestionUiListener> listeners) {
		ConcreteTreeSet = questionsTreeSet;
		this.listeners = listeners;
	}



	@Override
	public void execute() {
		for (QuestionUiListener listener : listeners) {
			listener.moveToNonDuplicateSetToModel();
		}
	}

}
