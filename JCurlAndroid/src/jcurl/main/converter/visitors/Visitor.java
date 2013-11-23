package jcurl.main.converter.visitors;

import jcurl.main.converter.syntaxtree.Curl;
import jcurl.main.converter.syntaxtree.Flag;
import jcurl.main.converter.syntaxtree.Method;
import jcurl.main.converter.syntaxtree.URL;
import jcurl.main.converter.syntaxtree.flags.Compressed;
import jcurl.main.converter.syntaxtree.flags.Data;
import jcurl.main.converter.syntaxtree.flags.H;
import jcurl.main.converter.syntaxtree.flags.L;

public interface Visitor {

	public void accept(Curl curl);
	public void accept(URL url);
	public void accept(Method method);
	public void accept(Flag flag);
	public void accept(H h);
	public void accept(L l);
	public void accept(Data data);
	public void accept(Compressed compressed);
	
}
