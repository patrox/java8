package pl.najda.upc.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pl.najda.upc.java8.Developer.Language;

/**
 *
 * @author Patryk
 */
class DeveloperCollector<T>
        implements Collector<Developer, StringBuilder, String> {
    
    @Override
    public Supplier<StringBuilder> supplier()
        { return () -> new StringBuilder(); }
 
    @Override
    public BiConsumer<StringBuilder, Developer> accumulator()
        { return (builder, t) -> builder.append(t).append(" <> "); }
 
    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (left, right) -> {
            left.append(right);
            return left;
        };
    }
 
    @Override
    public Function<StringBuilder, String> finisher() {
        return (builder) -> {
            if (builder.length() > 4) {
                return builder.substring(0, builder.length() - 4);
            } else {
                return builder.toString();
            }
        };
    }
 
    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}

public class Main {
    
    public static void main(String[] args) {
        List<Developer> devs = new ArrayList();
        
        String s2 = devs
                .stream()
                .collect(new DeveloperCollector<>());
        
        System.out.println(s2);
        
        devs.add(new Developer("Patryk", "Najda", 33, Developer.Language.JAVA, Developer.Language.PYTHON, Developer.Language.SCALA));
        devs.add(new Developer("Monika", "Najda", 30, Developer.Language.JAVA_SCRIPT));
        devs.add(new Developer("Krzysztof", "Kubok", 33, Developer.Language.JAVA_SCRIPT));
        devs.add(new Developer("Jan", "Kowalski", 22, Developer.Language.JAVA));
        devs.add(new Developer("Łukasz", "Klich", 22, Developer.Language.JAVA, Developer.Language.SCALA));

//        devs.forEach((Developer d) -> System.out.println(d));
//        
//        devs.removeIf((Developer d) -> !d.getProgrammingLanguages().contains(Developer.Language.JAVA));
//        
//        devs.forEach((Developer d) -> System.out.println(d));
        
        String s = devs
                .stream()
                .collect(new DeveloperCollector<>());
        
        System.out.println(s);
        
        List<Developer> trueDevs = devs
                .stream()
                .filter((Developer d) -> d.getProgrammingLanguages().contains(Language.JAVA))
                .collect(Collectors.toList());
        
        //System.out.println(trueDevs);
        
        Map<Integer, List<Developer>> devsByAge;
        
        //devs.
        devsByAge = devs
                .stream()
                .collect(Collectors.groupingBy(Developer::getAge));
        
        //System.out.println(devsByAge);
        
        String scalaDevs = devs.stream()
                           .filter(d -> d.getProgrammingLanguages().contains(Language.SCALA))
                           .map(Object::toString)
                           .collect(Collectors.joining(" oraz ", "W mojej firmie ", " są cool!"));
        
        //System.out.println(scalaDevs);
        
        //X <> Y <> Z
        
        // http://www.nurkiewicz.com/2014/07/introduction-to-writing-custom.html
        
        List<Integer> nums =
                Arrays.asList(1,2,3,4);
        
        List<Integer> numsOfNums = nums
                .stream()
                .collect(Collectors.toList());

        List<Object> langs = devs
            .stream()
//            .map(d -> d.getProgrammingLanguages())
            .flatMap(d -> d.getProgrammingLanguages().stream())
            .distinct()
            .collect(Collectors.toList());
        
        Developer d = devs
                .stream()
                .reduce((d1,d2) -> d1.getAge() < d2.getAge() ? d1 : d2).get();
        
        // suma wieku wszystkich devów
        
        // silnia 50k
        
        // sekwencyjnie, strumienie, strumienie równolegle
        
        
        //System.out.println(d);
        
        //System.out.println(langs);

//                .map(e -> IntStream.rangeClosed(e-1, e+1).collect(Collectors.toList))
        
        //System.out.println(nums);
        
        
    }    
}