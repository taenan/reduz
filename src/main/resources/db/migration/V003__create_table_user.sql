CREATE TABLE `user` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(75) NOT NULL,
	`status` VARCHAR(8) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb3_general_ci'
;