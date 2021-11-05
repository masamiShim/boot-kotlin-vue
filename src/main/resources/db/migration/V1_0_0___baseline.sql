CREATE TABLE IF NOT EXISTS users
(
    id         bigint AUTO_INCREMENT,
    user_id    VARCHAR(256) UNIQUE,
    login_id   VARCHAR(256) UNIQUE                                             NOT NULL,
    password   VARCHAR(256)                                                    NOT NULL,
    created_at DATETIME  DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    created_by VARCHAR(256)                                                    NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    updated_by VARCHAR(256)                                                    NOT NULL,
    deleted_at DATETIME                                                       null,
    deleted_by VARCHAR(256)                                                    null,
    PRIMARY KEY (id),
    UNIQUE users_unique_user_id (user_id),
    UNIQUE users_unique_login_id (login_id)
);
