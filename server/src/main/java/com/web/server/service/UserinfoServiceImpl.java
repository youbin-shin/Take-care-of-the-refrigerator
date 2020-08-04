package com.web.server.service;

import com.web.server.dto.User;
import com.web.server.repo.UserinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;


@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    UserinfoDao uDao;

    @Override
    public User login(String email, String password) {
        return uDao.login(email, password);
    }
    
    /**
     * 닉네임 중복 여부 체크
     * 
     * @param nickname
     * @return
     * @throws SQLException
     */
    @Override
    public boolean checkAccount(String nickname) throws SQLException {
        int check = uDao.checkAccount(nickname);
        return check == 0 ? true : false;
    }

    /**
     * 이메일, 닉네임의 중복 여부를 체크한다.
     *
     * @param email
     * @param nickname
     * @return true or false (true : 중복 없음, false : 중복 있음)
     */
    @Override
    public boolean checkAccount(String email, String nickname) throws SQLException {
        int check = uDao.checkAccount(email, nickname);
        return check == 0 ? true : false;
    }

    /**
     * 사용자 정보 수정
     *
     * @param user
     * @return
     */
    @Override
    public int modify(User user) {
        int result = 0;
        result = uDao.updateUser(user);
        return result;
    }

    @Override
    public User searchByEmail(String email) {
        User resultUser = uDao.selectByIsEmail(email);
        return resultUser;
    }

    /**
     * 사용자 삭제
     * 
     * @param email
     * @return
     */
    @Override
    public int deleteUser(String email) {
        return uDao.deleteUser(email);
    }

    //////////////////////////// 사용하지 않는 서비스 //////////////////////

    @Override
    public int insertUser(User user) {
        return uDao.insertUser(user);
    }

    @Override
    public User selectByIsId(String id) {
        return uDao.selectByIsId(id);
    }

    @Override
    public Collection<User> search(String by, String keyword) {
        return uDao.search(by, keyword);
    }

    @Override
    public int updateUser(User user) {
        return uDao.updateUser(user);
    }

}
