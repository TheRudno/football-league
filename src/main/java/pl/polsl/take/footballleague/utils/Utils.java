package pl.polsl.take.footballleague.utils;

import java.util.Objects;
import java.util.function.Consumer;

public class Utils {
    public static <T> void consumeIfNonNull(Consumer<T> consumer,T object){
        if(Objects.nonNull(object)){
            consumer.accept(object);
        }
    }
}
