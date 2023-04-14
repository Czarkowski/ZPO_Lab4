package application.zpo_lab4;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

public class FContainer {
    public static FContainer MakeFieldContainer(Field f, Class<?> c) {
        String name = f.getName();
        Class<?> fieldType = f.getType();
        EnumType enumType = getEnumType(fieldType);
        if (enumType == null) {
            System.out.println("unsupported type " + fieldType.getName() + " for field " + name);
        }
        String Name = name.substring(0, 1).toUpperCase() + name.substring(1);
        Method methodGet = null;
        try {
            methodGet = c.getMethod("get" + Name);
        } catch (NoSuchMethodException e) {
            System.out.println("no getter for field " + name);
        }
        if (methodGet == null)
            return null;
        if (methodGet.getReturnType() != fieldType) {
            System.out.println("different type getter return for " + name);
            return null;
        }
        Method methodSet = null;
        try {
            methodSet = c.getMethod("set" + Name, fieldType);
        } catch (NoSuchMethodException e) {
            System.out.println("no setter for field " + name);
        }
        if (methodSet == null)
            return null;
        FContainer fContainer = null;
        fContainer = new FContainer(name, c, fieldType, enumType, methodSet, methodGet);

        return fContainer;
    }

    String name;
    HBox hBox;
    private Label label;
    private Class<?> objectType;
    private Control control;
    private Method methodSet;
    private Method methodGet;
    private Class<?> fieldType;
    private EnumType enumType;

    public static EnumType getEnumType(Class<?> type) {
        EnumType enumType = null;
        System.out.println(type.toString());

        if (type == String.class) {
            enumType = EnumType.String;
        } else if (type == Integer.class || type == int.class) {
            enumType = EnumType.Integer;
        } else if (type == Double.class || type == double.class) {
            enumType = EnumType.Double;
        } else if (type == Float.class || type == float.class) {
            enumType = EnumType.Float;
        } else if (type == Long.class || type == long.class) {
            enumType = EnumType.Long;
        } else if (type == Character.class || type == char.class) {
            enumType = EnumType.Character;
        } else if (type == Boolean.class || type == boolean.class) {
            enumType = EnumType.Boolean;
        } else if (type == Byte.class || type == byte.class) {
            enumType = EnumType.Byte;
        } else if (type == Short.class || type == short.class) {
            enumType = EnumType.Short;
        } else {
            System.out.println("Brak dopasowania typu!");
        }

        return enumType;
    }

    public FContainer(String name, Class<?> objectType, Class<?> fieldType, EnumType enumType, Method methodSet, Method methodGet) {
        this.name = name;
        this.objectType = objectType;
        this.methodSet = methodSet;
        this.methodGet = methodGet;
        this.fieldType = fieldType;
        this.enumType = enumType;

        this.hBox = new HBox();
        this.label = new Label(" <--" + name);
        if (enumType == EnumType.Boolean) {
            this.control = new CheckBox();
        } else {
            if (name.startsWith("text"))
                this.control = new TextArea();
            else
                this.control = new TextField();
        }
        hBox.getChildren().add(control);
        hBox.getChildren().add(label);
    }

    public void setControlValueFromObject(Object object) {
        if (object.getClass() != this.objectType) {
            System.out.println("Wrong object type!");
            return;
        }
        try {
            if (enumType == EnumType.Boolean) {
                ((CheckBox) this.control).setSelected((boolean) this.methodGet.invoke(object));
            } else {
                ((TextInputControl) this.control).setText(this.methodGet.invoke(object).toString());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public boolean setFieldValueForObject(Object object) {
        if (object.getClass() != this.objectType) {
            System.out.println("Wrong object type!");
            return false;
        }
        boolean good = false;
        try {
            if (enumType == EnumType.Boolean) {
                Boolean newBoolean = ((CheckBox) this.control).isSelected();
                this.methodSet.invoke(object, newBoolean);
                good = true;
            } else {
                String newString = ((TextInputControl) this.control).getText();
                Object newValue = null;
                switch (this.enumType) {
                    case Byte -> {
                        newValue = Byte.parseByte(newString);
                    }
                    case Short -> {
                        newValue = Short.parseShort(newString);
                    }
                    case Integer -> {
                        newValue = Integer.parseInt(newString);
                    }
                    case Long -> {
                        newValue = Long.parseLong(newString);
                    }
                    case Float -> {
                        newValue = Float.parseFloat(newString);
                    }
                    case Double -> {
                        newValue = Double.parseDouble(newString);
                    }
                    case String -> {
                        newValue = newString;
                    }
                    case Character -> {
                        newValue = newString.charAt(0);
                    }
                }
                System.out.println(newValue.getClass().toString());
                this.methodSet.invoke(object, newValue);
                good = true;
            }
        } catch (IllegalAccessException | InvocationTargetException | NumberFormatException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return good;
    }

    public Object getFieldValueFromObject(Object object) {
        Object value = null;
        try {
            value = methodGet.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return value;
    }


}
