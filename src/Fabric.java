//Defines a Fabric and its variables

import java.io.Serializable;



public class Fabric implements Serializable {
	
	public final static String[] ALL_USES = { "Shirt", "Pants", "Underwear", "Outerwear", "Dresses" };

	/**
	 * 
	 */
	private static final long serialVersionUID = -6023935205525499701L;
	
	private String name;
	
	private String base;
	
	private double yardage;
	
	private int stretch = 100;
	
	private String uses = "";
	
	
	
	
	public Fabric(String name, String base, double amount) {
		this(name, amount, base, 100, allUsesToString()); //All of them.
	}
	
	public Fabric(String name, String base, double amount, int stretch) {
		this(name, amount, base, stretch, allUsesToString()); //All of them.
	}
	
	public Fabric(String name, double amount, String base, String uses) {
		this(name, amount, base, 100, uses);
	}
	
	public Fabric(String fabricName, double yardage, String base, int stretch, String uses) {
		this.name = fabricName;
		this.base = base;
		this.yardage = yardage;
		this.stretch = stretch;
		this.setUses(uses);
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public double getYardage() {
		return yardage;
	}

	public void setYardage(double yardage) {
		this.yardage = yardage;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public String toString() { return "Fabric {\n\tname=" + name + "\n\tbase=" + base +
			"\n\tyardage=" + yardage + "\n\tstretch=" + stretch + "\n\tuses=" + uses + 
			"\n}"; }
	 

	public int getStretch() {
		return stretch;
	}

	public void setStretch(int stretch) {
		this.stretch = stretch;
	}
	
	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	private static String allUsesToString() {
		String allUses = "";
		for(String use : ALL_USES)
			allUses=allUses + " " + use;
		return allUses;
	}
	
	

}
