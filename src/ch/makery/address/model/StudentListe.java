package ch.makery.address.model;

public class StudentListe{


		private  String CNE;
		private  String Nom;
		private  String Prenom;
		private  String DateNaissance ;
		private String CodeApoge;



			/**
			 * Constructor with some initial data.

			 */

		public StudentListe(String cne,String nom, String prenom,String codeApoge,String dateNaissance) {
			super();
			 CNE =cne;
			 Nom = nom;
			 Prenom = prenom;
			 CodeApoge=codeApoge;
			 DateNaissance=dateNaissance;


		}



			public StudentListe() {
				super();
				 CNE ="";
				 Nom = "";
				 Prenom = "";
				 CodeApoge="";
				 DateNaissance="";


			}

			public String getCNE(){
				return CNE;
			}

			public void setCNE(String cne){
				CNE=cne;
			}


			public String getNom() {
				return Nom;
			}

			public void setCodeApoge(String codeApoge) {
				CodeApoge=codeApoge;
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



			public String getCodeApoge() {
				return CodeApoge;
			}

			public String getDateNaissance() {
				return DateNaissance;
			}

			public void setDateNaissance(String dateNaissance) {
				DateNaissance = dateNaissance;
			}


	}


