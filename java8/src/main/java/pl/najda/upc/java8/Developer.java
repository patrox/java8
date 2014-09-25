package pl.najda.upc.java8;

/**
 *
 * @author Patryk
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects; 

public class Developer {
 
        private String firstName;
        private String lastName;
        private Integer age;
        private List<Language> programmingLanguages;
       
        public enum Language {
                CLOJURE, GROOVY, JAVA, JAVA_SCRIPT, PYTHON, RUBY, SCALA
        }
       
        public Developer(String firstName, String lastName, Integer age, Language ... languages) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.age = age;
                this.programmingLanguages = Arrays.asList(languages);
        }
 
        public String getFirstName() {
                return firstName;
        }
 
        public void setFirstName(String name) {
                this.firstName = name;
        }
 
        public String getLastName() {
                return lastName;
        }
 
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
 
        public List<Language> getProgrammingLanguages() {
                return programmingLanguages;
        }
 
        public void setProgrammingLanguages(List<Language> programmingLanguages) {
                this.programmingLanguages = programmingLanguages;
        }
 
        public Integer getAge() {
                return age;
        }
 
        public void setAge(Integer age) {
                this.age = age;
        }
 
        @Override
        public int hashCode() {
                return Objects.hash(firstName, lastName, age, programmingLanguages);
        }
 
        @Override
        public boolean equals(Object obj) {
                if (this == obj) {
                        return true;
                }
 
                if (obj instanceof Developer) {
                        Developer other = (Developer) obj;
                        return Objects.equals(firstName, other.firstName)
                                        && Objects.equals(lastName, other.lastName)
                                        && Objects.equals(age,  other.age)
                                        && Objects.equals(programmingLanguages, other.programmingLanguages);
                }
 
                return false;
        }
 
        @Override
        public String toString() {
                return firstName + " " + lastName + " (" + age + ")";
        }

	public static class DevelopersRepository {
		public static List<Developer> getSampleList() {
			return new ArrayList<Developer>(Arrays.asList(
		            new Developer("John", "Scott", 20, Language.JAVA, Language.SCALA),
		            new Developer("Betty", "Scott", 32, Language.RUBY, Language.JAVA_SCRIPT),
		            new Developer("Robert", "Carter", 32, Language.GROOVY, Language.JAVA),
		            new Developer("Tom", "Wood", 41, Language.JAVA, Language.JAVA_SCRIPT),
		            new Developer("Bill", "Parker", 27, Language.PYTHON, Language.RUBY),
		            new Developer("Bill", "Parker", 24, Language.CLOJURE, Language.SCALA),
		            new Developer("Michael", "Beer", 36, Language.JAVA)));
		}
	} 
}