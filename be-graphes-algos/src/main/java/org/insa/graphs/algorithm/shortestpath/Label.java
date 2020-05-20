package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
	
	private Node sommet;
	private boolean marque;
	protected float cost;
	private Node pere;
	private boolean dTas;

	public Label(Node sommet) {
		
		this.sommet = sommet;
		this.marque = false;
		this.cost = Float.POSITIVE_INFINITY;
		this.pere = null;
		this.dTas = false;
		
	}
	
	public boolean getdTas() {
		return this.dTas;
	}

	public void setdTas() {
		this.dTas = true;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public void setMarque() {
		this.marque = true;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public Node getPere() {
		return this.pere;
	}
	
	public void setPere(Node pere) {
		this.pere = pere;
	}
	
	public Node getSommet() {
		return this.sommet;
	}
	
	public void setSommet(Node sommet) {
		this.sommet = sommet;
	}
	
	public float getTotalCost() {
		return this.cost;
	}
	
	@Override
	public int compareTo(Label o) {
		// TODO Auto-generated method stub
		
		if (this.getTotalCost() < o.getTotalCost()) {
			return -1;
		}
		else if (this.getTotalCost() == o.getTotalCost()) {
			return 0;
		}
		else 
			return 1;
	}

}
