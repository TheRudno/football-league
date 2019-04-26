package pl.polsl.take.footballleague.utils;

import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.exceptions.NoEnumConstantException;

import java.util.Objects;
import java.util.function.Consumer;

public class Utils {
    public static <T> void consumeIfNonNull(Consumer<T> consumer,T object){
        if(Objects.nonNull(object)){
            consumer.accept(object);
        }
    }

    public static <T> void consumeEnumIfNonNull(Consumer<T> consumer,T object) throws NoEnumConstantException{
        if(Objects.nonNull(object)){
            try{
                consumer.accept(object);
            }catch(Exception exception){
                throw new NoEnumConstantException(object + " is not correct value of position");
            }
        }
    }
}
