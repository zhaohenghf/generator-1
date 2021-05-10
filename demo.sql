CREATE TABLE demo
(
    id          bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    phone       varchar(64)         NOT NULL DEFAULT '' COMMENT '区号+手机号（非+86时加区号如+852-656***）',
    username    varchar(255)        NOT NULL DEFAULT '' COMMENT '姓名',
    nickname    varchar(255)        NOT NULL DEFAULT '' COMMENT '昵称',
    gender      int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '性别，0-未知、1-男性、2-女性、3-疑似男性、4-疑似女性',
    birthday    date                         DEFAULT NULL COMMENT '出生日，年-月-日，如：2006-01-02',
    avatar_url  varchar(512)        NOT NULL DEFAULT '' COMMENT '头像url',
    email       varchar(255)        NOT NULL DEFAULT '' COMMENT '邮箱',
    os_language varchar(64)         NOT NULL DEFAULT '' COMMENT '语言，如：zh_CN',
    country     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '国家编码',
    province    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '省编码',
    city        int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '市编码',
    district    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '区编码',
    active_sw   int(11) unsigned    NOT NULL DEFAULT '1' COMMENT '有效？1-有效、0-无效',
    remark      varchar(255)        NOT NULL DEFAULT '' COMMENT '备注，公开',
    note        varchar(255)        NOT NULL DEFAULT '' COMMENT '内部备注，不公开',
    created_at  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted_id  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '删除？id-是、0-否',
    deleted_at  datetime                     DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_phone (phone, deleted_id),
    KEY idx_phone_id (username, deleted_id),
    KEY idx_created_at (deleted_id, created_at)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户';
