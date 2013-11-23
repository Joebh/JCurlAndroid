package jcurl.main.converter.tokens;

public class StringToken extends Token {

	private String value;

	public String getValue() {
		return value;
	}
	
	

	@Override
	public String toString() {
		return "StringToken [value=" + value + "]";
	}



	public void setValue(String value) {
		this.value = value;
	}

	public StringToken(String value) {
		super();
		this.value = value;
	}

}
