package ch.makery.address.model;

public class Student{


		private  String Id;
		private  String Nom;
		private  String Prenom;
		private  Double Note;
		private  String Resultat ;

		private  String DateNaissance ;



			/**
			 * Constructor with some initial data.

			 */

		public Student(String id,String nom, String prenom,Double n,String r) {
			super();
			
			 Id =id;
			 Nom = nom;
			 Prenom = prenom;
			 Note = n;
			 Resultat = r;

		}



			public Student() {
				super();
				 Id ="";
				 Nom = "";
				 Prenom = "";
				 Note = null;
				 Resultat = null;


			}



			public  Double getNote() {
				return Note;
			}



			public  void setNote(Double Note) {
				this.Note = Note;
			}




			public String getResultat() {
				return Resultat;
			}



			public  void setResultat(String Resultat) {
				this.Resultat = Resultat;
			}



			public String getNom() {
				return Nom;
			}



			public void setNom(String nom) {
				Nom = nom;
			}



			public String getPrenom() {
				return Prenom;
			}



			public void setPrenom(String prenom) {
				Prenom = prenom;
			}



			public String getId() {
				return Id;
			}
			public String getDateNaissance() {
				return DateNaissance;
			}




	}


