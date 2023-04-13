package application.zpo_lab4;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

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
    public static FConteiner MakeFieldConteiner(Field f , Class<?> c,Object object){
        String name = f.getName();
        Class<?> type = f.getType();
        EnumType enumType = getEnumType(type);
        if (enumType == null){
            System.out.println("unsupported type " +type.getName() + " for field " + name);
        }
        String Name = name.substring(0,1).toUpperCase() + name.substring(1);
        Method methodGet = null;
        try {
            methodGet = c.getMethod("get" + Name);
        } catch (NoSuchMethodException e) {
            System.out.println("no getter for field " + name);
        }
        if (methodGet==null)
            return null;
        if (methodGet.getReturnType() != type){
            System.out.println("different type getter return for " +name);
            return null;
        }
        Method methodSet = null;
        try {
            methodSet = c.getMethod("set" + Name,type);
        } catch (NoSuchMethodException e) {
            System.out.println("no setter for field " + name);
        }
        if (methodSet==null)
            return null;
        FConteiner fConteiner = null;
        try {
            fConteiner = new FConteiner(name,object,type,enumType,methodSet,methodGet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fConteiner;
    }

    String name;
    Object object;
    HBox hBox;
    Label label;

    Control control;
    Method methodSet;

    Method methodGet;

    Class<?> type;

    EnumType enumType;

    public static EnumType getEnumType(Class<?> type){
        EnumType enumType = null;
        System.out.println(type.toString());
        if (type.isPrimitive()){
            System.out.println("prymitywny");
        }
//        System.out.println(type.getClass().toString());
//        System.out.println(int.class.toString());
        if (type == String.class) {
            enumType = EnumType.String;
        } else if (type == Integer.class || type == int.class) {
            enumType = EnumType.Integer;
        }else if (type == Double.class || type == double.class) {
            enumType = EnumType.Double;
        }else if (type == Float.class) {
            enumType = EnumType.Float;
        }else if (type == Long.class) {
            enumType = EnumType.Long;
        }else if (type == Character.class) {
            enumType = EnumType.Character;
        }else if (type == Boolean.class|| type == boolean.class) {
            enumType = EnumType.Boolean;
        }else if (type == Byte.class) {
            enumType = EnumType.Byte;
        }else if (type == Short.class) {
            enumType = EnumType.Short;
        }else {
            System.out.println("Brak dopasowania typu!");
        }

        return enumType;
    }

    public FConteiner(String name,Object object, Class<?> type, EnumType enumType, Method methodSet, Method methodGet) throws InvocationTargetException, IllegalAccessException {
        this.name = name;
        this.object = object;
        this.methodSet = methodSet;
        this.methodGet = methodGet;
        this.type = type;
        this.enumType = enumType;

        this.hBox = new HBox();
        this.label = new Label(" <--" + name);

        if (enumType == EnumType.Boolean) {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected((boolean)this.methodGet.invoke(object));
            this.control = checkBox;
        }
        else {
            if (name.startsWith("text"))
                this.control = new TextArea();
            else
                this.control = new TextField();
            ((TextInputControl)this.control).setText(this.methodGet.invoke(object).toString());
        }
        hBox.getChildren().add(control);
        hBox.getChildren().add(label);
    }

    public FConteiner(String name) {
        this.name = name;


    }

}
