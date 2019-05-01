package pl.polsl.take.footballleague.utils;

import pl.polsl.take.footballleague.exceptions.ConversionException;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {
    public static <T> void consumeIfNonNull(Consumer<T> consumer,T object){
        if(Objects.nonNull(object)){
            consumer.accept(object);
        }
    }

    public static <T,V> void convertAndConsumeIfNonNull(Consumer<T> consumer, Function<V,T> converter, V object) throws ConversionException {
        if(Objects.nonNull(object)){
            T value;
            try{
                value = converter.apply(object);
            }catch(Exception exception){
                throw new ConversionException("Cannot convert \""+object+"\".");
            }
            consumer.accept(value);
        }
    }
}
