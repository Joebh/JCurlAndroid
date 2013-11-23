package jcurl.main.converter.syntaxtree.flags;

import jcurl.main.converter.syntaxtree.Flag;
import jcurl.main.converter.visitors.Visitor;

public class L extends Flag{

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "The Redirect Flag";
	}

}
