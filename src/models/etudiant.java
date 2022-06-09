package models;

import java.sql.Date;

public class etudiant {
	String nom;
	String prenom;
    Date date_naissance;
    String lieu_naissance;
    String code_apogé;
    String cne;
    String filiere;


    public etudiant()
    {
    	super();
    }
    public etudiant(String nom, String prenom , Date date_naissance , String lieu_naissance)
    {
    	this.nom=nom;
    	this.prenom=prenom;
    	this.date_naissance=date_naissance;
    	this.lieu_naissance=lieu_naissance;
    }
    public etudiant(String code_apogé, String cne , String filiere )
    {
    	this.code_apogé=code_apogé;
    	this.cne= cne ;
    	this.filiere=filiere;
    }
    public String getcode_apogé() {
		return code_apogé;
	}
	public void setcode_apogé(String code_apogé) {
		this.code_apogé = code_apogé;
	}
	 public String getcne() {
			return cne;
		}
		public void setcne(String cne) {
			this.cne = cne;
		}

		 public String getfiliere() {
				return filiere;
			}
			public void setfiliere(String filiere) {
				this.filiere = filiere;
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
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getLieu_naissance() {
		return lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}

}
