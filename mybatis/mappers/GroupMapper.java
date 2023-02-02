package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;

public interface GroupMapper {
    @Insert("INSERT INTO ‘group‘ (schoolid, group_name, room) VALUES " +
            "( #{school.id}, #{group.name}, #{group.room} )")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    Integer insert(@Param("school") School school, @Param("group") Group group);

    @Update("UPDATE ‘group‘ SET group_id = #{group.id}, group_name = #{group.name}, " +
            "room = #{group.room} WHERE group_id = #{group.id}")
    void update (@Param("group") Group group);

    @Select("SELECT group_id as id, group_name as name, room FROM ‘group‘")
    List<Group> getAll();

    @Select("SELECT group_id as id, group_name as name, room FROM ‘group‘ WHERE schoolid = #{school.id}")

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))

    })
    List<Group> getBySchool(@Param("school")School school);

    @Select("SELECT group_id as id, group_name as name, room from ‘group‘ WHERE group_id IN (SELECT groupid FROM subject_group WHERE subjectid = #{subject.id})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))})

    List<Group> getBySubject(Subject subject);

    @Delete("DELETE FROM ‘group‘ WHERE id = #{group.id}")
    int delete(@Param("group") Group group);

}
