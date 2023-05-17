package com.example.javaadvance5;

import db.CompanyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CompanyController {
    @FXML
    private TableView<User.Company> table;
    @FXML
    private TableColumn<User.Company, Integer> idColumn;

    @FXML
    private TableColumn<User.Company, String> nameColumn;

    @FXML
    private TextField addName;

    @FXML
    private TextField editName;

    @FXML
    private Label Error;

    int clickId;

    //企業リスト
    ObservableList<User.Company> CompanyList;

    @FXML
    public void initialize() {

        var companyService= new CompanyService();

        var findCompany = companyService.findCompanyAll();

        CompanyList = FXCollections.observableArrayList();

        //findCompanyの中身を全てCompanyListに追加
        CompanyList.addAll(findCompany);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(CompanyList);

    }

    //追加ボタン
    @FXML
    public void addButtonClick() {

        try {
            var CompanyService= new CompanyService();

            String addName = this.addName.getText();

            //入力値チェック
            if(check(addName)) {
                return;
            }

            CompanyService.addCompany(addName);

            CompanyList = FXCollections.observableArrayList();
            var findCompany = CompanyService.findCompanyAll();
            //findCompanyの中身を全てCompanyListに追加
            CompanyList.addAll(findCompany);

            table.setItems(CompanyList);
            Error.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onListClick() {
        User.Company clickItems = table.getSelectionModel().getSelectedItem();
        clickId = clickItems.getId();
        String name = clickItems.getName();

        editName.setText(name);

    }

    @FXML
    public void deleteButtonClick() {
        String editName = this.editName.getText();

        //入力値チェック
        if(check(editName)) {
            return;
        }

        CompanyService CompanyService = new CompanyService();

        CompanyService.deleteCompany(clickId);

        CompanyList = FXCollections.observableArrayList();
        var findCompany = CompanyService.findCompanyAll();
        //findCompanyの中身を全てCompanyListに追加
        CompanyList.addAll(findCompany);

        table.setItems(CompanyList);

        Error.setText("");

    }


    @FXML
    public void editButtonClick() {
        try {
            var CompanyService= new CompanyService();

            String editName = this.editName.getText();

            //入力値チェック
            if(check(editName)) {
                return;
            }

            //更新処理
            CompanyService.updateCompany(editName, clickId);

            CompanyList = FXCollections.observableArrayList();
            var findCompany = CompanyService.findCompanyAll();
            //findCompanyの中身を全てCompanyListに追加
            CompanyList.addAll(findCompany);

            table.setItems(CompanyList);

            Error.setText("");

        } catch (NumberFormatException e) {
            Error.setText("スコアに数値を入力してください");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //値チェックする処理
    public boolean check(String coName) {
        boolean judge = false;
        //空白値チェック
        if ("".equals(coName)) {
            Error.setText("値を入力してください");
            judge = true;
        }
        return judge;
    }

}
