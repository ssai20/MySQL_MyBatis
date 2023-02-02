package net.thumbtack.school.database.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data

public class Subject {
//    Класс Subject - предмет в Школе программиста
//В классе должны быть следующие поля
    private int id;
//id для Subject. Для несохраненного в БД Subject это поле имеет значение 0,
// после сохранения  значение присваивается БД
    private String name;
//Название предмета.
//Сделайте для этого класса следующие конструкторы
    public Subject()
//Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится,
// но будет нужен на следующем занятии, поэтому лучше его сразу сделать.
    {}
    public Subject(int id, String name)
//Конструктор, устанавливающий значения всех полей
    {
        this.id = id;
        this.name = name;
    }
    public Subject(String name)
//Конструктор, устанавливающий значения всех полей , Полю id присваивается значение 0.
    {
        this(0, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        if (getId() != subject.getId()) return false;
        return getName() != null ? getName().equals(subject.getName()) : subject.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
    //Добавьте геттеры, сеттеры и методы equals и hashCode
}
