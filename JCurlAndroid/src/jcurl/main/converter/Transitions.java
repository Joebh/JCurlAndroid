package jcurl.main.converter;

public enum Transitions {
	C(0, "c"), U(1, "u"), R(2, "r"), L(3, "l"),
	DASH(4, "\\-"), SPACE(5, "[ \n]"), QUOTE(6, "[']"),
	NOTQUOTE(7, "[^']"), ALPHA(8, "[a-zA-Z]"), 
	EOFTRANSITION(9, "\\$");
	
	int i;
	String regex;
	
	Transitions(int i, String regex){
		this.i = i;
		this.regex = regex;
	}
}
