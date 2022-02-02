import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ElectronicStoreButtonPane extends Pane {
    private Button reset, add, remove, complete;

    public ElectronicStoreButtonPane(){
        Pane allButtons = new Pane();

        // Reset Store Button
        reset = new Button("Reset Store");
        reset.relocate(20,0);
        reset.setPrefSize(135,50);

        // Add to Cart Button
        add = new Button("Add to Cart");
        add.relocate(265,0);
        add.setPrefSize(135,50);

        // Remove from Cart Button
        remove = new Button("Remove form Cart");
        remove.relocate(490,0);
        remove.setPrefSize(145,50);

        //Complete Sale Button
        complete = new Button("Complete Sale");
        complete.relocate(635,0);
        complete.setPrefSize(135,50);

       getChildren().addAll(reset, add, remove, complete);


    }

    public Button getReset() { return reset;}
    public Button getAdd() { return add;}
    public Button getRemove() { return remove;}
    public Button getComplete() { return complete;}

}
