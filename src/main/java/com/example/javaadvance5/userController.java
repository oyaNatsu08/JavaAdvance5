package com.example.javaadvance5;

import db.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class userController {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> companyColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, Integer> scoreColumn;

    @FXML
    private ComboBox<String> addCompany;
    @FXML
    private TextField addName;
    @FXML
    private TextField addScore;

    @FXML
    private ComboBox<String> editCompany;
    @FXML
    private TextField editName;
    @FXML
    private TextField editScore;

    @FXML
    private Label Error;

    @FXML
    private TextField search;

    //クリックしたレコードのID
    int clickId;

    //レコードのカレントID
    int currentId = 1;

    //テーブルのレコード数
    int count;

    //ユーザーリスト
    ObservableList<User> userList;

    @FXML
    public void initialize() {

        var userService= new UserService();

        //var findUser = userService.findListAll();
        var findUser = userService.findListAny(currentId);

        count = userService.count();

        userList = FXCollections.observableArrayList();

        //findUserの中身を全てuserListに追加
        userList.addAll(findUser);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.setItems(userList);


        //追加処理の初期設定
        ObservableList<String> items = FXCollections.observableArrayList();
        var findCompany = userService.findCompanyAll();
        for (User.Company company : findCompany) {
            items.add(company.getName());
        }
        addCompany.setItems(items);
        editCompany.setItems(items);

    }

    //追加ボタン
    @FXML
    public void addButtonClick() {

        try {
            var userService = new UserService();

            String companyName = this.addCompany.getValue();
            //companyNameから企業IDを割り出す
            int companyId = userService.findCompanyId(companyName);

            String addName = this.addName.getText();
            int addScore = Integer.parseInt(this.addScore.getText());

            //入力値チェック
            if(check(companyName, addName, addScore)) {
                return;
            }

            userService.addUser(addName, companyId, addScore);

            userList = FXCollections.observableArrayList();
            //var findUser = userService.findListAll();
            var findUser = userService.findListAny(currentId);
            //findUserの中身を全てuserListに追加
            userList.addAll(findUser);
            //レコード件数をプラス１
            count++;

            table.setItems(userList);
            Error.setText("");

        } catch (NumberFormatException e) {         //数値チェック
            Error.setText("スコアに数値を入力してください");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onListClick() {
        User clickItems = table.getSelectionModel().getSelectedItem();
        clickId = clickItems.id;
        String company = clickItems.company;
        String name = clickItems.name;
        Integer score = clickItems.score;

        editCompany.setValue(company);
        editName.setText(name);
        editScore.setText(score.toString());

    }

    @FXML
    public void deleteButtonClick() {
        try {
            String companyName = this.editCompany.getValue();
            String editName = this.editName.getText();
            int editScore = Integer.parseInt(this.editScore.getText());

            //入力値チェック
            if(check(companyName, editName, editScore)) {
                return;
            }

            UserService userService = new UserService();

            userService.deleteUser(clickId);

            userList = FXCollections.observableArrayList();
            //var findUser = userService.findListAll();
            var findUser = userService.findListAny(currentId);
            //findUserの中身を全てuserListに追加
            userList.addAll(findUser);
            //レコード件数をマイナス１
            count -= 1;

            table.setItems(userList);

            Error.setText("");

        } catch (NumberFormatException e) {
            Error.setText("スコアに数値を入力してください");
        }
    }

    @FXML
    public void editButtonClick() {
        try {
            var userService= new UserService();

            String companyName = this.editCompany.getValue();
            //companyNameから企業IDを割り出す
            int companyId = userService.findCompanyId(companyName);
            String editName = this.editName.getText();
            int editScore = Integer.parseInt(this.editScore.getText());

            //入力値チェック
            if(check(companyName, editName, editScore)) {
                return;
            }

            //更新処理
            userService.updateUser(editName, companyId, editScore, clickId);

            userList = FXCollections.observableArrayList();
            //var findUser = userService.findListAll();
            var findUser = userService.findListAny(currentId);
            //findUserの中身を全てuserListに追加
            userList.addAll(findUser);

            table.setItems(userList);

            Error.setText("");

        } catch (NumberFormatException e) {
            Error.setText("スコアに数値を入力してください");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void searchClick() {
        var userService= new UserService();

        //キーワード
        String searchText = this.search.getText();

        //検索処理
        userList = FXCollections.observableArrayList();
        var findUser = userService.findByName(searchText);
        //findUserの中身を全てuserListに追加
        userList.addAll(findUser);

        table.setItems(userList);
    }

    //値チェックする処理
    public boolean check(String coName, String usName, int score) {
        boolean judge = false;
        //空白値チェック
        if (coName == null || "".equals(usName)) {
            Error.setText("値を入力してください");
            judge = true;
        } else if (score < 0 || score > 100) {          //スコア値チェック
            Error.setText("正しいスコアを入力してください");
            judge = true;
        }
        return judge;
    }

    @FXML
    public void companyList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(userApplication.class.getResource("company-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        Stage stage = new Stage();
        stage.setTitle("ユーザーアプリケーション");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backRecord() {
        var userService= new UserService();

        currentId -= 50;

        //currentIdを0以下にならないために
        if (currentId < 1) {
            currentId = 1;
        }
        var findUser = userService.findListAny(currentId);

        userList = FXCollections.observableArrayList();
        //findUserの中身を全てuserListに追加
        userList.addAll(findUser);
        table.setItems(userList);

    }

    @FXML
    public void nextRecord() {
        var userService= new UserService();

        currentId += 50;

        if (currentId > count) {
            currentId -= 50;
            return;
        }

        var findUser = userService.findListAny(currentId);

        userList = FXCollections.observableArrayList();
        //findUserの中身を全てuserListに追加
        userList.addAll(findUser);
        table.setItems(userList);

    }

}
