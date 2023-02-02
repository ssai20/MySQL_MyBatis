package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


public interface SchoolMapper {
    @Insert("INSERT INTO school (school_name, year) VALUES " + "( #{school.name}, #{school.year} )")
    @Options(useGeneratedKeys = true, keyProperty = "school.id")
    Integer insert(@Param("school") School school);

    @Select("SELECT id, school_name as name, year FROM school WHERE id = #{id}")

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchool", fetchType = FetchType.LAZY))
    })

    School getById(int id);

    @Select("SELECT id, school_name as name, year FROM school")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchool", fetchType = FetchType.LAZY))
    })
    List<School> getAllLazy();

    @Update("UPDATE school SET id = #{school.id}, school_name = #{school.name}, " +
            "year = #{school.year} WHERE id = #{school.id}")
    void update (@Param("school") School school);

    @Delete("DELETE FROM school WHERE id = #{school.id}")
    int delete(@Param("school") School school);

    @Delete("DELETE FROM school")
    void deleteAll();
}
