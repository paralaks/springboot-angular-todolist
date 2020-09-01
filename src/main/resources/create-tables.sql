CREATE TABLE IF NOT EXISTS `todo_list`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(75)  NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
)
    COLLATE = 'latin5_turkish_ci'
    ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `todo_item`
(
    `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `list_id`  INT UNSIGNED NOT NULL DEFAULT '0',
    `todo`     VARCHAR(255) NOT NULL DEFAULT '',
    `complete` TINYINT      NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    INDEX `Index 2` (`list_id`)
)
    COLLATE = 'latin5_turkish_ci'
    ENGINE = MyISAM
;
