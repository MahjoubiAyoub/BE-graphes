package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
	
	private Node sommet;
	private boolean marque;
	protected double cout;
	private Node pere;
	private boolean dPile;

	public Label(Node sommet) {
		
		this.sommet = sommet;
		this.marque = false;
		this.cout = Double.POSITIVE_INFINITY;
		this.pere = null;
		this.dPile = false;
		
	}
	
	public boolean isdPile() {
		return dPile;
	}

	public void setdPile(boolean dPile) {
		this.dPile = dPile;
	}
	
	public boolean isMarque() {
		return this.marque;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public double getCout() {
		return this.cout;
	}
	
	public void setCout(double cout) {
		this.cout = cout;
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
	
	public double getSomCout() {
		return this.cout;
	}
	
	@Override
	public int compareTo(Label o) {
		// TODO Auto-generated method stub
		
		if (this.getSomCout() < o.getSomCout()) {
			return -1;
		}
		else if (this.getSomCout() == o.getSomCout()) {
			return 0;
		}
		else 
			return 1;
	}

}
