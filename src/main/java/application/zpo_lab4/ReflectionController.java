package application.zpo_lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionController {
    @FXML
    Button btnSave;
    @FXML
    Button btnCreate;
    @FXML
    TextField className;
    @FXML
    TextArea consoleTextArea;

    Class clazz;
    @FXML
    protected void onBtnCreateClick() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String clazzName = className.getText();
        clazz = null;

        clazz = Class.forName(clazzName);

        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println(clazz!=null);






    }
    @FXML
    protected void onBtnSaveClick() {
        ;
    }
}