import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BikeStore {

	private ArrayList<Bike> inventory = new ArrayList<>();
	
	public static void main(String[] args) {
		
		BikeStore store = new BikeStore();
		store.loadInventory("src/BikeShop3.csv");
		store.menu();
	}
	
	public void loadInventory(String filePath) {
		String line="";
		String[] values = new String[11];
		String brand;
		String category;
		String frameSize;
		double framePriceFactor;
		int qty;
		double price;
		String frameMaterial;
		double weight;
		String feature;
		double tireWidth;
		Bike curBike;
		
		//loading shoes from the csv file
		try {
			Scanner input = new Scanner(new File(filePath));
			boolean isFirstLine = true;
			while(input.hasNext()) {
				boolean bikeExists = false;
				line = input.nextLine();
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				// Split into an Array of Values based on Comma
				values = line.split(",");
				
				brand = values[0];
				category = values[1];
				frameSize = values[2];
				framePriceFactor = Double.parseDouble(values[3]);
				qty = Integer.parseInt(values[4]);
				price = Double.parseDouble(values[5]);
				frameMaterial = values[6];
				weight = Double.parseDouble(values[7]);
				if (values[8].equals("")) {
					feature = values[9];
				}else {
					feature = values[8];
				}
				tireWidth = Double.parseDouble(values[10]);
				
				for (int i = 0; i < inventory.size(); i++) {
					if (inventory.get(i).getBrand().equals(brand) && inventory.get(i).getCategory().equals(category)) {
						curBike = inventory.get(i);
						curBike.setQuantity(frameSize, qty);
						curBike.setWeight(frameSize, weight);
						curBike.setFramePriceFactor(frameSize, framePriceFactor);
						bikeExists = true;
						break;
					}
				}
				
				if (!bikeExists) {
					switch (category) {
						case "Road":
							inventory.add(new Road(brand, category, price, frameMaterial, feature, (int) tireWidth));
							curBike = inventory.get(inventory.size()-1);
							curBike.setQuantity(frameSize, qty);
							curBike.setWeight(frameSize, weight);
							curBike.setFramePriceFactor(frameSize, framePriceFactor);
							break;
						case "Mountain":
							tireWidth = Math.round(tireWidth*25/0.984252);
							inventory.add(new Mountain(brand, category, price, frameMaterial, feature, (int) tireWidth));
							curBike = inventory.get(inventory.size()-1);
							curBike.setQuantity(frameSize, qty);
							curBike.setWeight(frameSize, weight);
							curBike.setFramePriceFactor(frameSize, framePriceFactor);
							break;
					}	
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void menu() {
			
		Scanner input = new Scanner(System.in);
		String choice = "";
		
		System.out.println("================================");
		System.out.println("Welcome to Gavin's Bike Store!!!");
		System.out.println("================================\n");
		
		do {
			System.out.println("1) Bike Lookup By Brand");
			System.out.println("2) Bike Lookup By Category");
			System.out.println("3) Bike Lookup by Frame Type");
			System.out.println("4) Bike Lookup by Frame Size");
			System.out.println("5) Bike Pricing Report");
			System.out.println("6) Bike Inventory Report");
			System.out.println("7) Bike Average Price");
			System.out.println("8) Most Expensive Bike");
			System.out.println("9) Smallest Tire Bike");
			System.out.println("10) Top 3 Stocked Bike");
			
			System.out.print("\nPlease Select a Menu Option or 0 to Quit: ");
			choice = input.nextLine();
			
			switch (choice) {
				case "1":
					System.out.println("\ni. Trek");
					System.out.println("ii. Specialized");
					System.out.println("iii. Cervelo");
					System.out.println("iv. Giant");
					System.out.println("v. Cannondale");
					System.out.print("\nPlease Type a Brand Listed Above: ");
					String brand;
					brand = input.nextLine();
					System.out.println(lookupByBrand(brand));
					break;
				case "2":
					System.out.println("\ni. Road");
					System.out.println("ii. Mountain");
					System.out.print("\nPlease Type a Category Listed Above: ");
					String category;
					category = input.nextLine();
					System.out.println(lookupByCategory(category));
					break;
				case "3":
					System.out.println("\ni. Aluminum");
					System.out.println("ii. Carbon");
					System.out.print("\nPlease Type a Frame Type Listed Above: ");
					String frameMaterial;
					frameMaterial = input.nextLine();
					System.out.println(lookupByFrameType(frameMaterial));
					break;
				case "4":
					System.out.println("\ni. S");
					System.out.println("ii. M");
					System.out.println("iii. L");
					System.out.println("iv. XL");
					System.out.print("\nPlease Type a Frame Size Listed Above: ");
					String size;
					size = input.nextLine();
					System.out.println(lookupByFrameSize(size));
					break;
				case "5":
					break;
				case "6":
					break;
				case "7":
					System.out.println(bikeAveragePrice());
					break;
				case "8":
					System.out.println(mostExpensiveBike());
					break;
				case "9":
					System.out.println(smallestTireBike());
					break;
				case "10":
					break;
			}
			
			
		} while(choice.equals("0"));
		
	}
	
	public String smallestTireBike() {
		
		int lowestTireWidth = Integer.MAX_VALUE;
		Bike lowestTireWidthBike = null;
		String output = String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width");;
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTireWidth() < lowestTireWidth) {
				lowestTireWidthBike = inventory.get(i);
				lowestTireWidth = lowestTireWidthBike.getTireWidth();
			}
		}
		
		output += String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", 
				lowestTireWidthBike.getBrand(), 
				lowestTireWidthBike.getCategory(), 
				String.format("$%.2f", lowestTireWidthBike.getBasePrice()), 
				lowestTireWidthBike.getFrameMaterial(), 
				lowestTireWidthBike.getFeature(), 
				lowestTireWidthBike.getTireWidth() + "mm");
		
		return output;
	}
	
	public String bikeAveragePrice() {
		
		Bike curBike = null;
		String output = String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width");;
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			
			curBike = inventory.get(i);
			output += String.format("\n%-20s%-20s%-20s%-20s%-30s%-20s\n", 
				curBike.getBrand(), 
				curBike.getCategory(), 
				String.format("$%.2f", curBike.getBasePrice()), 
				curBike.getFrameMaterial(), 
				curBike.getFeature(), 
				curBike.getTireWidth() + "mm"); 
		}
		
		return output;
	}
	
	public String lookupByBrand(String brand) {
		
		Bike curBike = null;
		String output = String.format("\n%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width"); 
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			curBike = inventory.get(i);
			if (curBike.getBrand().equals(brand)) {
				output += String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", 
					curBike.getBrand(), 
					curBike.getCategory(), 
					String.format("$%.2f", curBike.getBasePrice()), 
					curBike.getFrameMaterial(), 
					curBike.getFeature(), 
					curBike.getTireWidth() + "mm"); 
			}
		}
		
		return output;
		
	}
	
	public String lookupByCategory(String category) {
		
		Bike curBike = null;
		String output = String.format("\n%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width"); 
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			curBike = inventory.get(i);
			if (curBike.getCategory().equals(category)) {
				output += String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", 
					curBike.getBrand(), 
					curBike.getCategory(), 
					String.format("$%.2f", curBike.getBasePrice()), 
					curBike.getFrameMaterial(), 
					curBike.getFeature(), 
					curBike.getTireWidth() + "mm"); 
			}
		}
		
		return output;
		
	}
	
	public String lookupByFrameType(String frameMaterial) {
		
		Bike curBike = null;
		String output = String.format("\n%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width"); 
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			curBike = inventory.get(i);
			if (curBike.getFrameMaterial().equals(frameMaterial)) {
				output += String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", 
					curBike.getBrand(), 
					curBike.getCategory(), 
					String.format("$%.2f", curBike.getBasePrice()), 
					curBike.getFrameMaterial(), 
					curBike.getFeature(), 
					curBike.getTireWidth() + "mm"); 
			}
		}
		
		return output;
		
	}
	
	public String lookupByFrameSize(String size) {
		
		Bike curBike = null;
		String output = String.format("\n%-20s%-20s%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Quantity", "Price", "Frame Material", "Weight", "Feature", "Tire Width"); 
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			curBike = inventory.get(i);
			if (curBike.getQuantity(size) > 0) {
				output += String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-30s%-20s\n", 
					curBike.getBrand(), 
					curBike.getCategory(), 
					curBike.getQuantity(size),
					String.format("$%.2f", curBike.getPrice(size)), 
					curBike.getFrameMaterial(),
					curBike.getWeight(size) + "kg",
					curBike.getFeature(), 
					curBike.getTireWidth() + "mm"); 
			}
		}
		
		return output;
		
	}
	
	public String mostExpensiveBike() {
		
		double highestCost = 0.00;
		Bike mostExpensive = null;
		String output = String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", "Brand", "Category", "Base Price", "Frame Material", "Feature", "Tire Width");;
		output += "---\n";
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getBasePrice() > highestCost) {
				mostExpensive = inventory.get(i);
				highestCost = mostExpensive.getBasePrice();
			}
		}
		
		output += String.format("%-20s%-20s%-20s%-20s%-30s%-20s\n", 
				mostExpensive.getBrand(), 
				mostExpensive.getCategory(), 
				String.format("$%.2f", mostExpensive.getBasePrice()), 
				mostExpensive.getFrameMaterial(), 
				mostExpensive.getFeature(), 
				mostExpensive.getTireWidth() + "mm");
		
		return output;
	}
	
	
	
}
