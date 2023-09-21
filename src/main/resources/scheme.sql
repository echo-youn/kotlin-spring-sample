CREATE TABLE user_entity(
    'id' bigint(20) primary key auto increment,
    'username' varchar(255)
);

CREATE TABLE game(
    'id' bigint(20) primary key auto increment,
    'game_name' varchar(255)
);

CREATE TABLE user_game_relation(
    'id' bigint(20) primary key auto increment,
    'uid' bigint(20),
    'gid' bigint(20)
);
