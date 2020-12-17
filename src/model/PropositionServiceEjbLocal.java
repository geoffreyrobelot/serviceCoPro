package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface m�tier locale est d�finie dans cette interface
 */

@Local
public interface PropositionServiceEjbLocal {

	public Propositionservice persistProposition(Propositionservice ps);

	public Propositionservice mergeProposition(Propositionservice ps);

	public Propositionservice removeProposition(Propositionservice ps);

	public List<Propositionservice> getPropositionServiceFindAll();



}
