
SQL> CREATE TABLE EMPLOYEE(
    EID INT PRIMARY KEY,
   NAME VARCHAR2(20),
   ADDRESS VARCHAR2(20),
   GENDER CHAR(1) CHECK(GENDER='M' OR GENDER='F'),
   SALARY NUMBER(6),
    SUPEREID REFERENCES EMPLOYEE(EID),
    DNO NUMBER);

Table created.

SQL> 
SQL> INSERT INTO EMPLOYEE VALUES(1,'RAHUL','MANGALURU','M',35000,1,NULL);

1 row created.

SQL> INSERT INTO EMPLOYEE VALUES(2,'SAHANA','MANGALURU','F',35000,1,NULL);

1 row created.

SQL> INSERT INTO EMPLOYEE VALUES(3,'SAGAR','BENGALURU','M',35000,1,NULL);

1 row created.

SQL> INSERT INTO EMPLOYEE VALUES(4,'SAGARIK','MANGALURU','M',35000,1,NULL);

1 row created.

SQL> INSERT INTO EMPLOYEE VALUES(5,'SAJAAN','MYSORE','M',600000,1,NULL);

1 row created.


SQL> SET PAGESIZE 200 LINESIZE 3000;
SQL> SELECT * FROM EMPLOYEE;

       EID NAME                 ADDRESS              G     SALARY   SUPEREID        DNO
---------- -------------------- -------------------- - ---------- ---------- ----------
         1 RAHUL                MANGALURU            M      35000          1
         2 SAHANA               MANGALURU            F      35000          1
         3 SAGAR                BENGALURU            M      35000          1
         4 SAGARIK              MANGALURU            M      35000          1
         5 SAJAAN               MYSORE               M     600000          1

SQL> CREATE TABLE DEPARTMENT(
  2  DNUM NUMBER(5) PRIMARY KEY,
  3  DNAME VARCHAR2(10),
  4  DMGR_ID REFERENCES EMPLOYEE(EID),
  5  MGR_START_DATE DATE);

Table created.

SQL> INSERT INTO DEPARTMENT VALUES(1,'CSE',1,'2-NOV-2007');

1 row created.

SQL> INSERT INTO DEPARTMENT VALUES(2,'IOT',2,'2-NOV-2007');

1 row created.

SQL> INSERT INTO DEPARTMENT VALUES(3,'ACCOUNT',2,'2-NOV-2017');

1 row created.

SQL> INSERT INTO DEPARTMENT VALUES(4,'ISE',1,'2-NOV-2000');

1 row created.

SQL> INSERT INTO DEPARTMENT VALUES(5,'FINANCE',1,'3-NOV-2001');

1 row created.

SQL> SELECT * FROM DEPARTMENT;

      DNUM DNAME         DMGR_ID MGR_START
---------- ---------- ---------- ---------
         1 CSE                 1 02-NOV-07
         2 IOT                 2 02-NOV-07
         3 ACCOUNT             2 02-NOV-17
         4 ISE                 1 02-NOV-00
         5 FINANCE             1 03-NOV-01

SQL> ALTER TABLE EMPLOYEE ADD CONSTRAINT FK FOREIGN KEY(DNO) REFERENCES DEPARTMENT(DNUM);

Table altered.

SQL> UPDATE EMPLOYEE SET DNO=4 WHERE EID=1;

1 row updated.

SQL> UPDATE EMPLOYEE SET DNO=1 WHERE EID=2;

1 row updated.

SQL> UPDATE EMPLOYEE SET DNO=3 WHERE EID=3;

1 row updated.

SQL> UPDATE EMPLOYEE SET DNO=3 WHERE EID=4;

1 row updated.

SQL> UPDATE EMPLOYEE SET DNO=3 WHERE EID=5;

1 row updated.


SQL> SELECT * FROM EMPLOYEE;

       EID NAME                 ADDRESS              G     SALARY   SUPEREID        DNO
---------- -------------------- -------------------- - ---------- ---------- ----------
         1 RAHUL                MANGALURU            M      35000          1          4
         2 SAHANA               MANGALURU            F      35000          1          1
         3 SAGAR                BENGALURU            M      35000          1          3
         4 SAGARIK              MANGALURU            M      35000          1          3
         5 SAJAAN               MYSORE               M     600000          1          3

SQL> CREATE TABLE DLOCATION(
  2  DNO REFERENCES DEPARTMENT(DNUM),
  3  LOCATION VARCHAR2(10),
  4  PRIMARY KEY(DNO,LOCATION));

Table created.

SQL> INSERT INTO DLOCATION VALUES(1,'MANGALURU');

1 row created.

SQL> INSERT INTO DLOCATION VALUES(1,'MYSORE');

1 row created.

SQL> INSERT INTO DLOCATION VALUES(2,'MANGALURU');

1 row created.

SQL> INSERT INTO DLOCATION VALUES(3,'BENGALURU');

1 row created.

SQL> INSERT INTO DLOCATION VALUES(4,'MANGALURU');

1 row created.

SQL> INSERT INTO DLOCATION VALUES(5,'MANGALURU');

1 row created.

SQL>  SELECT * FROM DLOCATION;

       DNO LOCATION
---------- ----------
         1 MANGALURU
         1 MYSORE
         2 MANGALURU
         3 BENGALURU
         4 MANGALURU
         5 MANGALURU

6 rows selected.

SQL> CREATE TABLE PROJECT(
  2  PNUM NUMBER(2) PRIMARY KEY,
  3  PNAME VARCHAR2(20),
  4  PLOCATION VARCHAR2(20),
  5  DNO NUMBER REFERENCES DEPARTMENT(DNUM));

Table created.

SQL> INSERT INTO PROJECT VALUES(1,'IOT','MANGALURU',1);

1 row created.

SQL> INSERT INTO PROJECT VALUES(2,'DATA MINING','MANGALURU',1);

1 row created.

SQL> INSERT INTO PROJECT VALUES(3,'CC','HUBLI',3);

1 row created.

SQL> INSERT INTO PROJECT VALUES(4,'IMAGE PROCESSING','MANGALURU',4);

1 row created.

SQL> INSERT INTO PROJECT VALUES(5,'RESEARCH','MANGALURU',5);

1 row created.

SQL> SELECT * FROM PROJECT;

      PNUM PNAME                PLOCATION                   DNO
---------- -------------------- -------------------- ----------
         1 IOT                  MANGALURU                     1
         2 DATA MINING          MANGALURU                     1
         3 CC                   HUBLI                         3
         4 IMAGE PROCESSING     MANGALURU                     4
         5 RESEARCH             MANGALURU                     5


CREATE TABLE WORKSON(
EID NUMBER(5) REFERENCES EMPLOYEE(EID),
PNO NUMBER(2)REFERENCES PROJECT(PNUM),
HOURS NUMBER(5,2),
PRIMARY KEY(EID,PNO));

INSERT INTO WORKSON VALUES(1,1,4);
INSERT INTO WORKSON VALUES(2,1,5);
INSERT INTO WORKSON VALUES(3,2,4);
INSERT INTO WORKSON VALUES(4,3,4);
INSERT INTO WORKSON VALUES(5,5,4);

SQL> SELECT * FROM WORKSON;

       EID        PNO      HOURS
---------- ---------- ----------
         1          1          4
         2          1          5
         3          2          4
         4          3          4
         5          5          4


CREATE TABLE DEPENDENT(
EMPID INT CONSTRAINT DEP_EMPID_PK PRIMARY KEY,
DEP_NAME VARCHAR2(12),
 GENDER VARCHAR2(5),
BDATE DATE,
RELATIONSHIP VARCHAR2(12), 
FOREIGN KEY(EMPID)REFERENCES EMPLOYEE(EID) ON DELETE CASCADE);

INSERT INTO DEPENDENT VALUES(1,'SHREYA','F','22-JAN-75','WIFE');
INSERT INTO DEPENDENT VALUES(2,'AKASH','M','15-JUN-77','BROTHER');
INSERT INTO DEPENDENT VALUES(3,'PRIYA','F','10-SEP-80','SISTER');
INSERT INTO DEPENDENT VALUES(4,'NIKHIL','M','02-FEB-79','FATHER');
INSERT INTO DEPENDENT VALUES(5,'AKSHA','F','30-OCT-75','MOTHER');

SQL> SELECT * FROM DEPENDENT;

     EMPID DEP_NAME     GENDE BDATE     RELATIONSHIP
---------- ------------ ----- --------- ------------
         1 SHREYA       F     22-JAN-75 WIFE
         2 AKASH        M     15-JUN-77 BROTHER
         3 PRIYA        F     10-SEP-80 SISTER
         4 NIKHIL       M     02-FEB-79 FATHER
         5 AKSHA        F     30-OCT-75 MOTHER


Query 1:
SELECT pno FROM WORKSON WHERE EID IN 
(SELECT Eid FROM EMPLOYEE WHERE NAME='RAHUL') UNION (
SELECT Pnum FROM PROJECT WHERE dno IN (
SELECT Dnum FROM DEPARTMENT WHERE DMGR_ID IN (
SELECT Eid FROM EMPLOYEE WHERE NAME='RAHUL'
)));


      PNO
---------
        1
        2
        4
        5


Query 2:
SELECT Eid, name, salary, salary+0.1*salary as updated_salary FROM EMPLOYEE
WHERE Eid IN (SELECT Eid FROM WORKSON WHERE Pno IN(
SELECT Pnum FROM PROJECT WHERE Pname='IOT'
)); 

       EID NAME                     SALARY UPDATED_SALARY
---------- -------------------- ---------- --------------
         1 RAHUL                     35000          38500
         2 SAHANA                    35000          38500

Query 3:
SELECT SUM(SALARY), AVG(SALARY), MAX(SALARY), MIN(SALARY) FROM EMPLOYEE E, DEPARTMENT D
WHERE D.Dnum=E.Dno AND Dname='ACCOUNT';


SUM(SALARY) AVG(SALARY) MAX(SALARY) MIN(SALARY)
----------- ----------- ----------- -----------
     670000  223333.333      600000       35000

Query 4:
SELECT Eid, name FROM EMPLOYEE E WHERE NOT EXISTS(
(SELECT Pnum FROM PROJECT WHERE Dno=5) MINUS (SELECT Pno FROM WORKSON W WHERE W.Eid=E.Eid));

       EID NAME
---------- --------------------
         5 SAJAAN


Query 5:
CREATE VIEW DEPTINFO(name, count_emp, sum_sal) AS
SELECT D.Dname, COUNT(*), SUM(SALARY) FROM DEPARTMENT D INNER JOIN EMPLOYEE E ON E.Dno = D.Dnum 
GROUP BY D.Dname;

SQL> SELECT * FROM DEPTINFO;

NAME        COUNT_EMP    SUM_SAL
---------- ---------- ----------
ACCOUNT             3     670000
ISE                 1      35000
CSE                 1      35000
