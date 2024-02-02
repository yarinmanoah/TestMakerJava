package id316397249_id208945956.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import id316397249_id208945956.observer.Observer;

public class MyArrayList<T> {
	private T[] a = (T[]) new Object[0];
	private int count = 0;
	private static Set<Observer> observerSet = new HashSet<Observer>();

	
	public void add(T value) {
		T [] temp = (T[])new Object [++count];
		for (int i = 0; i < a.length; i++) {
			temp[i] = a[i];
		}
		temp [count - 1] = value;
		a = temp;
	}
	
	public Iterator<T> iterator() {
		myNotify();
		return new ConcreteIterator(); 
	}
	
	// addListener
		public void attach(Observer o) {
			observerSet.add(o);
		}

		public void detach(Observer o) {
			observerSet.remove(o);
		}

		public void myNotify() {
			for (Observer o : observerSet)
				o.update(this);
		}
	
	private class ConcreteIterator implements Iterator<T> {
		private int cur = 0;
		
		@Override
		public boolean hasNext() {
			return cur < count;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return a[cur++];
		} 
		
		@Override
		public void remove() {
			T [] temp = (T[])new Object [--count];
			for (int i = 0; i < cur; i++) {
				temp[i] = a[i];
			}
			for (int i = cur; i < a.length - 1; i++) {
			      temp[i] = a[i + 1];
			   }
			a = temp;
		}
	} 
	
}