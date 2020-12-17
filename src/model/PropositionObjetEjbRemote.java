package model;

import java.util.List;
import javax.ejb.Remote;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Remote
public interface PropositionObjetEjbRemote {

	public Propositionobjet persistProposition(Propositionobjet po);

	public Propositionobjet mergeProposition(Propositionobjet po);

	public Propositionobjet removeProposition(Propositionobjet po);

	public List<Propositionobjet> getPropositionObjetFindAll();



}
