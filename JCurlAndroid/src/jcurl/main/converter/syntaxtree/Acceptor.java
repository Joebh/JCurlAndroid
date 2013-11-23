package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public interface Acceptor {

	public void accept(Visitor visitor);
	
}
