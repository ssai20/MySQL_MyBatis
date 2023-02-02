package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SubjectMapper {
    @Insert("INSERT INTO subject (name) VALUES ( #{subject.name} )")
    @Options(useGeneratedKeys = true, keyProperty = "subject.id")
    Integer insert(@Param("subject") Subject subject);

    @Select("SELECT id, name FROM subject WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySubject",
                            fetchType = FetchType.LAZY) ) })
    Subject getById(int id);

    @Select("SELECT * FROM subject WHERE id IN (SELECT subjectid FROM subject_group WHERE groupid = #{group.id})")
    List<Subject> getByGroup(@Param("group") Group group);


    @Update("UPDATE subject SET id = #{subject.id}, name = #{subject.name} WHERE id = #{subject.id}")
    void update (@Param("subject") Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{subject.id}")
    int delete(@Param("subject") Subject subject);

    @Select("SELECT id, name FROM subject")
    List<Subject> getAll();

    @Delete("DELETE FROM subject")
    void deleteAll();

    @Insert("INSERT INTO subject_group (subjectid, groupid) VALUES ( #{subject.id}, #{group.id} )")
    Integer addSubjectToGroup(@Param("group") Group group, @Param("subject") Subject subject);
}
