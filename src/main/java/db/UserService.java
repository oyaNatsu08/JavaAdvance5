package db;

import com.example.javaadvance5.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao dao;

    private Connection con;

    public UserService() {
        this.con = DbUtil.getConnection();
        this.dao = new UserDao(con);
    }

    //ユーザー一覧を受け取る
    public List<User> findListAll() {
        var userDao = dao;

        var user = userDao.findListAll();

        return user;
    }

    //企業一覧を受け取る
    public List<User.Company> findCompanyAll() {
        var userDao = dao;

        var company = userDao.findCompanyAll();

        return company;
    }

    //企業IDを割り出す
    public int findCompanyId(String companyName) {
        var userDao = dao;

        var id = userDao.findCompanyId(companyName);

        return id;
    }

    //ユーザーの追加
    public void addUser(String usName, int coId, int score) throws SQLException {
        var userDao = dao;

        userDao.addUser(usName, coId, score);

    }

    //ユーザーの編集
    public void updateUser(String usName, int coId, int score, int id) throws SQLException {
        var userDao = dao;

        userDao.updateUser(usName, coId, score, id);
    }

    //ユーザーの削除
    public void deleteUser(int id) {
        var userDao = dao;

        userDao.deleteUser(id);
    }

    //ユーザー検索
    public List<User> findByName(String name) {
        var userDao = dao;

        var user = userDao.findByName(name);

        return user;
    }
}
