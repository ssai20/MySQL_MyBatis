package net.thumbtack.school.database.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data

public class Trainee {
    //В конструкторах и сеттерах классов для этого занятия не надо производить никакие проверки на null и т.п.,
//поэтому исключения в них выбрасываться не должны.
//Класс Trainee - студент Школы программиста
//В классе должны быть следующие поля
    private int id;
    //id для Trainee. Для несохраненного в БД Trainee это поле имеет значение 0, после сохранения
//значение присваивается БД
    private String firstName;
    //Имя студента
    private String lastName;
    //Фамилия студента
    private int rating;

    //Оценка студента
//Сделайте для этого класса следующие конструкторы
    public Trainee()
//Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится,
// но будет нужен на следующем занятии, поэтому лучше его сразу сделать.
    {
    }

    public Trainee(int id, String firstName, String lastName, int rating)
//Конструктор, устанавливающий значения всех полей
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }
public Trainee(String firstName, String lastName, int rating)
//Конструктор, устанавливающий значения всех полей , Полю id присваивается значение 0.
    {
        this(0, firstName, lastName, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainee)) return false;

        Trainee trainee = (Trainee) o;

        if (getId() != trainee.getId()) return false;
        if (getRating() != trainee.getRating()) return false;
        if (getFirstName() != null ? !getFirstName().equals(trainee.getFirstName()) : trainee.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(trainee.getLastName()) : trainee.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + getRating();
        return result;
    }
}
