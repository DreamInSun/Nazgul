/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/17 17:56:27                           */
/*==============================================================*/


drop table if exists als_channle_id;

drop table if exists dls_app_feature;

drop table if exists dls_app_version;

drop table if exists dls_application;

drop table if exists dls_config;

drop table if exists dls_download_record;

drop table if exists dls_download_statistic;

drop table if exists dls_res_mngm;

/*==============================================================*/
/* Table: als_channle_id                                        */
/*==============================================================*/
create table als_channle_id
(
    app_id               char(36) not null,
    chn_name             varchar(255),
    package_id           varchar(255),
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (app_id)
);

alter table als_channle_id comment '默认渠道为本应用。该表记录了同一个应用在不同的渠道的发布ID（唯一索引包名）
';

/*==============================================================*/
/* Table: dls_app_feature                                       */
/*==============================================================*/
create table dls_app_feature
(
    app_id               char(36) not null,
    app_ver_id           char(36) not null,
    feature_id           char(36),
    feature_code         varchar(64),
    feature_deac         varchar(1024) not null,
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*==============================================================*/
/* Table: dls_app_version                                       */
/*==============================================================*/
create table dls_app_version
(
    app_id               char(36) not null,
    app_ver_id           char(36) not null,
    app_ver_name         varchar(255) not null,
    profiles             varchar(1024),
    ts_tag               timestamp,
    ts_upload            timestamp,
    ts_publish           timestamp,
    ts_deprecate         timestamp,
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (app_id, app_ver_id)
);

alter table dls_app_version comment '应用每建立一个发布版本，在该表创建一条发布记录。
版本管理是该服务的核心功能，主要用例都基于版本展开。';

/*==============================================================*/
/* Table: dls_application                                       */
/*==============================================================*/
create table dls_application
(
    app_id               char(36) not null,
    app_name             varchar(64) not null,
    app_namespace        varchar(255) not null,
    app_brief            varchar(255),
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (app_id)
);

alter table dls_application comment '该服务管理的每个应用都在该表有一条记录。记录应用的基本信息。
应用按照app_namespace/app_v';

/*==============================================================*/
/* Table: dls_config                                            */
/*==============================================================*/
create table dls_config
(
    key                  varchar(255) not null,
    app_id               char(36) not null,
value                text,
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

alter table dls_config comment '用于配置存储，每个应用独立配置。

例如：
诸葛IO的统计
七牛CDN账';

/*==============================================================*/
/* Table: dls_download_record                                   */
/*==============================================================*/
create table dls_download_record
(
    app_id               char(36) not null,
    app_ver_id           char(36) not null,
    rec_id               int not null,
    app_ver              varchar(64),
    ip                   varchar(64),
    ts_download          timestamp,
    agent_info           varchar(512),
    os_info              varchar(64),
    device_info          varchar(64)
);

alter table dls_download_record comment '每次下载在该表创建一个下载记录，便于后期统计。';

/*==============================================================*/
/* Table: dls_download_statistic                                */
/*==============================================================*/
create table dls_download_statistic
(
    app_id               char(36) not null,
    app_ver_id           char(36) not null,
    download_cnt         int,
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    key AK_Identifier_1 (app_id, app_ver_id)
);

/*==============================================================*/
/* Table: dls_res_mngm                                          */
/*==============================================================*/
create table dls_res_mngm
(
    app_id               char(36) not null,
    app_ver_id           char(36) not null,
    res_mngn_id          char(36),
    profile              varchar(64),
    os_type              varchar(64),
    local_url            varchar(64),
    cnd_urls             text,
    ts_create            timestamp default CURRENT_TIMESTAMP,
    ts_update            timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

alter table dls_res_mngm comment '同一个应用版本，有不同的profile。默认有devel/qa/rc/product/demo
根据OS区分';

alter table als_channle_id add constraint FK_包名描述 foreign key (app_id)
references dls_application (app_id) on delete restrict on update restrict;

alter table dls_app_feature add constraint FK_版本说明 foreign key (app_id, app_ver_id)
references dls_app_version (app_id, app_ver_id) on delete restrict on update restrict;

alter table dls_app_version add constraint FK_版本记录 foreign key (app_id)
references dls_application (app_id) on delete restrict on update restrict;

alter table dls_config add constraint FK_应用配置管理 foreign key (app_id)
references dls_application (app_id) on delete restrict on update restrict;

alter table dls_download_record add constraint FK_记录 foreign key (app_id, app_ver_id)
references dls_app_version (app_id, app_ver_id) on delete restrict on update restrict;

alter table dls_download_statistic add constraint FK_统计 foreign key (app_id, app_ver_id)
references dls_app_version (app_id, app_ver_id) on delete restrict on update restrict;

alter table dls_res_mngm add constraint FK_资源管理 foreign key (app_id, app_ver_id)
references dls_app_version (app_id, app_ver_id) on delete restrict on update restrict;

