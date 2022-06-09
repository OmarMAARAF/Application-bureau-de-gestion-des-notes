package controllers;

public class Cours {


	//les colonnes
	private String jour; // dans une colonne tout seul

	private String  firstHour,secondHour,thirdHour , forthHour ;

	public Cours(String jour,String firstHour, String secondHour, String thirdHour, String forthHour) {
		this.jour=jour ;
		this.firstHour=firstHour ;
		this.secondHour=secondHour ;
		this.thirdHour=thirdHour ;
		this.forthHour=forthHour ;

	}

	public String getSecondHour() {
		return secondHour;
	}

	public void setSecondHour(String secondHour) {
		this.secondHour = secondHour;
	}

	public String getThirdHour() {
		return thirdHour;
	}

	public void setThirdHour(String thirdHour) {
		this.thirdHour = thirdHour;
	}

	public String getForthHour() {
		return forthHour;
	}

	public void setForthHour(String forthHour) {
		this.forthHour = forthHour;
	}

	public String getFirstHour() {
		return firstHour;
	}

	public void setFirstHour(String firstHour) {
		this.firstHour = firstHour;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}
	


}
