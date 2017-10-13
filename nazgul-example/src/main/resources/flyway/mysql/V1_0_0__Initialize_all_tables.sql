/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/17 18:24:45                           */
/*==============================================================*/



/*==============================================================*/
/* Table: als_channle_id                                        */
/*==============================================================*/
CREATE TABLE dls_channle_id
(
  app_id     CHAR(36) NOT NULL,
  chn_name   VARCHAR(255),
  package_id VARCHAR(255),
  ts_create  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (app_id)
);

ALTER TABLE dls_channle_id COMMENT '默认渠道为本应用。该表记录了同一个应用在不同的渠道的发布ID（唯一索引包名）
';

/*==============================================================*/
/* Table: dls_app_feature                                       */
/*==============================================================*/
CREATE TABLE dls_app_feature
(
  app_id       CHAR(36)      NOT NULL,
  app_ver_id   CHAR(36)      NOT NULL,
  feature_id   CHAR(36),
  feature_code VARCHAR(64),
  feature_deac VARCHAR(1024) NOT NULL,
  ts_create    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*==============================================================*/
/* Table: dls_app_version                                       */
/*==============================================================*/
CREATE TABLE dls_app_version
(
  app_id       CHAR(36)     NOT NULL,
  app_ver_id   CHAR(36)     NOT NULL,
  app_ver_name VARCHAR(255) NOT NULL,
  profiles     VARCHAR(1024),
  ts_tag       TIMESTAMP,
  ts_upload    TIMESTAMP,
  ts_publish   TIMESTAMP,
  ts_deprecate TIMESTAMP,
  ts_create    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (app_id, app_ver_id)
);

ALTER TABLE dls_app_version COMMENT '应用每建立一个发布版本，在该表创建一条发布记录。
版本管理是该服务的核心功能，主要用例都基于版本展开。';

/*==============================================================*/
/* Table: dls_application                                       */
/*==============================================================*/
CREATE TABLE dls_application
(
  app_id        CHAR(36)     NOT NULL,
  app_name      VARCHAR(64)  NOT NULL,
  app_namespace VARCHAR(255) NOT NULL,
  app_brief     VARCHAR(255),
  ts_create     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (app_id)
);

ALTER TABLE dls_application COMMENT '该服务管理的每个应用都在该表有一条记录。记录应用的基本信息。
应用按照app_namespace/app_v';

/*==============================================================*/
/* Table: dls_config                                            */
/*==============================================================*/
CREATE TABLE dls_config
(
  config_key   VARCHAR(255) NOT NULL,
  app_id       CHAR(36)     NOT NULL,
  config_value TEXT,
  ts_create    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE dls_config COMMENT '用于配置存储，每个应用独立配置。

例如：
诸葛IO的统计
七牛CDN账';

/*==============================================================*/
/* Table: dls_download_record                                   */
/*==============================================================*/
CREATE TABLE dls_download_record
(

  app_id      CHAR(36) NOT NULL,
  app_ver_id  CHAR(36) NOT NULL,
  rec_id      INT      NOT NULL AUTO_INCREMENT,
  app_ver     VARCHAR(64),
  ip          VARCHAR(64),
  ts_download TIMESTAMP,
  agent_info  VARCHAR(512),
  os_info     VARCHAR(64),
  device_info VARCHAR(64),
  PRIMARY KEY (rec_id)
);

ALTER TABLE dls_download_record COMMENT '每次下载在该表创建一个下载记录，便于后期统计。';

/*==============================================================*/
/* Table: dls_download_statistic                                */
/*==============================================================*/
CREATE TABLE dls_download_statistic
(
  app_id       CHAR(36) NOT NULL,
  app_ver_id   CHAR(36) NOT NULL,
  download_cnt INT,
  ts_create    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY AK_Identifier_1 (app_id, app_ver_id)
);

/*==============================================================*/
/* Table: dls_res_mngm                                          */
/*==============================================================*/
CREATE TABLE dls_res_mngm
(
  app_id      CHAR(36) NOT NULL,
  app_ver_id  CHAR(36) NOT NULL,
  res_mngn_id CHAR(36),
  profile     VARCHAR(64),
  os_type     VARCHAR(64),
  local_url   VARCHAR(64),
  cnd_urls    TEXT,
  ts_create   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE dls_res_mngm COMMENT '同一个应用版本，有不同的profile。默认有devel/qa/rc/product/demo
根据OS区分';


/*===== Example =====*/
CREATE TABLE user
(
  id        CHAR(36) NOT NULL,
  name      CHAR(36) NOT NULL,
  user      VARCHAR(64),
  ts_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ts_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

