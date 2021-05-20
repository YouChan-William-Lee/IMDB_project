-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: imdb
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(45) NOT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `type_of_user` varchar(45) DEFAULT NULL,
  `approved` tinyint NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','$2a$10$h.dl5J86rGH7I8bD9bZeZe','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','asdf123@gmail.com','Australia','Male','John','Walker','admin',1),('critics1','$2a$10$h.dl5J86rGH7I8bD9bZeZe','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','123adsf@gmail.com','Australia','Female','Sarah','Wilson','critics',1),('critics2','$2a$10$e0MYzXyjpJS7Pd0RVvHwHe','$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy','qwer123@gmail.com','Australia','Male','Bucky','Barnes','critics',1),('critics3','$2a$10$E3DgchtVry3qlYlzJCsyxe','$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2','zxcv123@gmail.com','Australia','Male','Barry','Allen','critics',1),('critictest1','$2a$10$eZI7XZa5838nM0/5nXmTtu','$2a$10$eZI7XZa5838nM0/5nXmTtu54fv5n3M4rUosU1QWvNl7rJMc/V57h6','test@gmail.com','Australia','Male','Karl','johnson','critics',0),('critictest2','$2a$10$T/2DCdSsOQOie1pi/1sxXu','$2a$10$T/2DCdSsOQOie1pi/1sxXuRIo1mjZMEUJbSColDQ8UqQ3.DpwU8fy','test2@gamil.com','Australia','Male','Phil','Lip','critics',0),('pco1','$2a$10$uhlWAaQC5JqZwlGXKq8t8e','$2a$10$uhlWAaQC5JqZwlGXKq8t8e68duZCttC/26wEtv9Lb9pjLD4eXxo/y','willsmit@google.com','Australia','Male','Will','Smith','pco',0),('pco2','$2a$10$cgpIVJRE4U6lxIDre3qRcO','$2a$10$cgpIVJRE4U6lxIDre3qRcOfyqGDc/yRzSqpEUPveSo4LqwkYRRp7i','johnnydepp@google.com','Australia','Male','Johnny','Depp','pco',1),('regular1','$2a$10$h.dl5J86rGH7I8bD9bZeZe','$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO','asdf@gmail.com','Australia','Male','Andrew','Jason','regular',1),('regular2','$2a$10$e0MYzXyjpJS7Pd0RVvHwHe','$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy','qwer@gmail.com','Australia','Female','Emma','Wilson','regular',1),('regular3','$2a$10$E3DgchtVry3qlYlzJCsyxe','$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2','zxcv@gmail.com','Australia','Male','William','Liam','regular',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credits_roll`
--

DROP TABLE IF EXISTS `credits_roll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credits_roll` (
  `person_id` int NOT NULL,
  `role` varchar(45) NOT NULL,
  `show_id` int NOT NULL,
  `start_year` int NOT NULL,
  `end_year` int DEFAULT '0',
  `character_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`show_id`,`person_id`),
  KEY `fk_person_idx` (`person_id`),
  CONSTRAINT `fk_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `fk_show` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credits_roll`
--

LOCK TABLES `credits_roll` WRITE;
/*!40000 ALTER TABLE `credits_roll` DISABLE KEYS */;
INSERT INTO `credits_roll` VALUES (6,'Actor',1,2019,0,'Kylo Ren'),(11,'Actress',1,2019,0,'Maz Kanata'),(13,'Actress',1,2019,0,'Rey'),(14,'Actor',2,2005,0,'Obi-Wan Kenoboi'),(15,'Actress',2,2005,0,'Queen Amidala'),(16,'Actor',3,1980,0,'Luke Skywalker'),(17,'Actor',3,1980,0,'Princess Leia'),(13,'Actress',4,2015,0,'Rey'),(18,'Actor',4,2015,0,'Finn'),(19,'Actor',5,2010,0,'Cobb'),(20,'Actor',5,2010,0,'Eames'),(21,'Actor',6,2019,0,'Captain America'),(22,'Actor',6,2019,0,'Thor');
/*!40000 ALTER TABLE `credits_roll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `bio` varchar(1000) NOT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Robert De Niro','Actor','1943-08-17','One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City, to artists Virginia (Admiral) and Robert De Niro Sr. His paternal grandfather was of Italian descent, and his other ancestry is Irish, English, Dutch, German, and French.'),(2,'Robert Pattinson','Actor','1986-05-13','Robert Douglas Thomas Pattinson was born May 13, 1986 in London, England, to Richard Pattinson, a car dealer importing vintage cars, and Clare Pattinson (née Charlton), who worked as a booker at a model agency. He grew up in Barnes, southwest London with two older sisters and attended Tower House'),(3,'Claire Foy','Actress','1984-04-16','Claire Elizabeth Foy (born 16 April 1984) is an English actress. She studied acting at the Liverpool John Moores University and the Oxford School of Drama and made her screen debut in the pilot of the supernatural comedy series Being Human, in 2008.'),(4,'Olivia Colman','Actress','1974-01-30','Olivia Colman was born on January 30, 1974 in Norwich, Norfolk, England as Sarah Caroline Olivia Colman. She is an actress, known for The Favourite (2018), Tyrannosaur (2011) and The Lobster (2015). She has been married to Ed Sinclair since August 2001. They have three children.'),(5,'Troy Baker','Actor','1976-04-01','Troy Baker was born on April 1, 1976 in Dallas, Texas, USA as Troy Edward Baker. He is known for his work on The Last of Us (2013), BioShock Infinite (2013) and Batman: Arkham Knight (2015). He has been married to Pamela Walworth since October 13, 2012. They have one child.'),(6,'Adam Driver','Actor','1983-11-03','Adam Douglas Driver was born in San Diego, California. His mother, Nancy (Needham) Wright, is a paralegal from Mishawaka, Indiana, and his father, Joe Douglas Driver, who has deep roots in the American South, is from Little Rock, Arkansas.'),(7,'Chadwick Boseman','Actor, Producer, Writer','1977-11-29','Chadwick Boseman is an American actor. He is known for his portrayal of T\'Challa / Black Panther in the Marvel Cinematic Universe (since 2016), particularly in Black Panther (2018), and for his starring roles in as Jackie Robinson in 42 (2013), James Brown in Get on Up (2014), and Thurgood Marshall in Marshall (2017). '),(8,'Gal Gadot','Actress, Producer','1985-04-30','Gal Gadot is an Israeli actress, singer, martial artist, and model. She was born in Rosh Ha\'ayin, Israel, to a Jewish family. Her parents are Irit, a teacher, and Michael, an engineer, who is a sixth-generation Israeli. She served in the IDF for two years, and won the Miss Israel title in 2004.'),(9,'Meryl Streep','Actress','1949-06-22','Considered by many critics to be the greatest living actress, Meryl Streep has been nominated for the Academy Award an astonishing 21 times, and has won it three times. Meryl was born Mary Louise Streep in 1949 in Summit, New Jersey, to Mary Wolf (Wilkinson), a commercial artist, and Harry William Streep, Jr., a pharmaceutical executive.'),(10,'Cate Blanchett','Actress','1969-05-14','Cate Blanchett was born on May 14, 1969 in Melbourne, Victoria, Australia, to June (Gamble), an Australian teacher and property developer, and Robert DeWitt Blanchett, Jr., an American advertising executive, originally from Texas.'),(11,'Lupita Nyong\'o','Actress, Producer','1983-03-01','Lupita Amondi Nyong\'o was born March 1, 1983 in Mexico City, Mexico, to Kenyan parents, Dorothy Ogada Buyu and Peter Anyang\' Nyong\'o. Her father, a senator, was then a visiting lecturer in political science. She was raised in Kenya. At age 16, her parents sent her back to Mexico for seven months to learn Spanish.'),(12,'Zoe Kravitz','Actress, Producer','1988-12-01','Zoe Isabella Kravitz, the daughter of singer/actor Lenny Kravitz and actress Lisa Bonet, was born on December 1, 1988 in Los Angeles, California. She is of half African-American (from her father\'s mother and her mother\'s father) and half Ashkenazi Jewish (from her father\'s father and her mother\'s mother) descent.'),(13,'Daisy Ridley','Actress','1992-04-10','Daisy Jazz Isobel Ridley is an English actress. She is best known for her breakthrough role as \\\"Rey\\\" in the 2015 film, Star Wars: Episode VII - The Force Awakens (2015). Daisy was born in Westminster, London, on April 10, 1992. She is the daughter of Louise Fawkner-Corbett and Chris Ridley.'),(14,'Ewan McGregor','Actor','1971-03-31','Ewan Gordon McGregor was born on March 31, 1971 in Perth, Perthshire, Scotland, to Carol Diane (Lawson) and James Charles McGregor, both teachers. His uncle is actor Denis Lawson. He was raised in Crieff. At age 16, he left Morrison Academy to join the Perth Repertory Theatre'),(15,'Natalie Portman','Actress','1981-06-09','Natalie Portman is the first person born in the 1980s to have won the Academy Award for Best Actress (for Black Swan (2010)). Natalie was born Natalie Hershlag on June 9, 1981, in Jerusalem, Israel.'),(16,'Mark Hamil','Actor','1951-09-25','Mark Richard Hamill (born September 25, 1951) is an American actor, voice actor, and writer. He is known for playing Luke Skywalker in the Star Wars film series, winning three Saturn Awards for the role. His other film appearances include Corvette Summer (1978) and The Big Red One (1980). Hamill has also appeared on stage in several theater productions, primarily during the 1980s.'),(17,'Carrie Fisher','Actor','1956-10-21','Carrie Frances Fisher (October 21, 1956 - December 27, 2016) was an American actress and writer. Fisher played Princess Leia in the Star Wars films, a role for which she was nominated for four Saturn Awards. Her other film credits include Shampoo (1975), The Blues Brothers (1980), Hannah and Her Sisters (1986), The \'Burbs (1989), When Harry Met Sally... (1989), Soapdish (1991), and The Women (2008).Fisher was nominated twice for the Primetime Emmy Award for Outstanding Guest Actress in a Comedy Series for her performances on the television series 30 Rock and Catastrophe. She was posthumously made a Disney Legend in 2017, and in 2018 she was awarded a posthumous Grammy Award for Best Spoken Word Album.'),(18,'John Boyega','Actor','1992-03-17','John Adedayo Bamidele Adegboyega (born 17 March 1992), known professionally as John Boyega, is a British-Nigerian actor and producer.He first rose to prominence in his native Britain for his role as Moses in the sci-fi comedy film Attack the Block (2011), and his international breakthrough came with his role as Finn in the Star Wars sequel trilogy films The Force Awakens (2015), The Last Jedi (2017), and The Rise of Skywalker (2019).'),(19,'Leonardo DiCaprio','Actor','1974-11-11','Few actors in the world have had a career quite as diverse as Leonardo DiCaprio\'s. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom Growing Pains (1985) and low budget horror movies, such as Critters 3 (1991), to a major teenage heartthrob in the 1990s, as the hunky lead actor in movies such as Romeo + Juliet (1996) and Titanic (1997), to then become a leading man in Hollywood blockbusters, made by internationally renowned directors such as Martin Scorsese and Christopher Nolan.'),(20,'Tom Hardy','Actor','1977-09-15','With his breakthrough performance as Eames in Christopher Nolan\'s sci-fi thriller Inception (2010), English actor Tom Hardy has been brought to the attention of mainstream audiences worldwide. However, the versatile actor has been steadily working on both stage and screen since his television debut in the miniseries Band of Brothers (2001).'),(21,'Chris Evans','Actor','1981-06-13','Christopher Robert Evans (born June 13, 1981) is an American actor, best known for his role as Captain America in the Marvel Cinematic Universe (MCU) series of films. Evans began his career with roles in television series, such as in Opposite Sex in 2000. Following appearances in several teen films including 2001\'s Not Another Teen Movie, he gained attention for his portrayal of Marvel Comics character Human Torch in 2005\'s Fantastic Four, and its sequel Fantastic Four: Rise of the Silver Surfer (2007). Evans made further appearances in film adaptations of comic books and graphic novels: TMNT (2007), Scott Pilgrim vs. the World (2010), and Snowpiercer (2013).'),(22,'Chris Hemsworth','Actor','1983-08-11','Christopher Hemsworth (born 11 August 1983) is an Australian actor. He first rose to prominence in Australia playing Kim Hyde in the Australian television series Home and Away (2004-2007) before beginning a film career in Hollywood. Hemsworth is best known for playing Thor in eight Marvel Cinematic Universe films, beginning with Thor (2011) and appearing most recently in Avengers: Endgame (2019), which established him among the world\'s highest-paid actors.');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_company`
--

DROP TABLE IF EXISTS `production_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_company` (
  `proco_id` int NOT NULL AUTO_INCREMENT,
  `proco_name` varchar(45) NOT NULL,
  PRIMARY KEY (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_company`
--

LOCK TABLES `production_company` WRITE;
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` VALUES (1,'Universal Pictures'),(2,'Paramount Pictures'),(3,'20th Century Fox'),(4,'Warner Bros.'),(5,'DreamWorks Pictures'),(6,'Metro-Goldwyn-Meyer'),(7,'Miramax'),(8,'Columbia Pictures'),(9,'Walt Disney Pictures'),(10,'Sony Pictures'),(11,'New Line Cinema'),(12,'Lucas Films'),(13,'Lucasflim'),(14,'Legendary Entertainment'),(15,'Marvel Studios');
/*!40000 ALTER TABLE `production_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show`
--

DROP TABLE IF EXISTS `show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show` (
  `showid` int NOT NULL AUTO_INCREMENT,
  `show_title` varchar(55) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `length` decimal(3,2) NOT NULL,
  `movie` int NOT NULL DEFAULT '1',
  `series` int NOT NULL DEFAULT '0',
  `proco_id` int NOT NULL,
  `year` int NOT NULL DEFAULT '2020',
  `approved` tinyint NOT NULL,
  `imageAddress` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`showid`),
  KEY `fk_proco_idx` (`proco_id`),
  CONSTRAINT `fk_proco` FOREIGN KEY (`proco_id`) REFERENCES `production_company` (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show`
--

LOCK TABLES `show` WRITE;
/*!40000 ALTER TABLE `show` DISABLE KEYS */;
INSERT INTO `show` VALUES (1,'Star Wars: Episode IX - The Rise of Skywalker','Action',2.22,1,0,9,2019,1,'https://lumiere-a.akamaihd.net/v1/images/star-wars-the-rise-of-skywalker-theatrical-poster-1000_ebc74357.jpeg?region=0%2C0%2C891%2C1372'),(2,'Star Wars: Episode III - Revenge of The Sith','Action',2.20,1,0,9,2005,1,'https://i.pinimg.com/originals/9d/6a/23/9d6a232c628e4aaaecfaf3dbdb1d9653.jpg'),(3,'Star Wars: Episode V - The Empire strikes Back','Action',2.07,1,0,12,1980,1,'https://i.redd.it/brn7y2yjk1051.jpg'),(4,'Star Wars: Episode VII - The Force Awakens','Action',2.15,1,0,13,2015,1,'https://lumiere-a.akamaihd.net/v1/images/swtfa-digital_900f3ac4.jpeg?region=0%2C0%2C645%2C1000'),(5,'Inception','Sci-Fi',2.28,1,0,14,2010,1,'https://flxt.tmsimg.com/assets/p7825626_p_v10_af.jpg'),(6,'Avengers: Endgame','Action',3.02,1,0,15,2019,0,'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UY1200_CR90,0,630,1200_AL_.jpg');
/*!40000 ALTER TABLE `show` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_review` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `review` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `fk_showid_idx` (`show_id`),
  KEY `fk_username_idx` (`user_id`),
  CONSTRAINT `fk_showid` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`),
  CONSTRAINT `fk_username` FOREIGN KEY (`user_id`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` VALUES (1,1,3,'This movie is such a nice movie.','2021-05-14 22:49:56'),(2,1,3,'I love it.','2021-05-14 22:50:40'),(3,2,1,'It was just okay.','2021-05-14 23:08:48'),(4,1,2,'Aweosme!!!','2021-05-14 23:10:06'),(5,3,5,'The best movie in my life.','2021-05-14 23:31:55');
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

--
-- Table structure for table `user_rating`
--

DROP TABLE IF EXISTS `user_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_rating` (
  `ratingId` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `rating` int NOT NULL DEFAULT '3',
  `date` datetime NOT NULL,
  PRIMARY KEY (`ratingId`),
  KEY `fk_showid_idx` (`show_id`),
  KEY `fk_username_idx` (`user_id`),
  CONSTRAINT `fk_showid2` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`),
  CONSTRAINT `fk_username2` FOREIGN KEY (`user_id`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_feedback` (
  `feedbackId` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `feedback` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`feedbackId`),
  KEY `fk_showid_idx` (`show_id`),
  KEY `fk_username_idx` (`user_id`),
  CONSTRAINT `fk_showid3` FOREIGN KEY (`show_id`) REFERENCES `show` (`showid`),
  CONSTRAINT `fk_username3` FOREIGN KEY (`user_id`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dump completed on 2021-05-19 22:39:22
