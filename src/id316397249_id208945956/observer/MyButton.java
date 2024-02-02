package id316397249_id208945956.observer;

import java.util.HashSet;
import java.util.Set;

import id316397249_id208945956.Command.Command;
import id316397249_id208945956.model.MyArrayList;
import javafx.scene.control.Button;

public class MyButton extends Button implements Observer {
	private String msg;	

	public MyButton() {
		
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
		
	}
}
