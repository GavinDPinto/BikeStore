
public class Bike {
	
	private String brand;
	private String category;
	private String frameMaterial;
	private double price;
	private int tireWidth; // in mm
	private double[] framePriceFactor;
	private double[] weight;
	private int[] quantity;
	
	public Bike(String brand, String category, double price, String frameMaterial, int tireWidth) {
		this.brand = brand;
		this.category = category;
		this.frameMaterial = frameMaterial;
		this.price = price;
		this.tireWidth = tireWidth;
		framePriceFactor = new double[4]; // S, M, L, XL
		quantity = new int[4]; // S, M, L, XL
		weight = new double[4]; // S, M, L, Xl
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getFrameMaterial() {
		return frameMaterial;
	}
	
	public int getTireWidth() {
		return tireWidth;
	}
	
	public void setWeight(String size, double bikeWeight) {
		switch (size) {
			case "S":
				weight[0] = bikeWeight;
				break;
			case "M":
				weight[1] = bikeWeight;
				break;
			case "L":
				weight[2] = bikeWeight;
				break;
			case "XL":
				weight[3] = bikeWeight;
				break;
		}
	}
	
	public double getWeight(String size) {
		switch (size) {
			case "S":
				return weight[0];
			case "M":
				return weight[1];
			case "L":
				return weight[2];
			case "XL":
				return weight[3];
		}
		//random value to avoid syntax error
		return 0.00;
	}
	
	public void setQuantity(String size, int qty) {
		switch (size) {
			case "S":
				quantity[0] = qty;
				break;
			case "M":
				quantity[1] = qty;
				break;
			case "L":
				quantity[2] = qty;
				break;
			case "XL":
				quantity[3] = qty;
				break;
		}
	}
	
	public int getQuantity(String size) {
		switch (size) {
			case "S":
				return quantity[0];
			case "M":
				return quantity[1];
			case "L":
				return quantity[2];
			case "XL":
				return quantity[3];
		}
		//random value to avoid syntax error
		return 0;
	} 
	
	public void setFramePriceFactor(String size, double priceFactor) {
		switch (size) {
			case "S":
				framePriceFactor[0] = priceFactor;
				break;
			case "M":
				framePriceFactor[1] = priceFactor;
				break;
			case "L":
				framePriceFactor[2] = priceFactor;
				break;
			case "XL":
				framePriceFactor[3] = priceFactor;
				break;
		}
	}
	
	public double getFramePriceFactor(String size) {
		switch (size) {
			case "S":
				return framePriceFactor[0];
			case "M":
				return framePriceFactor[1];
			case "L":
				return framePriceFactor[2];
			case "XL":
				return framePriceFactor[3];
		}
		return 1.0;
	} 
	
	public double getPrice(String size) {
		double tempPrice = 0.00;
		switch (size) {
			case "S":
				tempPrice = Math.round(getBasePrice()*framePriceFactor[0]*100)/100.00;
				break;
			case "M":
				tempPrice = Math.round(getBasePrice()*framePriceFactor[1]*100)/100.00;
				break;
			case "L":
				tempPrice = Math.round(getBasePrice()*framePriceFactor[2]*100)/100.00;
				break;
			case "XL":
				tempPrice = Math.round(getBasePrice()*framePriceFactor[3]*100)/100.00;
				break;
		}
		
		if (isOnSale(size)) {
			return Math.round(tempPrice*0.75*100)/100.00;
		}
		
		if (getFrameMaterial().equals("Carbon")) {
			return Math.round(tempPrice*0.85*100)/100.00;
		}
		
		return tempPrice;
	}
	
	public double getBasePrice() {
		return price;
	}
	
	public boolean isOnSale(String size) {
		return false;
	}
	
	public String getFeature() {
		return "";
	}
	
}


	
	
