package core;

import java.io.Serializable;
import java.util.Random;

public class De implements Serializable {

	private static final long serialVersionUID = -8015445662187526600L;

	public int lancer() {
		return ((new Random()).nextInt(6) + 1);
	}
}
