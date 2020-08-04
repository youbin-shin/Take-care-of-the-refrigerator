package com.web.server.repo;

import com.web.server.dto.CommentDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{
    private String ns = "com.web.server.repo.CommentDao.";
    private static String NAMESPACE="mybatis.mapper.commentMapper";
    @Autowired
    SqlSessionTemplate template;

    @Override
    public List<CommentDto> selectByBoardId(int boardId) throws SQLException {
//        List<CommentDto> comments = null;
//        try {
//            comments = template.selectList(ns + "selectCommentsInBoardByBoardId", boardId);
//        } catch (Exception e) {
//            System.out.println("ERROR : " + e.getMessage());
//            throw new SQLException();
//        }
//        return comments;
        return template.selectList(ns + "selectCommentsInBoardByBoardId", boardId);
    }

    @Override
    public void createCommentByBoardId(CommentDto comment) throws SQLException{
        template.insert( NAMESPACE+ "createCommentsInBoardByBoardId", comment);
    }

    @Override
    public void updateCommentByBoardId(CommentDto commnet) throws SQLException{
        template.update(NAMESPACE+"updateCommentsInBoardByBoardId", comment );
    }

    @Override
    public void deleteCommentByBoardId(CommentDto comment) throws SQLException{
        template.delete(NAMESPACE+"deleteCommentsInBoardByBoardId", commentId);
    }
}
