package db;

import com.example.javaadvance5.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    private CompanyDao dao;

    private Connection con;

    public CompanyService() {
        this.con = DbUtil.getConnection();
        this.dao = new CompanyDao(con);
    }

    //企業一覧を受け取る
    public List<User.Company> findCompanyAll() {
        var companyDao = dao;

        var company = companyDao.findCompanyAll();

        return company;
    }

    //企業の追加
    public void addCompany(String coName) throws SQLException {
        var companyDao = dao;

        companyDao.addCompany(coName);

    }


    //企業の編集
    public void updateCompany(String coName, int id) throws SQLException {
        var companyDao = dao;

        companyDao.updateCompany(coName, id);
    }


    //ユーザーの削除
    public void deleteCompany(int id) {
        var companyDao = dao;

        companyDao.deleteCompany(id);
    }

}
