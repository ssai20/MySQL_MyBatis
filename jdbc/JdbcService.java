package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException
//Добавляет Trainee в базу данных.
    {
        String insertQuery = "insert into trainee values(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setNull(2, java.sql.Types.INTEGER);
            stmt.setString(3, trainee.getFirstName());
            stmt.setString(4, trainee.getLastName());
            stmt.setInt(5, trainee.getRating());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    trainee.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void updateTrainee(Trainee trainee) throws SQLException
//Изменяет ранее записанный Trainee в базе данных. В случае ошибки выбрасывает SQLException.
    {
        String updateQuery = "update trainee set firstname = ?, lastname = ?, rating = ? where id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(updateQuery)){
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.setInt(4, trainee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException
//Получает Trainee  из базы данных по его ID, используя метод получения “по именам полей”.
// Если Trainee с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from trainee where id = ?";
        Trainee trainee = new Trainee();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery()){
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt("id");
                    String firstName = generatedKeys.getString("firstname");
                    String lastName = generatedKeys.getString("lastname");
                    int rating = generatedKeys.getInt("rating");
                    trainee.setId(id);
                    trainee.setFirstName(firstName);
                    trainee.setLastName(lastName);
                    trainee.setRating(rating);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (trainee.getId() == 0) trainee = null;
        return trainee;
    }
    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException
//Получает Trainee  из базы данных по его ID, используя метод получения “по номерам полей”.
// Если Trainee с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from trainee where id = ?";
        Trainee trainee = new Trainee();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)){
            stmt.setInt(1, traineeId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery()){
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    String firstName = generatedKeys.getString(3);
                    String lastName = generatedKeys.getString(4);
                    int rating = generatedKeys.getInt(5);
                    trainee.setId(id);
                    trainee.setFirstName(firstName);
                    trainee.setLastName(lastName);
                    trainee.setRating(rating);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (trainee.getId() == 0) trainee = null;
        return trainee;
    }
    public static List<Trainee> getTraineesUsingColNames() throws SQLException
//Получает все Trainee  из базы данных, используя метод получения “по именам полей”.
// Если ни одного Trainee в БД нет,  возвращает пустой список.
    {
        List<Trainee> traineeList = new ArrayList<>();
        String selectQuery = "select * from trainee";

        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery); ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int rating = rs.getInt("rating");
                Trainee trainee = new Trainee(id, firstName, lastName, rating);
                traineeList.add(trainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traineeList;
    }
    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException
//Получает все Trainee  из базы данных, используя метод получения “по номерам полей”.
// Если ни одного Trainee в БД нет,  возвращает пустой список.
    {
        List<Trainee> traineeList = new ArrayList<>();
        String selectQuery = "select * from trainee";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery); ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                int rating = rs.getInt(5);
                Trainee trainee = new Trainee(id, firstName, lastName, rating);
                traineeList.add(trainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traineeList;
    }
    public static void deleteTrainee(Trainee trainee) throws SQLException
//Удаляет Trainee из базы данных.
    {
        String deleteQuery = "delete from trainee where id = ?";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.setInt(1, trainee.getId());
            stmt.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTrainees() throws SQLException
//Удаляет все Trainee из базы данных
    {
        String deleteQuery = "delete from trainee";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertSubject(Subject subject) throws SQLException
//Добавляет Subject в базу данных
    {
        String insertQuery = "insert into subject values(?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, subject.getName());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    subject.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException
//Получает Subject  из базы данных по его ID, используя метод получения “по именам полей”.
// Если Subject с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from subject where id = ?";
        Subject subject = new Subject();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt("id");
                    String name = generatedKeys.getString("name");
                    subject.setId(id);
                    subject.setName(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException
//Получает Subject  из базы данных по его ID, используя метод получения “по номерам полей”.
// Если Subject с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from subject where id = ?";
        Subject subject = new Subject();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    String name = generatedKeys.getString(2);
                    subject.setId(id);
                    subject.setName(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
    public static void deleteSubjects() throws SQLException
//Удаляет все Subject из базы данных.
    {
        String deleteQuery = "delete from subject";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertSchool(School school) throws SQLException
//Добавляет School в базу данных
    {
        String insertQuery = "insert into school values(?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, school.getName());
            stmt.setInt(3, school.getYear());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    school.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException
//Получает School  из базы данных по ее ID, используя метод получения “по именам полей”.
// Если School с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from school where id = ?";
        School school = new School();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery()) {
                if (generatedKeys == null) school = null;
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt("id");
                    String name = generatedKeys.getString("school_name");
                    int year = generatedKeys.getInt("year");
                    school.setId(id);
                    school.setName(name);
                    school.setYear(year);
                    school.setGroups(new ArrayList<>());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return school;
        }
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException
//Получает School  из базы данных по ее ID, используя метод получения “по номерам полей”.
// Если School с таким ID нет, возвращает null.
    {
        String selectQuery = "select * from school where id = ?";
        School school = new School();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery()) {
                if (generatedKeys == null) school = null;
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    String name = generatedKeys.getString(2);
                    int year = generatedKeys.getInt(3);
                    school.setId(id);
                    school.setName(name);
                    school.setYear(year);
                    school.setGroups(new ArrayList<>());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return school;
        }
    }
    public static void deleteSchools() throws SQLException
//Удаляет все School из базы данных. Если список Group в School не пуст, удаляет все Group для каждой School.
    {
        String deleteQuery = "delete from school";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(deleteQuery)) {
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertGroup(School school, Group group) throws SQLException
//Добавляет Group в базу данных, устанавливая ее принадлежность к школе School.
    {
        String insertQuery = "insert into ‘group‘ values(?, ?, ?, ?)";
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setInt(2, school.getId());
            stmt.setString(3, group.getName());
            stmt.setString(4, group.getRoom());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int id = generatedKeys.getInt(1);
                    group.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException
//Получает School по ее ID вместе со всеми ее Group из базы данных. Если School с таким ID нет, возвращает null.
// Метод получения (по именам или номерам полей) - на Ваше усмотрение.
    {
        String selectQuery = "select * from school INNER JOIN ‘group‘ ON schoolid = ?";
        School school = new School();
        List<Group> groupList = new ArrayList();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            try (ResultSet generatedKeys = stmt.executeQuery()) {
                if (generatedKeys == null) school = null;
                while (generatedKeys.next()) {
                    int schoolId = generatedKeys.getInt(1);
                    String name = generatedKeys.getString(2);
                    int year = generatedKeys.getInt(3);
                    school.setId(schoolId);
                    school.setName(name);
                    school.setYear(year);

                    String groupName = generatedKeys.getString("group_name");
                    String room = generatedKeys.getString("room");
                    int groupId = generatedKeys.getInt("group_id");
                    Group group = new Group(groupId, groupName, room);

                    groupList.add(group);
                }
                school.setGroups(groupList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return school;
    }
    public static List<School> getSchoolsWithGroups() throws SQLException
//Получает список всех School вместе со всеми их Group из базы данных. Если ни одной  School в БД нет,
// возвращает пустой список. Метод получения (по именам или номерам полей) - на Ваше усмотрение.
    {
        String selectQuery = "select * from school INNER JOIN ‘group‘ ON schoolid = school.id";
        List<School> schoolList = new ArrayList();
        MultiValuedMap<School, Group> schoolMap = new HashSetValuedHashMap<>();
        try (PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery); ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                int schoolId = rs.getInt("id");
                String schoolName = rs.getString("school_name");
                int schoolYear = rs.getInt("year");
                School school = new School(schoolId, schoolName, schoolYear);

                int groupId = rs.getInt("group_id");
                String groupName = rs.getString("group_name");
                String room = rs.getString("room");
                Group group = new Group(groupId, groupName, room);

                schoolMap.put(school, group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (School s : schoolMap.keySet())
        {
            List<Group> groups = new ArrayList<>(schoolMap.get(s));
            groups.sort(Comparator.comparingInt(Group::getId));
            s.setGroups(groups);
            schoolList.add(s);
        }
        schoolList.sort(Comparator.comparingInt(School::getId));
        return schoolList;
    }
//Проверьте работу тестов в консольном окне, запишите все классы, файл ttschool.sql и  изменившийся файл .
// .gitlab-ci.yml на сервер (не забудьте изменить текст сообщения в git commit ) и убедитесь, что на сервере
// все тесты также проходят успешно (см. Занятие 1, п.10-14)
}
