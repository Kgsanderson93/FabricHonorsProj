import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class FabricInventory_jkp extends ArrayList<Fabric> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5456410210997465377L;

	public FabricInventory_jkp() {
		// TODO Auto-generated constructor stub
	}

	public FabricInventory_jkp(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}

	public FabricInventory_jkp(Collection<Fabric> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Fabric> containsFabric(String name) {
		ArrayList<Fabric> hitList = new ArrayList<>();
		for (Fabric ele: this) {
			if (ele.getName().equalsIgnoreCase(name)) {
				hitList.add(ele);
			}
		}
		return hitList;
	}
	
	public ArrayList<Fabric> containsMaterial(String material) {
		return containsMaterial(this, material);
	}
	
	public ArrayList<Fabric> containsMaterial(ArrayList<Fabric> searchList, String material) {
		ArrayList<Fabric> hitList = new ArrayList<>();
		for (Fabric ele: this) {
			String fabricMaterial =  ele.getBase();
			if (fabricMaterial != null && fabricMaterial.equalsIgnoreCase(material)) {
				hitList.add(ele);
			}
		}
		return hitList;
	}
	
	public ArrayList<Fabric> findStretch(int low, int high) {
		ArrayList<Fabric> hitList = new ArrayList<>();
		for (Fabric ele: this) {
			int fabricStretch = ele.getStretch();
			if (low <= fabricStretch && fabricStretch <= high) {
				hitList.add(ele);
			}
		}
		return hitList;
	}
	
	public ArrayList<Fabric> findStretch(ArrayList<Fabric> searchList, int low, int high) {
		ArrayList<Fabric> hitList = new ArrayList<>();
		for (Fabric ele: searchList) {
			int fabricStretch = ele.getStretch();
			if (low <= fabricStretch && fabricStretch <= high) {
				hitList.add(ele);
			}
		}
		return hitList;
	}
	
	

}
