//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.*;

public class ElectronicStore {
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  private String name;
  private Product[] stock; //Array to hold all products
  private double revenue;
  private int                     sales;
  private double                 $perSale;
  private ArrayList<Product> currCart;
  private ArrayList<String> finalCartItems;


  public ElectronicStore(String initName) {
    revenue = 0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    currCart = new ArrayList<>();
    finalCartItems = new ArrayList<>();
    sales = 0;
    $perSale = 0;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Product> getCurrCart() {
    return currCart;
  }

  public ArrayList<String> getFinalCartItems() {
    return finalCartItems;
  }

  public double getRevenue() {return revenue;}

  public double get$perSale() {return $perSale; }

  public int getSales() { return sales; }


  public void addProduct(Product newProduct) {
    if (curProducts < MAX_PRODUCTS) {
      stock[curProducts] = newProduct;
      curProducts++;
    }
  }

  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modifie
  public void sellProducts() {
    for (int i = 0; i < prodSeqInCart().size(); i++) {
      Product selectedProduct = prodSeqInCart().get(i);
      int amount = frequencyCounter(selectedProduct);
      selectedProduct.sellUnits(amount);
    }
    // Handling revenue
    revenue = revenue + currCartTotal();

    // Handling number of Sales
    sales += 1;

    // Handling $/Sales
    $perSale = revenue/sales;

    currCart.clear();

    // Removes all items from the CurrentCartListView
    for (int i = 0; i < finalCartItems.size(); i++) {
      finalCartItems.remove(i);
      i--;
    }
  }

  public static ElectronicStore createStore() {
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

  // Returns the list of all Products in stock.
  public ArrayList<Product> getElectronicStoreList() {
    ArrayList<Product> list = new ArrayList<>();
    for (int i = 0; i < curProducts; i++) {
      if (stock[i] != null) {
        list.add(stock[i]);
      }
    }
    return list;
  }


  // Returns a Array of items in stockList after handling stockQuantity = 0 for any product in the array.
  public ArrayList<Product> getInStockItems() {
    // go through our store stock and see
    // if value of store stock not 0 and not null, remove it from the list.
    ArrayList<Product> inStockList = getElectronicStoreList();
    for (int i = 0; i < inStockList.size(); i++) {
      Product requiredProduct = inStockList.get(i);
      if (getFinalCartItems().size() != 0 && frequencyCounter(inStockList.get(i)) == 10 || requiredProduct.getStockQuantity() == 0) {
        inStockList.remove(i);
        i--;
      }
    }
    return inStockList;
  }

  // Given the number of times a selected product is added to the currCart
  public int frequencyCounter(Product selectedProduct) {
    return Collections.frequency(currCart, selectedProduct);
  }


  // Handles adding products to the currentCartListView and updates the quantity of each product remomved.
  public void addProduct(int selectedItems) {
    Product selectedProduct = getInStockItems().get(selectedItems);

    if (frequencyCounter(selectedProduct) < selectedProduct.getStockQuantity())
      currCart.add(selectedProduct);

    if (currCart.size() != 0 && frequencyCounter(selectedProduct) <= 1 && frequencyCounter(selectedProduct) <= selectedProduct.getStockQuantity()) {
      finalCartItems.add(getCartString(selectedProduct));
    }
    else {
      if (finalCartItems.size() != 0) {
        finalCartItems.set(finalCartItems.indexOf(addAtIndex(selectedProduct)), getCartString(selectedProduct));
      }
    }
  }

  // Gives the order in which all products were added in currentCartList
  // without repeating them, helps map the indexes with the ones in stockListView.
  public ArrayList<Product> prodSeqInCart() {
    ArrayList<Product> sequenceArray = new ArrayList<>(curProducts);
    for (Product product : currCart) {
      if (!sequenceArray.contains(product)) {
        sequenceArray.add(product);
      }
    }
    return sequenceArray;
  }


  // Removes selected product from the currCart.
  public void removeProduct(int selectedItem) {
    Product selectedProduct = prodSeqInCart().get(selectedItem);

    // If there are more than 1 items in currCart for the selected product.
    if (frequencyCounter(selectedProduct) > 1 && getInStockItems().contains(selectedProduct)) {
      currCart.remove(selectedProduct);
      finalCartItems.set(selectedItem, getCartString(selectedProduct));

    } else if (frequencyCounter(selectedProduct) == 1 && finalCartItems.size() == 1) {
      currCart.remove(selectedProduct);
      finalCartItems.remove(selectedItem);

    } else if (frequencyCounter(selectedProduct) == 1 && finalCartItems.size() != 1) {
      currCart.remove(selectedProduct);
      finalCartItems.remove(selectedItem);

    } else if (frequencyCounter(selectedProduct) == 10 && !getInStockItems().contains(selectedProduct)) {
      currCart.remove(selectedProduct);
      getElectronicStoreList().add(selectedProduct);
      finalCartItems.set(selectedItem, getCartString(selectedProduct));
    }
  }

  // Returns the total of the current Cart
  public double currCartTotal() {
    double cartTotal = 0;
    if (currCart.size() != 0 && finalCartItems.size() != 0) {
      for (int i = 0; i < currCart.size(); i++) {
        cartTotal = cartTotal + currCart.get(i).getPrice();
      }
    }
    else{
      cartTotal = 0;
    }
    return cartTotal;
  }

  // Comparator for sorting the list by soldQuantity
  public static Comparator<Product> soldQuantity = new Comparator<Product>() {
    @Override
    public int compare(Product p1, Product p2) {
        int soldQ1 = p1.getSoldQuantity();
        int soldQ2 = p2.getSoldQuantity();

        return soldQ2-soldQ1;
      }
  };

  // Sorting Based on SoldQuantity
  public ArrayList<Product> mostPopular(){
    ArrayList<Product> compareArray = getElectronicStoreList();
    ArrayList<Product> mostPopular = new ArrayList<>(3);

    compareArray.sort(soldQuantity);

    mostPopular.add(compareArray.get(0));
    mostPopular.add(compareArray.get(1));
    mostPopular.add(compareArray.get(2));

    return mostPopular;
  }

  // toString for each product and its frequency.
  public String getCartString(Product selectedProduct) {
    String ans = frequencyCounter(selectedProduct) + " x " + selectedProduct.toString();
    return ans;
  }

  // Helps find index at which the string for a product in stored in the currentCartListView
  public String addAtIndex(Product selectedProduct) {
    String ans = (frequencyCounter(selectedProduct) - 1) + " x " + selectedProduct.toString();
    return ans;
  }

}