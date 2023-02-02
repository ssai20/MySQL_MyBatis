package net.thumbtack.school.database.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data

public class Group {
//    Класс Group - группа Школы программиста
//В классе должны быть следующие поля
    private int id;
//id для Group. Для несохраненной в БД Group это поле имеет значение 0, после сохранения
// значение присваивается БД
    private String name;
//Название группы
    private String room;
//Номер аудитории
    private List<Trainee> trainees;
//Список учащихся
    private List<Subject> subjects;
//Список предметов

//Класс должен содержать следующие конструкторы
    public Group()
//Конструктор без параметров с пустым телом. На этом занятии он нам не понадобится,
// но будет нужен на следующем занятии, поэтому лучше его сразу сделать.
    {}

    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects)
//Конструктор, устанавливающий значения всех полей
    {
        this.id = id;
        this.name = name;
        this.room = room;
        this.trainees = trainees;
        this.subjects = subjects;
    }

    public Group(int id, String name, String room)
//Конструктор, устанавливающий значения всех полей. Полям - спискам присваивается пустой список (не null!)
    {
        this(id, name, room, new ArrayList<>(), new ArrayList<>());
    }

    public Group(String name, String room)
//Конструктор, устанавливающий значения всех полей. Полю id присваивается значение 0,
// полям - спискам - пустые списки (не null!)
    {
        this(0, name, room);
    }

//Добавьте геттеры, сеттеры, методы equals и hashCode и следующие методы
//
    public void addTrainee(Trainee trainee)
//Добавляет Trainee в Group
    {
        trainees.add(trainee);
    }
    public void removeTrainee(Trainee trainee)
//Удаляет Trainee из Group
    {
        trainees.remove(trainee);
    }
    public void addSubject(Subject subject)
//Добавляет Subject в Group
    {
        subjects.add(subject);
    }
    public void removeSubject(Subject subject)
//Удаляет Subject из Group
    {
        subjects.remove(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getId() != group.getId()) return false;
        if (getName() != null ? !getName().equals(group.getName()) : group.getName() != null) return false;
        if (getRoom() != null ? !getRoom().equals(group.getRoom()) : group.getRoom() != null) return false;
        if (getTrainees() != null ? !getTrainees().equals(group.getTrainees()) : group.getTrainees() != null)
            return false;
        return getSubjects() != null ? getSubjects().equals(group.getSubjects()) : group.getSubjects() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        result = 31 * result + (getTrainees() != null ? getTrainees().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
        return result;
    }
}
