
public class Mountain extends Bike{

private String suspension;
	
	public Mountain(String brand, String category, double price, String frameMaterial, String suspension, int tireWidth) {
		
		super(brand, category, price, frameMaterial, tireWidth);
				
		this.suspension = suspension;
		
	}
	
	public String getSuspension() {
		return suspension;
	}
	
	public boolean isOnSale(String size) {
		return (getQuantity(size) > 3) && !(getSuspension().equals("Dual Link") || getSuspension().equals("Horst Link"));
	}
	
	public String getFeature() {
		return "Suspension: " + getSuspension();
	}
	
}
