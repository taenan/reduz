SET foreign_key_checks = 0;

DELETE FROM `reduz`.`category`;

SET foreign_key_checks = 1;

INSERT INTO `reduz`.`category` (`id`,`name`, `icon`) VALUES (1,'Pessoal', 'fas fa-home');
INSERT INTO `reduz`.`category` (`id`,`name`, `icon`) VALUES (2,'Trabalho', 'fas fa-building');