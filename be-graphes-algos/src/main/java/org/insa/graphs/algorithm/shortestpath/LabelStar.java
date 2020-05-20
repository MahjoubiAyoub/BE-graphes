package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.Node;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;

public class LabelStar extends Label implements Comparable<Label> {
	
	private float bInf;
	
	public LabelStar(Node sommet, ShortestPathData data) {
		super(sommet);
		// TODO Auto-generated constructor stub
		
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.bInf = (float)Point.distance(sommet.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.bInf = (float)Point.distance(sommet.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
		
	}
	
	public float getTotalCost() {
		return this.bInf + this.cost;
	}

}
