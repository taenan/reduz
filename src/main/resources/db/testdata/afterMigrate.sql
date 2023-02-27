SET foreign_key_checks = 0;

DELETE FROM `reduz`.`category`;
DELETE FROM `reduz`.`link`;
DELETE FROM `reduz`.`user`;

SET foreign_key_checks = 1;

INSERT INTO `user` VALUES (1, 'user1', '$2a$10$gbJ2ptR8erxVKXMgob7bHu1mtL/ZZu9hNG2WmkbyKffs5rKiH3GxG', 'Active');
-- password: asdfF1234

INSERT INTO `reduz`.`category` (`id`,`name`, `icon`, `status`, `user_id`) VALUES (1,'Pessoal', 'home', 'Active', 1);
INSERT INTO `reduz`.`category` (`id`,`name`, `icon`, `status`, `user_id`) VALUES (2,'Trabalho', 'business', 'Active', 1);