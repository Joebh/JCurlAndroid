package jcurl.main.converter;

public enum States {
	START(0, new Transitions[]{Transitions.C, Transitions.DASH, Transitions.SPACE, Transitions.QUOTE, Transitions.EOFTRANSITION}), 
	AFTER_C(1, new Transitions[]{Transitions.U}), 
	AFTER_CU(2, new Transitions[]{Transitions.R}), 
	AFTER_CUR(3, new Transitions[]{Transitions.L}), 
	AFTER_CURL(4, new Transitions[]{Transitions.SPACE, Transitions.EOFTRANSITION}),
	AFTER_DASH(5, new Transitions[]{Transitions.DASH, Transitions.ALPHA}), 
	AFTER_DASHDASH(6, new Transitions[]{Transitions.ALPHA}), 
	AFTER_DASHES(7, new Transitions[]{Transitions.SPACE, Transitions.ALPHA, Transitions.EOFTRANSITION}),
	AFTER_QUOTE(8, new Transitions[]{Transitions.QUOTE, Transitions.NOTQUOTE}), 
	IN_STRING(9, new Transitions[]{Transitions.QUOTE, Transitions.NOTQUOTE}), 
	DONE_STRING(10, new Transitions[]{Transitions.SPACE, Transitions.EOFTRANSITION}),
	EOF(11, null),
	ERROR(-1, null);
	
	int i;
	Transitions[] transitions;
	
	States(int i, Transitions[] transitions){
		this.i = i;
		this.transitions = transitions;
	}
	
}
