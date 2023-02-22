CREATE TABLE `link` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`slug` VARCHAR(7) NOT NULL,
	`title` VARCHAR(255) NOT NULL,
	`favicon` TEXT NULL,
	`original` TEXT NULL,
	`counter` INT NOT NULL DEFAULT 0,
	`created_at` TIMESTAMP NOT NULL,
	`status` VARCHAR(8) NOT NULL,
	`category_id` BIGINT NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `slug` (`slug`),
	CONSTRAINT `FK__category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb3_general_ci'
;