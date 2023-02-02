package net.thumbtack.school.database.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class School {
//    Класс School - Школа программиста
    private int id;
//id для School. Для несохраненной в БД School это поле имеет значение 0,
// после сохранения  значение присваивается БД
    private String name;
//Название школы
    private int year;
//Год обучения
    private List<Group> groups;
//Список групп
//Класс должен содержать следующие конструкторы
    public School()
//Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится,
// но будет нужен на следующем занятии, поэтому лучше его сразу сделать.
    {}
    public School(int id, String name, int year, List<Group> groups)
//Конструктор, устанавливающий значения всех полей
    {
        this.id = id;
        this.name = name;
        this.year = year;
        this.groups = groups;
    }
    public School(int id, String name, int year)
//Конструктор, устанавливающий значения всех полей. Полю - списку присваивается пустой список (не null!)
    {
        this(id, name, year, new ArrayList<>());
    }
    public School(String name, int year)
//Конструктор, устанавливающий значения всех полей. Полю id присваивается значение 0,
// полю - списку - пустой список (не null!)
    {
        this(0, name, year);
    }
//Добавьте геттеры, сеттеры, методы equals и hashCode и следующие методы
    public void addGroup(Group group)
//Добавляет Group в School
    {
        groups.add(group);
    }
    public void removeGroup(Group group)
//Удаляет Group из School
    {
        groups.remove(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;

        School school = (School) o;

        if (getId() != school.getId()) return false;
        if (getYear() != school.getYear()) return false;
        if (getName() != null ? !getName().equals(school.getName()) : school.getName() != null) return false;
        return getGroups() != null ? getGroups().equals(school.getGroups()) : school.getGroups() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + (getGroups() != null ? getGroups().hashCode() : 0);
        return result;
    }
}
