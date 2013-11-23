package jcurl.main.converter.syntaxtree.flags;

import jcurl.main.converter.syntaxtree.Flag;
import jcurl.main.converter.visitors.Visitor;

public class H extends Flag {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public H(String value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}

	@Override
	public String toString() {
		return "H [value=" + value + "]";
	}

}
