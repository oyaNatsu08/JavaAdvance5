
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

--idの現在の最大値を取得
SELECT last_value FROM users_id_seq;

SELECT * FROM users;

DROP TABLE users;

--usersテーブルcompaniesテーブル結合
SELECT users.id, users.name, companies.name, score FROM users 
INNER JOIN companies ON company_id = companies.id;


-- テストデータを50件作成
INSERT INTO users (name, company_id, score)
VALUES
  ('User1', 1, 45),
  ('User2', 2, 90),
  ('User3', 3, 100),
  ('User4', 1, 80),
  ('User5', 2, 50),
  ('User6', 3, 75),
  ('User7', 1, 60),
  ('User8', 2, 95),
  ('User9', 3, 70),
  ('User10', 1, 85),
  ('User11', 2, 40),
  ('User12', 3, 65),
  ('User13', 1, 55),
  ('User14', 2, 75),
  ('User15', 3, 90),
  ('User16', 1, 70),
  ('User17', 2, 95),
  ('User18', 3, 80),
  ('User19', 1, 90),
  ('User20', 2, 45),
  ('User21', 3, 70),
  ('User22', 1, 100),
  ('User23', 2, 80),
  ('User24', 3, 60),
  ('User25', 1, 75),
  ('User26', 2, 95),
  ('User27', 3, 55),
  ('User28', 1, 80),
  ('User29', 2, 75),
  ('User30', 3, 90),
  ('User31', 1, 45),
  ('User32', 2, 100),
  ('User33', 3, 70),
  ('User34', 1, 85),
  ('User35', 2, 60),
  ('User36', 3, 95),
  ('User37', 1, 55),
  ('User38', 2, 90),
  ('User39', 3, 80),
  ('User40', 1, 75),
  ('User41', 2, 90),
  ('User42', 3, 45),
  ('User43', 1, 70),
  ('User44', 2, 80),
  ('User45', 3, 100),
  ('User46', 1, 65),
  ('User47', 2, 75),
  ('User48', 3, 95),
  ('User49', 1, 55),
  ('User50', 2, 90);

-- テストデータを50件作成
INSERT INTO users (name, company_id, score)
VALUES
  ('User51', 3, 90),
  ('User52', 1, 55),
  ('User53', 2, 70),
  ('User54', 3, 80),
  ('User55', 1, 75),
  ('User56', 2, 90),
  ('User57', 3, 45),
  ('User58', 1, 70),
  ('User59', 2, 80),
  ('User60', 3, 100),
  ('User61', 1, 65),
  ('User62', 2, 75),
  ('User63', 3, 95),
  ('User64', 1, 55),
  ('User65', 2, 90),
  ('User66', 3, 80),
  ('User67', 1, 75),
  ('User68', 2, 90),
  ('User69', 3, 45),
  ('User70', 1, 70),
  ('User71', 2, 80),
  ('User72', 3, 100),
  ('User73', 1, 65),
  ('User74', 2, 75),
  ('User75', 3, 95),
  ('User76', 1, 55),
  ('User77', 2, 90),
  ('User78', 3, 80),
  ('User79', 1, 75),
  ('User80', 2, 90),
  ('User81', 3, 45),
  ('User82', 1, 70),
  ('User83', 2, 80),
  ('User84', 3, 100),
  ('User85', 1, 65),
  ('User86', 2, 75),
  ('User87', 3, 95),
  ('User88', 1, 55),
  ('User89', 2, 90),
  ('User90', 3, 80),
  ('User91', 1, 75),
  ('User92', 2, 90),
  ('User93', 3, 45),
  ('User94', 1, 70),
  ('User95', 2, 80),
  ('User96', 3, 100),
  ('User97', 1, 65),
  ('User98', 2, 75),
  ('User99', 3, 95),
  ('User100', 1, 55);

