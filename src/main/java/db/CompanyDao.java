package db;

import com.example.javaadvance5.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private Connection con;

    public  CompanyDao(Connection con) {
        this.con = con;
    }

    //企業一覧を受け取る
    public List<User.Company> findCompanyAll() {
        final var sql = "SELECT id, name FROM companies";

        var list = new ArrayList<User.Company>();

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var company = new User.Company(rs.getInt("id"), rs.getString("name"));
                list.add(company);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }

    //ユーザーを追加する
    public void addCompany(String coName) throws SQLException {
        final var sql = "INSERT INTO companies(name) VALUES(?)";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setString(1, coName);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }


    //ユーザー情報を編集する
    public void updateCompany(String coName, int id) throws SQLException {
        final var sql = "UPDATE companies SET name = ? WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setString(1, coName);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    //ユーザーの削除
    public void deleteCompany(int id) {
        final var sql = "DELETE FROM companies WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
