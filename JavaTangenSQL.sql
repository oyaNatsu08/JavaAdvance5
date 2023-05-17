
CREATE TABLE companies (
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

SELECT * FROM companies;

DROP TABLE companies;


CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(255),
company_id INT,
score INT); 

--テストデータ
INSERT INTO companies(name) VALUES ('RN社');
INSERT INTO companies(name) VALUES ('TC社');

--テストデータ
INSERT INTO users(name, company_id, score) VALUES ('testUser', 1, 100);
INSERT INTO users(name, company_id, score) VALUES ('AAA', 2, 80);

SELECT * FROM users;

DROP TABLE users;

--usersテーブルcompaniesテーブル結合
SELECT users.id, users.name, companies.name, score FROM users 
INNER JOIN companies ON company_id = companies.id;

