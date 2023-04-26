package application.zpo_lab4;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VinputText{

    private TextInputControl textInputControl;
    private ImageView imageView;
    private List<Validator> validators = new ArrayList<>();

    private Runnable update;

    public void setUpdate(Runnable update){
        this.update = update;
    }

    public VinputText(TextInputControl textInputControl, ImageView imageView) {
        this.textInputControl = textInputControl;
        this.textInputControl.setOnKeyTyped(e->onKeyTyped());
        this.imageView = imageView;
    }

    public void registerValidator(Validator v) {
        validators.add(v);
    }

    public boolean isGood(){
        for(Validator v : validators){
            if (!v.isValid())
                return false;
        }
        return true;
    }
    Tooltip tooltip = new Tooltip();

    private void validatorsCheck(Validator v){
        v.validate(textInputControl.getText());
        if (!v.isValid()){
            toolTipText += ('\n' + v.getMessage());
        }
    }
    private String toolTipText;
    private void onKeyTyped() {
        toolTipText = "";

        validators.forEach(this::validatorsCheck);
        if (toolTipText != "")
        {
            tooltip.setText(toolTipText);
            Tooltip.install(imageView,tooltip);
            try {
                    imageView.setImage(new Image(new FileInputStream("src\\wrong.png")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }else {
            Tooltip.uninstall(imageView,tooltip);
            try {
                imageView.setImage(new Image(new FileInputStream("src\\ok.png")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (update != null)
            update.run();
    }


}
