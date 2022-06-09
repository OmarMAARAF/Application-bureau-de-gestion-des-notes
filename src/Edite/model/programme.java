package Edite.model;

public class programme {


	//les colonnes
	private String jour; // dans une colonne tout seul

	private String  _8_10,_10_12,_14_16 , _16_18 ;

	public programme(String jour,String first, String second, String third, String forth) {
		this.jour=jour ;
		this._8_10=first;
		this._10_12=second ;
		this._14_16=third ;
		this._16_18=forth ;

	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public String get_8_10() {
		return _8_10;
	}

	public void set_8_10(String _8_10) {
		this._8_10 = _8_10;
	}

	public String get_10_12() {
		return _10_12;
	}

	public void set_10_12(String _10_12) {
		this._10_12 = _10_12;
	}

	public String get_14_16() {
		return _14_16;
	}

	public void set_14_16(String _14_16) {
		this._14_16 = _14_16;
	}

	public String get_16_18() {
		return _16_18;
	}

	public void set_16_18(String _16_18) {
		this._16_18 = _16_18;
	}




}
