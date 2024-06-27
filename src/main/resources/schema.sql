CREATE TABLE IF NOT EXISTS Todo (
  id INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  created_on TIMESTAMP NOT NULL,
  deadline_on TIMESTAMP NOT NULL,
  description VARCHAR(255) NOT NULL,
  version INT,
  PRIMARY KEY (id)
);
