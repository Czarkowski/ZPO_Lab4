package application.zpo_lab4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    List<FContainer> fContainerList = new ArrayList<>();
    Class<?> clazz;
    Object object;

    @FXML
    protected void onBtnCreateClick() {
        vBox.getChildren().clear();
        fContainerList.clear();
        clazz = null;
        object = null;

        String clazzName = className.getText();
        boolean isClass = false;
        try {
            clazz = Class.forName(clazzName);
            Constructor<?> constructor = clazz.getConstructor();
            object = constructor.newInstance();
            isClass = true;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        if (!isClass)
            return;
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field f : declaredFields) {
            System.out.println(f.getName());

            FContainer fContainer = FContainer.MakeFieldContainer(f, clazz);
            if (fContainer == null) {
                System.out.println("Cannot create FContainer object for field: " + f.getName());
                continue;
            }
            fContainer.setControlValueFromObject(object);
            fContainerList.add(fContainer);
            vBox.getChildren().add(fContainer.hBox);
        }

    }

    @FXML
    protected void onBtnSaveClick() {
        consoleTextArea.clear();
        fContainerList.forEach(fContainer ->{
            if (!fContainer.setFieldValueForObject(object)){
                consoleTextArea.appendText("The property " + fContainer.name + " was not changed successfully!\n");
            }
            consoleTextArea.appendText(fContainer.name+ " = "+ fContainer.getFieldValueFromObject(object).toString()+"\n");
        });
    }

}