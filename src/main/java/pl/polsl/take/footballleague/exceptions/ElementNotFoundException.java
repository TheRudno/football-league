package pl.polsl.take.footballleague.exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(){
        super("Requested element not found.");
    }
}
