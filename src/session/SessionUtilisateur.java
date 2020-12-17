package session;

public class SessionUtilisateur {
	
	private static SessionUtilisateur instance;
	
	private static String username;
	private static String mdp;
	
	public SessionUtilisateur(String username, String mdp) {
		SessionUtilisateur.username = username;
		SessionUtilisateur.mdp = mdp;
	}
	
	public static SessionUtilisateur getInstance(String username, String mdp) {
		if(instance == null) {
			instance = new SessionUtilisateur(username, mdp);
		}
		return instance;
	}
	
	 public static String getUsername() {
	        return username;
	    }
	 
	 public String getMdp(){
		 return mdp;
	 }
	 
	 public static String cleanSessionUtilisateur() {
		 username = "";
		 mdp = "";
		 System.out.println("Pas d'utilisateur connecté " +username);
		return null; 
	 }
	 
	 public String toString() {
		 return "SessionUtilisateur : " 
				 + "username : " +username + " mdp : " +mdp;
	 }
}
