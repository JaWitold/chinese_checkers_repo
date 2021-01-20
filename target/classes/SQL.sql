DROP DATABASE IF EXISTS chineserepo;

CREATE DATABASE chineserepo;
 
use chineserepo;

CREATE OR REPLACE TABLE game (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `players` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE OR REPLACE TABLE moves (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromX` int(11) NOT NULL,
  `fromY` int(11) NOT NULL,
  `toX` int(11) NOT NULL,
  `toY` int(11) NOT NULL,
  `color` varchar(10) NOT NULL,
  `game_id` int(11),
   CONSTRAINT `fk_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  PRIMARY KEY (`id`)
);

SET GLOBAL time_zone = '-6:00';