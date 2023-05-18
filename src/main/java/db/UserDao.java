package db;

import com.example.javaadvance5.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection con;

    public  UserDao(Connection con) {
        this.con = con;
    }

    //レコードの合計数
    public int count() {
        final var sql = "SELECT COUNT(*) FROM users";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //ユーザー一覧を50件受け取る
    public List<User> findListAny(int id) {
        final var sql = "SELECT u.id, u.name AS username, c.name AS company_name, " +
                "score FROM users AS u INNER JOIN companies AS c ON company_id = c.id " +
                "ORDER BY id LIMIT 50 OFFSET ?";

        var list = new ArrayList<User>();

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var user = new User(rs.getInt("id"), rs.getString("company_name"),
                        rs.getString("username"), rs.getInt("score"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }

    //ユーザー一覧を受け取る
    public List<User> findListAll() {
        final var sql = "SELECT u.id, u.name AS username, c.name AS company_name," +
                " score FROM users AS u INNER JOIN companies AS c ON company_id = c.id";

        var list = new ArrayList<User>();

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var user = new User(rs.getInt("id"), rs.getString("company_name"),
                        rs.getString("username"), rs.getInt("score"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

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

    //企業IDを取得
    public int findCompanyId(String companyName) {
        final var sql = "SELECT id FROM companies WHERE name = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {     // tryの()内にPreparedStatementを書く場合は、明示的にcloseする必要はない
            stmt.setString(1, companyName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ユーザーを追加する
    public void addUser(String usName, int coId, int score) throws SQLException {
        final var sql = "INSERT INTO users(name, company_id, score) VALUES(?, ?, ?)";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setString(1, usName);
            stmt.setInt(2, coId);
            stmt.setInt(3, score);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    //ユーザー情報を編集する
    public void updateUser(String usName, int coId, int score, int id) throws SQLException {
        final var sql = "UPDATE users SET name = ?, company_id = ?, score = ? WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {

            stmt.setString(1, usName);
            stmt.setInt(2, coId);
            stmt.setInt(3, score);
            stmt.setInt(4, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    //ユーザーの削除
    public void deleteUser(int id) {
        final var sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ユーザー検索
    public List<User> findByName(String name) {
        final var sql = "SELECT u.id, u.name AS username, c.name AS company_name," +
                " score FROM users AS u INNER JOIN companies AS c ON company_id = c.id" +
                " WHERE u.name LIKE ?";

        var list = new ArrayList<User>();

        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("company_name"), rs.getInt("score"));
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
