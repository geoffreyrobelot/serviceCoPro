package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Local
public interface PropositionObjetEjbLocal {

	public Propositionobjet persistProposition(Propositionobjet po);

	public Propositionobjet mergeProposition(Propositionobjet po);

	public Propositionobjet removeProposition(Propositionobjet po);

	public List<Propositionobjet> getPropositionObjetFindAll();



}
