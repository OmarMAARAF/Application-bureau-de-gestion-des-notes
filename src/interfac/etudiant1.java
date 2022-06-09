package interfac;

public class etudiant1 {
    String code_apogé;
    String cne;
    String filiere;


    public etudiant1()
    {
    	super();
    }
    public etudiant1(String code_apogé, String cne , String filiere )
    {
    	this.code_apogé=code_apogé;
    	this.cne= cne ;
    	this.filiere=filiere;
    }



    public String getCode_apogé() {
		return code_apogé;
	}
	public void setCode_apogé(String code_apogé) {
		this.code_apogé = code_apogé;
	}


	 public String getCne() {
			return cne;
	}
	public void setCne(String cne) {
			this.cne = cne;
	}

	public String getFiliere() {
				return filiere;
	}
	public void setFiliere(String filiere) {
			this.filiere = filiere;
	}


}
