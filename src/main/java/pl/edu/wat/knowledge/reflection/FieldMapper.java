package pl.edu.wat.knowledge.reflection;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import pl.edu.wat.knowledge.exception.ModifyClassException;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FieldMapper {
    TypeDescription target;
    TypeDescription source;
    Integer sourceArgIndex;
    Integer targetArgIndex;

    public static Implementation.Composable getMappingImpl(TypeDescription target,
                                                TypeDescription source,
                                                Integer sourceArgIndex,
                                                Integer targetArgIndex,
                                                List<String> fields) {
        var fieldMapper = new FieldMapper(target,source,sourceArgIndex,targetArgIndex);

        var list = fields.stream()
                .map(fieldMapper::getImplForField)
                .toList();
        return concat(list);
    }

    private static Implementation.Composable concat(List<MethodCall> list) {
        var iterator = list.iterator();
        Implementation.Composable result = null;
        if(iterator.hasNext()){
            result = iterator.next();
            while(iterator.hasNext()){
                result = result.andThen(iterator.next());
            }
        }
        return result;
    }


    private MethodCall getImplForField(String fieldName) {
        return MethodCall.invoke(setter(fieldName))
                .onArgument(targetArgIndex)
                .withMethodCall(
                        MethodCall.invoke(getter(fieldName)).onArgument(sourceArgIndex));
    }

    private MethodDescription.InDefinedShape getter(String fieldName) {
        return source.getDeclaredMethods()
                .filter(ElementMatchers.isGetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new ModifyClassException("Setter not found for %s in %s".formatted(fieldName, source.getCanonicalName())));
    }

    private MethodDescription.InDefinedShape setter(String fieldName) {
        return target.getDeclaredMethods()
                .filter(ElementMatchers.isSetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new ModifyClassException("Setter not found for %s in %s".formatted(fieldName, target.getCanonicalName())));
    }
}
