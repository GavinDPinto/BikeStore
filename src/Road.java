
public class Road extends Bike{

	private String groupSet;
	
	public Road(String brand, String category, double price, String frameMaterial, String groupSet, int tireWidth) {
		
		super(brand, category, price, frameMaterial, tireWidth);
		this.groupSet = groupSet;
		
	}
	
	public String getGroupSet() {
		return groupSet;
	}
	
	public boolean isOnSale(String size) {
		
		return (getQuantity(size) > 2) && !(getFrameMaterial().equals("Carbon"));
		
	}
	
	public String getFeature() {
		return "GrpSet: " + getGroupSet();
	}
}
