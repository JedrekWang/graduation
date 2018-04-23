
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(15) NOT NULL,
  `account` varchar(20) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `user_desc` varchar(120) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE `folder` (
  `folder_id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(50) NOT NULL,
  `folder_desc` varchar(120) DEFAULT NULL,
  `parent_folder_id` int(11) DEFAULT NULL,
  `content_url` varchar(120) DEFAULT NULL,
  `created_user_id` int(11) NOT NULL,
  `created_user_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`folder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

CREATE TABLE `file_info` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(50) NOT NULL,
  `format` varchar(10) DEFAULT NULL,
  `parent_folder_id` int(11) DEFAULT NULL,
  `content_url` varchar(120) NOT NULL,
  `created_user_id` int(11) NOT NULL,
  `created_user_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

CREATE TABLE `user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `group_desc` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `user_group_con` (
  `user_group_con_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_group_con_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `version` (
  `version_id` int(11) NOT NULL AUTO_INCREMENT,
  `version_key` varchar(20) NOT NULL,
  `document_id` int(11) NOT NULL,
  `version_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`version_id`),
  UNIQUE KEY `version_key` (`version_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `document_desc` varchar(120) DEFAULT NULL,
  `content_url` varchar(50) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` int(11) NOT NULL,
  `last_modify_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modify_user_id` int(11) DEFAULT NULL,
  `root_document_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;