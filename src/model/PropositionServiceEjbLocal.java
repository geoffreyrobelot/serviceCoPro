package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Local
public interface PropositionServiceEjbLocal {

	public Propositionservice persistProposition(Propositionservice ps);

	public Propositionservice mergeProposition(Propositionservice ps);

	public Propositionservice removeProposition(Propositionservice ps);

	public List<Propositionservice> getPropositionServiceFindAll();



}
