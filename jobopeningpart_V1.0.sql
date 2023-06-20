

CREATE TABLE [Company] (
  [Company_ID] int IDENTITY(1,1) NOT NULL,
  [Company_Name] varchar(45) NOT NULL,
  [URL] varchar(512),
  [Location] varchar(45) NOT NULL,
  [Industry] varchar(45) NOT NULL,
  [Logo] varbinary(max),
  PRIMARY KEY ([Company_ID])
);

ALTER TABLE [Company]
ADD CONSTRAINT Unique_CompanyName UNIQUE (Company_Name);

INSERT INTO [Company] ([Company_Name], [Location], [Industry])
VALUES ('XYZ Corp', 'London', 'Finance');
INSERT INTO [Company] ([Company_Name], [URL], [Location], [Industry], [Logo])
VALUES ('PQR Corp', 'http://www.pqrcorp.com', 'San Francisco', 'Retail', NULL);



CREATE TABLE [Member] (
  [ID] INT IDENTITY(1,1) NOT NULL,
  [Company_ID]  INT    NUll,
  [User_Type] varchar(45)  NOT NULL,
  [First_Name] varchar(45)  NOT NULL,
  [Last_Name] varchar(45)  NOT NULL,
  [Email] varchar(45)  NOT NULL,
  [Password] varchar(45)  NOT NULL,
  [Sex] varchar(6)  NOT NULL,
  [Country] varchar(45)  NOT NULL,
  [City] varchar(45)  NOT NULL,
  [Birthday] DATE  NOT NULL,
  [Phone_Number] INT,
  [Profile_Pic] varbinary(max),
  [Postion] varchar(45),
  PRIMARY KEY ([ID]),
  CONSTRAINT [FK_Member_Company]
    FOREIGN KEY ([Company_ID])
    REFERENCES [Company]([Company_ID])
);
ALTER TABLE Member
ADD CONSTRAINT CHK_Member_UserType CHECK (User_Type IN ('employer', 'employee','JobSeeker'));

CREATE TABLE [Skill] (
  [Skiil_ID] int,
  [Skill_Name] varchar(45),
  PRIMARY KEY ([Skiil_ID])
);

CREATE TABLE [MemberSkills] (
  [ID] int  NOT NULL,
  [Skill_ID] int  NOT NULL,
  [Skill_Level] varchar(45) NOT NULL CHECK (Skill_Level IN ('Beginner', 'Intermediate', 'Advanced', 'Expert')),
  CONSTRAINT [FK_MemberSkills.Skill_ID]
    FOREIGN KEY ([Skill_ID])
      REFERENCES [Skill]([Skiil_ID]),
  CONSTRAINT [FK_MemberSkills.ID]
    FOREIGN KEY ([ID])
      REFERENCES [Member]([ID])
);

CREATE TABLE [MemberPhones] (
  [Phone_Number] int NOT NULL ,
  [ID] int NOT NULL,
  FOREIGN KEY ([ID])
      REFERENCES [Member]([ID]),
  PRIMARY KEY ([Phone_Number])
);



CREATE VIEW EmployerView AS
SELECT CONCAT(First_name, ' ',Last_Name) as Name, ID,Company_ID
FROM Member M
WHERE M.User_Type = 'employer';

CREATE VIEW IntersertID AS
SELECT Interset

drop view EmployerView

SELECT ID as EmployerID,Name
FROM EmployerView
WHERE Company_ID = 1;

SELECT ID as EmployerID
FROM EmployerView
WHERE NAME = 'ayman tota' AND Company_ID = 1;


INSERT INTO [Member] ([Company_ID],[User_Type], [First_Name], [Last_Name], [Email], 
[Password], [Sex], [Country], [City], [Birthday],[Phone_Number],[Postion],[Salary])
VALUES ( 1,'employer', 'methad', 'sisha', 'kito@example.com', '11', 
'female', 'United States', 'aswan', '1990-01-01','15795565','cleaner','15600');


ALTER TABLE [Member]
ADD [Salary] INT;



CREATE TABLE [JobOpening] (
  [Job_Opening_ID]  int IDENTITY(1,1) NOT NULL,
  [Company_ID] int NOT NULL,
  [Job_Tilte] varchar(45) NOT NULL,
  [Job_Description] varchar(500) NOT NULL,
  [Job_Type] varchar(45) NOT NULL,
  [Salary] int,
  [Deadline] date NOT NULL,
  PRIMARY KEY ([Job_Opening_ID]),
  CONSTRAINT [FK_JobOpening.Company_ID]
    FOREIGN KEY ([Company_ID])
      REFERENCES [Company]([Company_ID])
);
SELECT
  m.First_Name,
  m.Last_Name,
  m.Email,
  m.Sex,
  m.Country,
  m.City,
  DATEDIFF(YEAR, m.Birthday, GETDATE()) AS Age,
  m.Phone_Number,
  m.Postion AS Current_Position,
  c.Company_Name AS Current_Company,
  c.Industry AS Current_Industry
FROM
  Member m
  LEFT JOIN Company c ON m.Company_ID = c.Company_ID;


INSERT INTO JobOpening (Company_ID, Job_Tilte, Job_Description, Job_Type, Salary, Deadline)
VALUES (1, 'Software Engineer', 'Seeking a skilled software engineer with experience in Java and SQL.', 'Full-time', 80000, '2023-07-15');

INSERT INTO JobOpening (Company_ID, Job_Tilte, Job_Description, Job_Type, Salary, Deadline)
VALUES
  (1, 'Job 1', 'Job 1 Description', 'part-time', 50000, '2023-06-30'),
  (2, 'Job 2', 'Job 2 Description', 'Type 2', 60000, '2023-07-15'),
  (3, 'Job 3', 'Job 3 Description', 'Type 1', 55000, '2023-07-31'),
  (4, 'Job 4', 'Job 4 Description', 'Type 3', 70000, '2023-08-15'),
  (5, 'Job 5', 'Job 5 Description', 'Type 2', 65000, '2023-08-31');

  ALTER TABLE JobOpening
ADD CONSTRAINT CHK_JobOpening_JobType CHECK (Job_Type IN ('part-time', 'full-time'));

DELETE FROM JobOpening
WHERE Job_Opening_ID > 8;




CREATE TABLE [Application] (
  [ID] int NOT NULL,
  [Job_Opening_ID] int NOT NULL,
  [Resume] varbinary(max),
  [Application_Status] varchar(45) NOT NULL CHECK (Application_Status IN ('Rejected', 'Pending', 'Selected')),
  [Sent_Date] date NOT NULL,
  CONSTRAINT [FK_Application.ID]
    FOREIGN KEY ([ID])
      REFERENCES [Member]([ID]),
  CONSTRAINT [FK_Application.Job_Opening_ID]
    FOREIGN KEY ([Job_Opening_ID])
      REFERENCES [JobOpening]([Job_Opening_ID])
);

INSERT INTO Application (ID, Job_Opening_ID, Resume, Application_Status, Sent_Date)
VALUES (1, 7, 0x0123456789ABCDEF, 'Pending', '2023-06-01'),
	   (4, 7, 0x0123456789ABCDEF, 'Pending', '2023-06-01');

SELECT
    M.ID AS MemberID,
    J.Job_Tilte AS Job_Title,
	J.Job_Opening_ID,
    CONCAT(M.First_Name, ' ', M.Last_Name) AS Name,
    M.Email,
    M.City,
    M.Phone_Number,
    M.Postion AS Current_Position,
    C.Company_Name AS Current_Company,
    A.Application_Status,
	C.Company_ID
FROM
    Member M
     JOIN Application A ON M.ID = A.ID
     JOIN JobOpening J ON A.Job_Opening_ID = J.Job_Opening_ID
    LEFT JOIN Company C ON M.Company_ID = C.Company_ID;
   


CREATE TABLE [Interview] (
  [Job_Opening_ID] int NOT NULL,
  [Employer] int NOT NULL,
  [Job_Seeker] int NOT NULL,
  [Interview_Status] varchar(9) NOT NULL CHECK (Interview_Status IN ('Scheduled', 'Completed', 'Cancelled','Accepted')),
  [Date] date NOT NULL,
  [Interview_Type] varchar(8) NOT NULL CHECK (Interview_Type IN ('Phone', 'Video', 'InPerson')),
  PRIMARY KEY ([Job_Opening_ID], [Employer], [Job_Seeker]),
  CONSTRAINT [FK_Interview.Job_Opening_ID]
    FOREIGN KEY ([Job_Opening_ID])
      REFERENCES [JobOpening]([Job_Opening_ID]),
  CONSTRAINT [FK_Interview.Employer]
    FOREIGN KEY ([Employer])
      REFERENCES [Member]([ID]),
  CONSTRAINT [FK_Interview.Job_Seeker]
    FOREIGN KEY ([Job_Seeker])
      REFERENCES [Member]([ID])
);

SELECT
    J.Job_Opening_ID,
    J.Job_Tilte AS Job_Title,
    CONCAT(E.First_Name, ' ', E.Last_Name) AS Interviewer,
    CONCAT(JS.First_Name, ' ', JS.Last_Name) AS Interviewee,
    I.Interview_Type,
    I.Date,
    I.Interview_Status,
	JS.ID AS IntervieweeID
FROM
    JobOpening J
    JOIN Interview I ON J.Job_Opening_ID = I.Job_Opening_ID
    JOIN Member E ON I.Employer = E.ID
    JOIN Member JS ON I.Job_Seeker = JS.ID
WHERE
    I.Interview_Status = 'Scheduled'
    AND J.Company_ID = 1;




	INSERT INTO Interview (Job_Opening_ID, Employer, Job_Seeker, Interview_Status, Date, Interview_Type)
VALUES (7, 5, 4, 'Scheduled', '2023-12-10', 'InPerson');



CREATE TABLE [Interset] (
  [Interset_ID] int IDENTITY(1,1) NOT NULL,
  [Interset_Name] varchar(45) NOT NULL,
  [Description] varchar(45),
  PRIMARY KEY ([Interset_ID])
);
CREATE TABLE [MemberIntersets] (
  [ID] int NOT NULL,
  [Interset_ID] int NOT NULL,
  CONSTRAINT [FK_MemberIntersets.ID]
    FOREIGN KEY ([ID])
      REFERENCES [Member]([ID]),
  CONSTRAINT [FK_MemberIntersets.Interset_ID]
    FOREIGN KEY ([Interset_ID])
      REFERENCES [Interset]([Interset_ID])
);

ALTER TABLE [Interset]
ADD CONSTRAINT [Interset_Name_Unique] UNIQUE (Interset_Name);


INSERT INTO Interset (Interset_Name)
VALUES
    ('good'),
    ('Music'),
    ('Art'),
    ('Technology'),
    ('Travel'),
    ('Cooking'),
    ('Reading'),
    ('Fashion'),
    ('Fitness'),
    ('Photography');




CREATE TABLE [JobOpeningInterset] (
  [Interset_ID] int NOT NULL,
  [Job_Opening_ID] int NOT NULL,
  CONSTRAINT [FK_JobOpeningInterset.Interset_ID]
    FOREIGN KEY ([Interset_ID])
      REFERENCES [Interset]([Interset_ID]),
  CONSTRAINT [FK_JobOpeningInterset.Job_Opening_ID]
    FOREIGN KEY ([Job_Opening_ID])
      REFERENCES [JobOpening]([Job_Opening_ID])
);
 drop table[JobOpeningInterset]