package algorithms.search;

import java.io.Serializable;

public class State<T> implements Comparable<State<T>>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private State<T> cameFrom;
	private double cost;
	private T value;
	private String key;
	
	
	public State(T value) {
		this.value=value;	
	}
	
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		return s.value.equals(this.value);
	}
	
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getKey() {
		return key;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}