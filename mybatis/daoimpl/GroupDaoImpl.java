package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.GroupDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GroupDaoImpl extends DaoImplBase implements GroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);
    @Override
    public Group insert(School school, Group group) {
        LOGGER.debug("DAO insert Group {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).insert(school, group);
            } catch (RuntimeException ex){
                LOGGER.info("Can't insert group {} {}", school, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return group;
    }

    @Override
    public Group update(Group group) {
        LOGGER.debug("DAO update group to {}", group);
        try (SqlSession sqlSession = getSession()) {
            try {
                getGroupMapper(sqlSession).update(group);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update group {} {}", group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return group;
    }

    @Override
    public List<Group> getAll() {
        LOGGER.debug("DAO get all groups ");
        try (SqlSession sqlSession = getSession()){
            return getGroupMapper(sqlSession).getAll();
        } catch(RuntimeException ex) {
            LOGGER.info("Can't get all groups {}", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Group group) {
        LOGGER.debug("DAO delete group {}", group);
        try(SqlSession sqlSession = getSession()){
            try {
                getGroupMapper(sqlSession).delete(group);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete group {} {}", group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Trainee moveTraineeToGroup(Group group, Trainee trainee) {
        LOGGER.debug("DAO move Trainee {} to Group {}", trainee, group);
        try(SqlSession sqlSession = getSession()){
            try {
                getTraineeMapper(sqlSession).moveTraineeToGroup(group, trainee);
            } catch (RuntimeException ex){
                LOGGER.info("Can't move Trainee {} to Group {} {}", trainee, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee;
    }

    @Override
    public void deleteTraineeFromGroup(Trainee trainee) {
        LOGGER.debug("DAO delete Trainee {} from any Group", trainee);
        try(SqlSession sqlSession = getSession()){
            try {
                getTraineeMapper(sqlSession).deleteTraineeFromGroup(trainee);
            } catch (RuntimeException ex){
                LOGGER.info("Can't delete Trainee {} from Group {}", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void addSubjectToGroup(Group group, Subject subject) {
        LOGGER.debug("DAO add Subject {} to Group {}", subject, group);
        try (SqlSession sqlSession = getSession()){
            try {
                getSubjectMapper(sqlSession).addSubjectToGroup(group, subject);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't add Subject {} to Group {}", subject, group, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
