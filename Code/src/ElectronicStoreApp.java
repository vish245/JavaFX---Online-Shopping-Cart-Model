import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;

    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView();
    }


    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        //Create the view
        ElectronicStoreView view = new ElectronicStoreView();

        //Updating the GUI
        view.update(model);


        // CONTROLS

        // Handles enable/disable of add to cart button.
        view.getStoreStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
            view.update(model);
            }
        });

        // Handles enable/disable of remove button.
        view.getCurrCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

         //Add to Cart button
        view.getButtonPane().getAdd().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                int selectedIndex = view.getStoreStockList().getSelectionModel().getSelectedIndex();
                Product selectedProduct = view.getStoreStockList().getSelectionModel().getSelectedItem();


                    model.addProduct(selectedIndex);
                    view.update(model);
            }
        });

        // Remove Button
        view.getButtonPane().getRemove().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                int selectedIndex = view.getCurrCartList().getSelectionModel().getSelectedIndex();
                Product selectedProduct = view.getStoreStockList().getSelectionModel().getSelectedItem();

                if (model.getCurrCart().size() != 0){
                    model.removeProduct(selectedIndex);
                    view.update(model);
                }
            }
        });

        // Complete Sale Button
        view.getButtonPane().getComplete().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.sellProducts();
                view.update(model);

            }
        });

        // Remove Button
        view.getButtonPane().getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                view.update(model);

            }
        });

        aPane.getChildren().add(view);
        primaryStage.setTitle(model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
