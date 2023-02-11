package core;

import java.io.Serializable;

public class Question implements Serializable {
	private static final long serialVersionUID = 8442232708787579223L;
	private String mot;
	private String definition;
	private String imagePath;

	public Question() {
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getMot() {
		return mot;
	}

	public String getDefinition() {
		return definition;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int hashCode() {
		return mot.hashCode();
	}

	public boolean equals(Object bj) {
		return this.mot.equals(((Question) bj).mot);
	}

}
