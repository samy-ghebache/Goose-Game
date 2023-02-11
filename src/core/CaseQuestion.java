package core;


import java.io.Serializable;

public abstract class CaseQuestion extends Case implements Serializable {

	private static final long serialVersionUID = 4877183426440360823L;
	protected Question question;

	public void setQuestion(Question question) {
		this.question = question;
	}

	public abstract boolean evaluate(String input);

}
