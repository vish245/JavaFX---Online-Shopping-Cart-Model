public static ElectronicStore createStore(){
	ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
	Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
	Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
	Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
	Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
	Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
	Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
	ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
	ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
	store1.addProduct(d1);
	store1.addProduct(d2);
	store1.addProduct(l1);
	store1.addProduct(l2);
	store1.addProduct(f1);
	store1.addProduct(f2);
	store1.addProduct(t1);
	store1.addProduct(t2);
	return store1;
}