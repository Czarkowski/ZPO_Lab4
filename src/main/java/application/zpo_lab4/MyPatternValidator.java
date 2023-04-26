package application.zpo_lab4;

public class MyPatternValidator implements Validator{

    MyPattern myPattern;
    private boolean valid = false;
    public MyPatternValidator(MyPattern myPattern){
        this.myPattern = myPattern;
    }
    @Override
    public void validate(String value) {
         valid = value.matches(myPattern.regex());
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public String getMessage() {
        return myPattern.message();
    }
}
