package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
	
	private Node sommet;
	private boolean marque;
	protected double cout;
	private Node pere;
	
	
	public Label(Node sommet) {
		
		this.sommet = sommet;
		this.marque = false;
		this.cout = Double.POSITIVE_INFINITY;
		this.pere = null;
		
		
	}
	
	public boolean isMarque() {
		return marque;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public double getCout() {
		return cout;
	}
	
	public void setCout(double cout) {
		this.cout = cout;
	}
	
	public Node getPere() {
		return pere;
	}
	
	public void setPere(Node pere) {
		this.pere = pere;
	}
	
	public Node getSommet() {
		return sommet;
	}
	
	public void setSommet(Node sommet) {
		this.sommet = sommet;
	}

	@Override
	public int compareTo(Label o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
