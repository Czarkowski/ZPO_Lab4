package application.zpo_lab4;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.lang.reflect.Method;

enum EnumType {
    Boolean,
    Byte,
    Short,
    Integer,
    Long,
    Float,
    Double,
    String,
    Character
}

public class FConteiner {
    String name;
    HBox hBox;
    Label label;
    TextField textField;
    TextArea textArea;

    Method methodSet;

    Method methodGet;

    Class<?> type;

    EnumType enumType;

    public EnumType SetEnumType(Class<?> type){
        this.type = type;
        if (type == Integer.class) {
            enumType = EnumType.Integer;
            System.out.println("integer");
        }
        return enumType;
    }

    public FConteiner(String name) {
        this.name = name;
        this.hBox = new HBox();
        this.label = new Label(" <--" + name);
        this.textField = new TextField();
        this.textArea = null;
        hBox.getChildren().add(textField);
        hBox.getChildren().add(label);
    }

}
