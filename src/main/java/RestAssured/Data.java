package RestAssured;

import java.util.Objects;

public class Data {
    String name;
    String salary;
    String age;
    Integer id;

    public Data(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", age='" + age + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(name, data.name) && Objects.equals(salary, data.salary) && Objects.equals(age, data.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, age);
    }
}
