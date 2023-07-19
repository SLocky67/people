import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        //кол-во несовершеннолетних
        long stream1 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних (младше 18 лет) = " + stream1);

//список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> stream2 = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("\nCписок фамилий призывников: " + stream2);

        //cписок людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин

        List<String> stream3 = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == (Sex.WOMAN) && person.getAge() < 60)
                        || (person.getSex() == (Sex.MAN) && person.getAge() < 65))
                .map(Person::getFamily) // получаем фамилии
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        System.out.println("\nСписок людей с высшим образованием : " + stream3);
    }
}