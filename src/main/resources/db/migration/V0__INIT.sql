CREATE TABLE conference
(
  idx int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  create_at timestamp DEFAULT current_timestamp() NOT NULL,
  name varchar(255),
  net_id varchar(255)
);
CREATE UNIQUE INDEX conference__index_name ON conference (name);
CREATE TABLE sign
(
    idx int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    conf_id int,
    depart varchar(255),
    device_id varchar(255),
    card_id varchar(255),
    card_type varchar(2),
    check_in timestamp DEFAULT current_timestamp() NOT NULL,
    check_out timestamp
);
