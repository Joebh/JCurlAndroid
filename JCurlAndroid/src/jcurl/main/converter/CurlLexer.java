package jcurl.main.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jcurl.main.converter.tokens.*;

public class CurlLexer {

	private char[] curlString;
	private int index;
	private static final States fsm[][];
	private static Logger log = Logger.getLogger(CurlLexer.class.getName());
	
	/**
	 * Build the fsm used in lexing
	 */
	static {
		fsm = new States[States.values().length][Transitions.values().length];
		for (int i = 0; i < fsm.length; i++) {
			for (int j = 0; j < fsm[i].length; j++) {
				fsm[i][j] = States.ERROR;
			}
		}

		fsm[States.START.i][Transitions.C.i] = States.AFTER_C;
		fsm[States.START.i][Transitions.DASH.i] = States.AFTER_DASH;
		fsm[States.START.i][Transitions.QUOTE.i] = States.AFTER_QUOTE;
		fsm[States.START.i][Transitions.SPACE.i] = States.START;
		fsm[States.START.i][Transitions.EOFTRANSITION.i]= States.START; 

		fsm[States.AFTER_C.i][Transitions.U.i] = States.AFTER_CU;

		fsm[States.AFTER_CU.i][Transitions.R.i] = States.AFTER_CUR;

		fsm[States.AFTER_CUR.i][Transitions.L.i] = States.AFTER_CURL;

		fsm[States.AFTER_CURL.i][Transitions.SPACE.i] = States.START;
		fsm[States.AFTER_CURL.i][Transitions.EOFTRANSITION.i]= States.START;

		fsm[States.AFTER_DASH.i][Transitions.DASH.i] = States.AFTER_DASHDASH;
		fsm[States.AFTER_DASH.i][Transitions.ALPHA.i] = States.AFTER_DASHES;

		fsm[States.AFTER_DASHDASH.i][Transitions.ALPHA.i] = States.AFTER_DASHES;

		fsm[States.AFTER_DASHES.i][Transitions.ALPHA.i] = States.AFTER_DASHES;
		fsm[States.AFTER_DASHES.i][Transitions.SPACE.i] = States.START;
		fsm[States.AFTER_DASHES.i][Transitions.EOFTRANSITION.i] = States.START;

		fsm[States.AFTER_QUOTE.i][Transitions.NOTQUOTE.i] = States.IN_STRING;
		fsm[States.AFTER_QUOTE.i][Transitions.QUOTE.i] = States.DONE_STRING;
		
		fsm[States.IN_STRING.i][Transitions.NOTQUOTE.i] = States.IN_STRING;
		fsm[States.IN_STRING.i][Transitions.QUOTE.i] = States.DONE_STRING;

		fsm[States.DONE_STRING.i][Transitions.SPACE.i] = States.START;
		fsm[States.DONE_STRING.i][Transitions.EOFTRANSITION.i] = States.START;
	}

	CurlLexer(String curlString) {
		curlString = curlString + "$";
		this.curlString = curlString.toCharArray();
		this.index = 0;
	}

	public Token nextToken() {
		log.finest("NEXT TOKEN CALLED");
		States state = States.START;
		StringBuilder value = new StringBuilder();
		States previousState = States.START;
		log.finest(state+"");
		do {			
			// get next char
			if(index == curlString.length){
				return new EOFToken();
			}
			String nextChar = curlString[index++] + "";
			log.finest("next char " + nextChar);
			
			//append char to value
			value.append(nextChar);
			
			//save previous state
			previousState = state;
			
			//use regex in transition enum to see what state to goto next
			Transitions[] transitions = state.transitions;
			boolean matches = false;
			for(Transitions transition : transitions){
				log.finest("Trying to match transition " + transition);
				if(nextChar.matches(transition.regex)){
					matches = true;
					log.finest("Matched " + transition);
					state = fsm[state.i][transition.i];
				}
			}
			if(!matches){
				state = States.ERROR;
			}
			log.finest("CURRENT STATE " + state+"\tPREVIOUS STATE " + previousState);
			
			if(state == States.ERROR){
				return new ErrorToken();
			}
		} while (state != States.START);
		
		
		if(previousState == States.AFTER_CURL){
			return new CurlToken();
		}
		value.deleteCharAt(value.length()-1);
		
		if(previousState == States.DONE_STRING){
			value.deleteCharAt(value.length()-1);
			value.deleteCharAt(0);
			return new StringToken(value.toString());
		}
		if(previousState == States.AFTER_DASHES){
			return new FlagToken(value.toString());
		}

		return nextToken();
	}
}
