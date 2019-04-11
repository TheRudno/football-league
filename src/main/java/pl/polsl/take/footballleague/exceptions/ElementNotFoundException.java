package pl.polsl.take.footballleague.exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(Class tClass){
        super(tClass.getSimpleName() + ": requested element not found.");
    }
}
