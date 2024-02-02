package id316397249_id208945956.observer;

import java.util.HashSet;
import java.util.Set;

import id316397249_id208945956.model.MyArrayList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MyLabel extends Label implements Observer {
	private String msg;
	

	public MyLabel() {
		
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	@Override
	public void update(MyArrayList<?> b) {
		this.setVisible(true);
		this.setTextFill(Color.GREEN);
	}
	
}
