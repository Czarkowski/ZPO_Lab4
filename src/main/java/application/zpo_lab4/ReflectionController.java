package application.zpo_lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionController {
    @FXML
    Button btnSave;
    @FXML
    Button btnCreate;
    @FXML
    TextField className;
    @FXML
    TextArea consoleTextArea;
    @FXML
    VBox vBox;

    List<FConteiner> fConteinerList = new ArrayList<>();
    Class clazz = null;

    Object object;
    @FXML
    protected void onBtnCreateClick() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String clazzName = className.getText();
        clazz = null;

        clazz = Class.forName(clazzName);

        Constructor constructor = clazz.getConstructor();
        object = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println(clazz!=null);

        fConteinerList.clear();
        Method[] methods = clazz.getMethods();
        for (var i:declaredFields) {
            System.out.println(i.getName());
            FConteiner fConteiner = new FConteiner(i.getName());
            fConteinerList.add(fConteiner);
            vBox.getChildren().add(fConteiner.hBox);
            String Name = fConteiner.name.substring(0,1).toUpperCase() + fConteiner.name.substring(1);
            Method method = clazz.getMethod("get" + Name, null);
            Class<?> returnType = method.getReturnType();

            fConteiner.setEnumType(returnType);
            fConteiner.methodGet = method;
            method = clazz.getMethod("set" + Name, fConteiner.type);
            fConteiner.methodSet = method;

            fConteiner.textField.setText(fConteiner.methodGet.invoke(object).toString());
        }

        for (Method m: methods) {
            //System.out.println(m.getName() + " parametry :"+ Arrays.toString(m.getParameters()) + "  " + Arrays.toString(m.getParameterTypes()) + "   " + m.getReturnType());

        }

//        Class[] parameterType = null;
//        clazz.getMethod("setName",String.class);





    }
    @FXML
    protected void onBtnSaveClick() {
        fConteinerList.forEach(fConteiner ->{
            try {
                fConteiner.methodSet.invoke(object,fConteiner.textField.getText());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
    protected void setNewValue(FConteiner fConteiner){
        switch (fConteiner.enumType){
            case Integer:
        }
    }
}


//    StringBuilder methodNameStringBuilder = new StringBuilder();
//            methodNameStringBuilder.append("set");
//                    methodNameStringBuilder.append(hBoxConteiner.name.substring(0,1).toUpperCase());
//                    System.out.println(methodNameStringBuilder.toString());
//                    methodNameStringBuilder.append(hBoxConteiner.name.substring(1));
//                    String metodName = methodNameStringBuilder.toString();
//                    System.out.println(metodName);
//                    Method method = null;
//                    try {
//                    method = clazz.getMethod(metodName);
//                    } catch (NoSuchMethodException e) {
//                    throw new RuntimeException(e);
//                    }
//                    consoleTextArea.appendText(method.getName());