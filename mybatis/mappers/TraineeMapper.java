package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TraineeMapper {
    @Insert("INSERT INTO trainee (groupid, firstname, lastname, rating) VALUES " +
            "( #{group.id}, #{trainee.firstName}, #{trainee.lastName}, #{trainee.rating} )")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    Integer insert (@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Select("SELECT * FROM trainee WHERE groupid = #{group.id}")
    List<Trainee> getByGroup(Group group);

    @Select("SELECT id, firstname, lastname, rating FROM trainee WHERE id = #{id}")
    Trainee getById(int id);

    @Update("UPDATE trainee SET id = #{trainee.id}, firstname = #{trainee.firstName}, " +
            "lastname = #{trainee.lastName}, rating = #{trainee.rating} WHERE id = #{trainee.id}")
    void update (@Param("trainee") Trainee trainee);

    @Select({"<script>",
                "SELECT id, firstname as firstName, lastname as lastName, rating FROM trainee"+
                "<where>" ,
                    "<if test='firstName != null'> firstname = #{firstName}",
                    "</if>",
                    "<if test='lastName != null'> AND lastname LIKE #{lastName}",
                    "</if>",
                    "<if test='rating != null'> AND rating = #{rating}",
                    "</if>",
                "</where>" ,
            "</script>"})
    List<Trainee> getAllWithParams(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("rating") Integer rating);

    @Select("SELECT id, firstname, lastname, rating FROM trainee")
    List<Trainee> getAll();

    @Insert({"<script>",
            "INSERT INTO trainee (firstname, lastname, rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.rating})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "list.id")
    void batchInsert(@Param("list") List<Trainee> trainees);

    @Delete("DELETE FROM trainee WHERE id = #{trainee.id}")
    int delete(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();

    @Update("UPDATE trainee SET groupid = #{group.id} WHERE id = #{trainee.id}")
    void moveTraineeToGroup (@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET groupid = null WHERE id = #{trainee.id}")
    void deleteTraineeFromGroup (@Param("trainee") Trainee trainee);
}
