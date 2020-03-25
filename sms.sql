DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `classId` int(11),
  `schoolName` varchar(100) DEFAULT NULL,
  `departName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class`
--

INSERT INTO `class` VALUES (1,'计算机学院','软件工程'),(3,'计算机学院','计算机科学与技术'),(5,'艺术设计学院','服装设计'),(6,'自动化学院','物联网工程'),(7,'自动化学院','物联网工程'),(8,'计算机学院','计算机科学与技术'),(9,'政法学院','法学'),(10,'政法学院','法学'),(11,'计算机学院','软件工程');


--
-- Table structure for table `cource`
--

DROP TABLE IF EXISTS `cource`;

CREATE TABLE `cource` (
  `courceId` int(11),
  `courceName` varchar(100) NOT NULL,
  `courceHour` float DEFAULT NULL,
  PRIMARY KEY (`courceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `cource`
--


INSERT INTO `cource` VALUES (1,'大学英语',2.5),(2,'大学物理',1.5),(3,'高等数学',5.5),(4,'C语言程序设计',3),(5,'数据结构',3),(6,'线性代数',1.5),(7,'大学语文',1.5),(8,'服装市场考察',2.5);


--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;

CREATE TABLE `school` (
  `schoolName` varchar(100) NOT NULL,
  PRIMARY KEY (`schoolName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `school`
--


INSERT INTO `school` VALUES ('计算机学院'),('艺术设计学院'),('自动化学院'),('政法学院'),('物理学院');


--
-- Table structure for table `sc`
--

DROP TABLE IF EXISTS `sc`;

CREATE TABLE `sc` (
  `stuNumber` int(11) NOT NULL,
  `courceName` varchar(100) NOT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`stuNumber`, `courceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `sc`
--


INSERT INTO `sc` VALUES (3001,'C语言程序设计',95),(3002,'高等数学',59),(3004,'高等数学',96),(3001,'数据结构',97),(3006,'大学物理',100);


--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `deptId` int(11),
  `schoolName` varchar(100) NOT NULL,
  `departName` varchar(100) NOT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `department`
--



INSERT INTO `department` VALUES (1,'计算机学院','计算机科学与技术'),(3,'艺术设计学院','服装设计'),(5,'计算机学院','软件工程'),(6,'政法学院','法学'),(7,'自动化学院','物联网工程'),(14,'计算机学院','网络工程');



--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stuNumber` int(11) NOT NULL,
  `stuName` varchar(20) NOT NULL,
  `stuSchool` varchar(100) DEFAULT NULL,
  `stuDept` varchar(100) DEFAULT NULL,
  `stuClass` int(11) DEFAULT NULL,
  PRIMARY KEY (`stuNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `student`
--


INSERT INTO `student` VALUES (3001,'黄章','计算机学院','软件工程',1),(3003,'马云','计算机学院','计算机科学与技术',2),(3002,'马化腾','计算机学院','软件工程',1),(3004,'周鸿祎','自动化学院','物联网工程',2),(3005,'雷军','自动化学院','物联网工程',2),(3006,'李彦宏','物理学院','物理学',1),(3007,'刘强东','政法学院','法学',1),(3008,'丁磊','物理学院','物理学',1),(3009,'张朝阳','艺术设计学院','服装设计',1);
