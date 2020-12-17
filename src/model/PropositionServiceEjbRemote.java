package model;

import java.util.List;
import javax.ejb.Remote;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Remote
public interface PropositionServiceEjbRemote {

	public Propositionservice persistProposition(Propositionservice ps);

	public Propositionservice mergeProposition(Propositionservice ps);

	public Propositionservice removeProposition(Propositionservice ps);

	public List<Propositionservice> getPropositionServiceFindAll();



}
