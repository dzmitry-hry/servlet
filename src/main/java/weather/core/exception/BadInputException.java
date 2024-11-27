package weather.core.exception;

public class BadInputException extends Exception{
public BadInputException(){
    super("City or coordinates must be specified");
}
}
