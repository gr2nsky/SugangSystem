package MODEL.mgr;

import java.util.*;

public interface Manageable {
	void read(Scanner scan);
	void print();
	boolean matches(String kwd);
	boolean compare(String kwd);
	public Manageable getSource();
}
