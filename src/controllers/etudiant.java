package controllers;

public class etudiant {
	//private int id;
	private String Matiéres;
	private int Notes;
	private String Résultat;

public etudiant(){
	super();
}
public etudiant(String Matiéres , int Notes , String Résultat){
	super();
	//this.id=id;
	this.Matiéres=Matiéres;
	this.Notes=Notes;
	this.Résultat=Résultat;
}
/*public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}*/
public String getMatiéres() {
	return Matiéres;
}
public void setMatiéres(String matiéres) {
	Matiéres = matiéres;
}
public int getNotes() {
	return Notes;
}
public void setNotes(int notes) {
	Notes = notes;
}
public String getRésultat() {
	return Résultat;
}
public void setRésultat(String résultat) {
	Résultat = résultat;
}
}
