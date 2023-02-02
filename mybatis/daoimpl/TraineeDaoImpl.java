package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.TraineeDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TraineeDaoImpl extends DaoImplBase implements TraineeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeDaoImpl.class);
    @Override
    public Trainee insert(Group group, Trainee trainee) {
        LOGGER.debug("DAO insert trainee {}", trainee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).insert (group, trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert trainee {} {}", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee;
    }

    @Override
    public Trainee getById(int id) {
        LOGGER.debug("DAO get trainee by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getTraineeMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Trainee {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Trainee> getAll() {
        LOGGER.debug("DAO get all trainees ");
        try (SqlSession sqlSession = getSession()){
            return getTraineeMapper(sqlSession).getAll();
        } catch(RuntimeException ex) {
            LOGGER.info("Can't get all trainees {}", ex);
            throw ex;
        }
    }

    @Override
    public Trainee update(Trainee trainee) {
        LOGGER.debug("DAO update trainee to {}", trainee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).update(trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update trainee {} {}", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return trainee;
    }

    @Override
    public List<Trainee> getAllWithParams(String firstName, String lastName, Integer rating) {
        LOGGER.debug("DAO get all trainees with parameters {} {} {}", firstName, lastName, rating );
        try (SqlSession sqlSession = getSession()){
                return getTraineeMapper(sqlSession).getAllWithParams(firstName, lastName, rating);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't get all trainees {}", ex);
                throw ex;
            }
    }

    @Override
    public void batchInsert(List<Trainee> trainees) {
        LOGGER.debug("DAO batch insert {}", trainees);
        try (SqlSession sqlSession = getSession()) {
            try {
                getTraineeMapper(sqlSession).batchInsert(trainees);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't batch insert Trainees {} {} ", trainees, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }


    @Override
    public void delete(Trainee trainee) {
        LOGGER.debug("DAO delete trainee {}", trainee);
        try(SqlSession sqlSession = getSession()){
            try {
                getTraineeMapper(sqlSession).delete(trainee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete trainee {} {}", trainee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all trainees");
        try(SqlSession sqlSession = getSession()){
            try {
                getTraineeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete trainees {} ", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
