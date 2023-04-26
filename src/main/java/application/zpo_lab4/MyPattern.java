package application.zpo_lab4;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(MyPatterns.class)
public @interface MyPattern {
    String regex();

    String message();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyPatterns{
    MyPattern[] value();
}