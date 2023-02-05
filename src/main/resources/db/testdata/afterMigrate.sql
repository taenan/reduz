SET foreign_key_checks = 0;

DELETE FROM `reduz`.`category`;

SET foreign_key_checks = 1;

INSERT INTO `reduz`.`category` (`id`,`name`, `icon`, `status`) VALUES (1,'Pessoal', 'home', 'Active');
INSERT INTO `reduz`.`category` (`id`,`name`, `icon`, `status`) VALUES (2,'Trabalho', 'business', 'Active');