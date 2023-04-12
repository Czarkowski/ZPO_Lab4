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

    public EnumType setEnumType(Class<?> type){
        this.type = type;
        System.out.println(type.toString());
//        System.out.println(type.getClass().toString());
//        System.out.println(int.class.toString());
        if (type == String.class) {
            enumType = EnumType.String;
        } else if (type == Integer.class) {
            enumType = EnumType.Integer;
        }else if (type == Double.class) {
            enumType = EnumType.Double;
        }else if (type == Float.class) {
            enumType = EnumType.Float;
        }else if (type == Long.class) {
            enumType = EnumType.Long;
        }else if (type == Character.class) {
            enumType = EnumType.Character;
        }else if (type == Boolean.class) {
            enumType = EnumType.Boolean;
        }else if (type == Byte.class) {
            enumType = EnumType.Byte;
        }else {
            System.out.println("Brak dopasowania typu!");
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
