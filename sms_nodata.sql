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

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseId` int(11),
  `courseName` varchar(100) NOT NULL,
  `credit` float DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `key_coursename`(`courseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `cource`
--



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


--
-- Table structure for table `sc`
--

DROP TABLE IF EXISTS `sc`;

CREATE TABLE `sc` (
  `stuNumber` int(11) NOT NULL,
  `courseName` varchar(100) NOT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`stuNumber`, `courseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `sc`
--


--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `deptId` int(11),
  `schoolName` varchar(100) NOT NULL,
  `departName` varchar(100) NOT NULL,
  PRIMARY KEY (`deptId`),
  UNIQUE KEY `key_departname` (`departName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `department`
--



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
  PRIMARY KEY (`stuNumber`),
  KEY `key_stuname` (`stuName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `student`
--

alter table `class` add constraint `class_school` foreign key (schoolName) references school (schoolName)
on delete cascade
on update cascade;

alter table `class` add constraint `class_depart` foreign key (departName) references department (departName)
on delete cascade
on update cascade;

alter table `sc` add constraint `sc_student` foreign key (stuNumber) references student (stuNumber)
on delete cascade
on update cascade;

alter table `sc` add constraint `sc_course` foreign key (courseName) references course (courseName)
on delete cascade
on update cascade;

alter table `department` add constraint `department_school` foreign key (schoolName) references school (schoolName)
on delete cascade
on update cascade;

alter table `student` add constraint `student_school` foreign key (stuSchool) references school (schoolName)
on delete cascade
on update cascade;

alter table `student` add constraint `student_depart` foreign key (stuDept) references department (departName)
on delete cascade
on update cascade;

alter table `student` add constraint `student_class` foreign key (stuClass) references class (classId)
on delete cascade
on update cascade;