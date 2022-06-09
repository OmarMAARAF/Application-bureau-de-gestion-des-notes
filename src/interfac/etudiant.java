package interfac;


public class etudiant {
	String nom;
	String prenom;
    String date_naissance;
    String lieu_naissance;

    public etudiant()
    {
    	super();
    }
    public etudiant(String nom, String prenom , String date_naissance , String lieu_naissance)
    {
    	this.nom=nom;
    	this.prenom=prenom;
    	this.date_naissance=date_naissance;
    	this.lieu_naissance=lieu_naissance;
    }

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getLieu_naissance() {
		return lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}
}
