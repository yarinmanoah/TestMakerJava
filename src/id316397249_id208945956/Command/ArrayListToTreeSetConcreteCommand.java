package id316397249_id208945956.Command;

import java.util.ArrayList;
import java.util.List;

import id316397249_id208945956.listener.QuestionUiListener;
import id316397249_id208945956.model.Question;
import id316397249_id208945956.viewTest.TestGUI;

public class ArrayListToTreeSetConcreteCommand implements Command{
	private List<Question> ConcreteArrayList;
	private List<QuestionUiListener> listeners; 
	
	public ArrayListToTreeSetConcreteCommand(List<Question> allQuestions, List<QuestionUiListener> listeners2) {
		this.ConcreteArrayList = allQuestions;
		this.listeners = listeners2;
	}
	
	public void execute() {
		for (QuestionUiListener listener : listeners) {
			listener.moveToSetToModel(ConcreteArrayList);
		}
		
	}

}
