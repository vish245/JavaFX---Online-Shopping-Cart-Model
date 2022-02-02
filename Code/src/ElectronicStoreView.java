import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import java.util.ArrayList;


public class ElectronicStoreView extends Pane {
    private Label                       current_cart;
    private TextField                   sField, rField, dField;
    private ListView<Product>           mostPopularList, storeStockList;
    private ListView<String>            currCartList;
    private ElectronicStoreButtonPane   buttonPane;


    // Get methods
    public ListView<Product> getMostPopularList() {return mostPopularList;}
    public ListView<Product> getStoreStockList() {return storeStockList;}
    public ListView<String> getCurrCartList() {return currCartList;}
    public ElectronicStoreButtonPane getButtonPane() {return buttonPane;}

    public void update(ElectronicStore model) {
        // Update Store Stock
        storeStockList.setItems(FXCollections.observableArrayList(model.getInStockItems()));

        // enable/disable the add button
        int selectedStockProduct = getStoreStockList().getSelectionModel().getSelectedIndex();
        buttonPane.getAdd().setDisable(selectedStockProduct < 0);

        // Update Current Cart list
        currCartList.setItems(FXCollections.observableArrayList(model.getFinalCartItems()));

        // enable/disable the remove button
        int selectedCartIndex = getCurrCartList().getSelectionModel().getSelectedIndex();
        buttonPane.getRemove().setDisable(selectedCartIndex < 0);

        // enable/disable the complete sale button.
        buttonPane.getComplete().setDisable(model.getFinalCartItems().size() == 0);

        // Update currCartTotal
        double totalCartAmt = model.currCartTotal();
        current_cart.setText("Current Cart " + "($" + totalCartAmt + ")");


        // Sales textField
        sField.setText(String.valueOf(model.getSales()));

        // Revenue TextField
        rField.setText(String.valueOf(model.getRevenue()));

        // $/Sale field
        if (model.getRevenue() != 0) {
            double DperSale = model.get$perSale();
            String d = String.format("%.2f", DperSale);
            dField.setText(d);
        }
        
        else{
            dField.setText("N/A");
        }

        // mostPopularItems List view.
        mostPopularList.setItems(FXCollections.observableArrayList(model.mostPopular()));
    }

    public ElectronicStoreView(){

        //Creating Labels
        Label store_sum = new Label("Store Summary:");
        store_sum.relocate(45,20);

        Label sales = new Label("# Sales:");
        sales.relocate(33,45);

        Label revenue = new Label("Revenue:");
        revenue.relocate(25,80);

        Label rev_per_sale = new Label("$ / Sale:");
        rev_per_sale.relocate(32,115);

        Label most_popular_items = new Label("Most Popular Items:");
        most_popular_items.relocate(40,155);

        Label store_stock = new Label("Store Stock:");
        store_stock.relocate(305,20);

        current_cart = new Label();
        current_cart.relocate(575,20);


        // Create textFields
        sField = new TextField();
        sField.relocate(85,40);
        sField.setPrefSize(105,30);

        rField = new TextField();
        rField.relocate(85,75);
        rField.setPrefSize(105,30);

        dField = new TextField();
        dField.relocate(85,110);
        dField.setPrefSize(105,30);

        // Creating ListViews
        mostPopularList = new ListView<Product>();
        mostPopularList.relocate(10,175);
        mostPopularList.setPrefSize(180,150);

        storeStockList = new ListView<Product>();
        storeStockList.relocate(200,40);
        storeStockList.setPrefSize(285,285);

        currCartList = new ListView<String>();
        currCartList.relocate(495,40);
        currCartList.setPrefSize(285,285);

        // Create the Button Pane
        buttonPane = new ElectronicStoreButtonPane();
        buttonPane.relocate(10,335);

        getChildren().addAll(store_sum, sales, revenue, rev_per_sale, most_popular_items, store_stock, current_cart,
                sField, rField, dField, mostPopularList, storeStockList, currCartList, buttonPane);
        setPrefSize(800,400);

    }

}
