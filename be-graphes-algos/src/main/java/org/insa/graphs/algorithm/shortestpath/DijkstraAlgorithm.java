package org.insa.graphs.algorithm.shortestpath;

import java.util.*;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	protected int sommet;
	protected int sommetExplorer;

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.sommetExplorer = 0;
    }

    @Override
    protected ShortestPathSolution doRun() {
    	
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        Graph graph = data.getGraph();
        int graphSize = graph.size();
        boolean fin = false;
        
        // Le tableau des Prédescesseurs
        Arc[] tabPredecesseur = new Arc[graphSize];
        
        // Le tableau des Labels
        Label tabLabel[] = new Label [graphSize];
        
        // Tas de Labels
		BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
		// L'Ajout du sommet de départ
		Label depart = new Label(data.getOrigin());
		tabLabel[depart.getSommet().getId()] = depart;
		tas.insert(depart);
		depart.setdTas();
		depart.setCost(0);
		
		// Pour Monsieur l'observeur
		notifyOriginProcessed(data.getOrigin());
		
		while(!tas.isEmpty() && !fin){      	

			Label courant = tas.deleteMin();
			
			// On indique aux observeurs que le Node était exploré 
			notifyNodeMarked(courant.getSommet());
			courant.setMarque();
			
			// Lorseque on explore la destination on s'arrête
			if (courant.getSommet() == data.getDestination()) {
				
				fin = true;
				
			}
			
			// Parcours des successeurs du sommet courant 
			Iterator<Arc> arc = courant.getSommet().iterator();
			while (arc.hasNext()) {
				
				Arc arcIterator = arc.next();

				// On vérifie si on peut réellement prendre cet Arc
				if (!data.isAllowed(arcIterator)) {
					
					continue;
					
				}

				Node successeur = arcIterator.getDestination();

				// On recupere le label correspondant au noeud dans tabLabel
				Label successeurLabel = tabLabel[successeur.getId()];

				// Si le label n'existe pas on le crée 
				if (successeurLabel == null) {
					
					// On informe les observeurs que l'on atteint un noeud pour la première fois 
					notifyNodeReached(arcIterator.getDestination());
					successeurLabel = new Label(successeur);
					tabLabel[successeurLabel.getSommet().getId()] = successeurLabel;
					
					// On incrémente le nombre des sommets explorées pour le test de la performance 
					this.sommetExplorer++;
					
				}

				// Si le successeur n'est pas encore exploré
				if (!successeurLabel.getMarque()) {
					
					// Si c'est un meilleur cout on le met à jour 
					if((successeurLabel.getTotalCost() > (courant.getCost() + data.getCost(arcIterator) + (successeurLabel.getTotalCost() - successeurLabel.getCost()))) || (successeurLabel.getCost()==Float.POSITIVE_INFINITY)){
						
						successeurLabel.setCost(courant.getCost()+(float)data.getCost(arcIterator));
						successeurLabel.setPere(courant.getSommet());
						
						// Si le label est déjà présent dans le tas on met à jour sa position 
						if(successeurLabel.getdTas()) {
							
							tas.remove(successeurLabel);
							
						}
						// Sinon on ajoute le label dans le tas
						else
							successeurLabel.setdTas();
						
						tas.insert(successeurLabel);
						tabPredecesseur[arcIterator.getDestination().getId()] = arcIterator;
						
					}
				}

			}
		}
		
		// Si la destination n'a pas de predecesseur la solution est infeasible
		if (tabPredecesseur[data.getDestination().getId()] == null) {
			
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
			
		} 
		else {

			// La destination has been found, notify the observers
			notifyDestinationReached(data.getDestination());
			
			// Creation du chemin à partir array des predecesseurs
			ArrayList<Arc> arcs = new ArrayList<>();
			Arc arc = tabPredecesseur[data.getDestination().getId()];

			while (arc != null) {
				
				arcs.add(arc);
				arc = tabPredecesseur[arc.getOrigin().getId()];
			
			}

			// Inverser le chemin
			Collections.reverse(arcs);

			// La creation de la solution finale
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));

		}
		
        return solution;
    }
    
    // Creer et retourner le Label correspondant au Node
	protected Label newLabel(Node node, ShortestPathData data) {
		return new Label(node);
	}
	
	// Retourner le nombre des sommets explorés
	public int getSommetExplorer() {
		return this.sommetExplorer;
	}

}
