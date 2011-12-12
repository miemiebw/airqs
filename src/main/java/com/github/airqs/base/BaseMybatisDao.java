/**
 * 
 */
package com.github.airqs.base;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Eric
 *
 */
@Singleton
public class BaseMybatisDao {

	protected SqlSession sqlSession;

    @Inject
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public <T> Page<T> findPage(String mappingId,String countId,Object parameter,int offset,int limit){
    	Integer total = (Integer) sqlSession.selectOne(countId, parameter);
    	List<T> list = sqlSession.selectList(mappingId, parameter, new RowBounds(offset, limit));
    	return new Page<T>(list, offset, limit, total);
    }
}
