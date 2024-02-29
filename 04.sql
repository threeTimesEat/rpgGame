use team;


CREATE TABLE IF NOT EXISTS MEMBER_INFO (
	MEMBER_CODE INT AUTO_INCREMENT,
    MEMBER_NAME VARCHAR(70),
    BIRTH_DATE DATE,
    DIVISION_CODE CHAR(2),
    DETAIL_INFO VARCHAR(500),
    CONTACT VARCHAR(50),
    TEAM_CODE INT,
	ACTIVE_STATUS CHAR(2) DEFAULT 'Y',
    PRIMARY KEY(MEMBER_CODE),
    FOREIGN KEY(TEAM_CODE) REFERENCES TEAM_INFO(TEAM_CODE),
    CHECK(ACTIVE_STATUS IN ('Y', 'N', 'H'))
) ENGINE = INNODB;

DESCRIBE MEMBER_INFO;

CREATE TABLE IF NOT EXISTS TEAM_INFO (
	TEAM_CODE INT AUTO_INCREMENT,
    TEAM_NAME VARCHAR(100),
    TEAM_DETAIL VARCHAR(500),
    USE_YN CHAR(2) DEFAULT 'Y',
    PRIMARY KEY(TEAM_CODE),
    CHECK(USE_YN IN ('Y', 'N'))
) ENGINE = INNODB;

DESCRIBE TEAM_INFO;

INSERT INTO TEAM_INFO(TEAM_CODE, TEAM_NAME, TEAM_DETAIL, USE_YN)
VALUES (NULL, '음악감상부', '클래식 및 재즈 음악을 감상하는 사람들의 모임', 'Y'),
		(NULL, '맛집탐방부', '맛집을 찾아다니는 사람들의 모임', 'N'),
		(NULL, '행복찾기부', NULL, 'Y');

SELECT * FROM TEAM_INFO;

TRUNCATE TABLE TEAM_INFO;

INSERT INTO MEMBER_INFO(MEMBER_CODE, MEMBER_NAME, BIRTH_DATE, DIVISION_CODE, DETAIL_INFO, CONTACT, TEAM_CODE, ACTIVE_STATUS)
VALUES (NULL, '송가인', 19900130, 1, '안녕하세요 송가인입니다~', '010-9494-9494', 1, 'H'),
(NULL, '임영웅', 19920503, null, '국민아들 임영웅입니다~', 'hero@trot.com', 1, default),
(NULL, '태진아', null, null, null, '(1급 기밀)', 3, default);

SELECT * FROM MEMBER_INFO;

INSERT INTO MEMBER_INFO(MEMBER_CODE, MEMBER_NAME, BIRTH_DATE, DIVISION_CODE, DETAIL_INFO, CONTACT, TEAM_CODE, ACTIVE_STATUS)
VALUES (NULL, '임영웅', 1992-05-03, null, '국민아들 임영웅입니다~', 'hero@trot.com', 1, default),
		(NULL, '태진아', null, null, null, '(1급 기밀)', 3, default);
        
REPLACE MEMBER_INFO VALUES (2,'임영웅', 19920503, null, '국민아들 임영웅입니다~', 'hero@trot.com', 1, default);
REPLACE MEMBER_INFO VALUES (1,'송가인', 19900130, 1, '안녕하세요 송가인입니다~', '010-9494-9494', 1, 'H');

--

use employee;


select * from employee;	-- dept_code
select * from department; -- dept_id

SELECT
	dept_id,
    count(*)
FROM
	department
WHERE dept_title LIKE '%영업%';

-- 영업부 명단
SELECT
	c.dept_title,
    a.emp_name
FROM
	employee a
LEFT JOIN (SELECT dept_id FROM department WHERE dept_title LIKE '%영업%') AS B ON b.dept_id = a.dept_code
LEFT JOIN (SELECT dept_id, dept_title FROM department WHERE dept_title LIKE '%영업%') AS C ON c.dept_id = a.dept_code
WHERE dept_title is not null;

select * from employee;	-- dept_code
select * from job;

-- 기술지원부 대리 (department 와 job / job_code, job_name)

SELECT
	emp_name,
    dept_code
from
	employee a
left join job b on a.job_code = b.job_code
where job_code = J6;

-- 인사관리부의 사원 (department 와 job / job_code, job_name)

use employee;

SELECT
	emp_name,
    phone
from
	employee;
    
SELECT
	emp_name,
    substring(phone, 1, 3),
    substring(phone, 4, 4),
    substring(phone, 8, 4)
from
	employee;
    
--

SELECT
	a.emp_name,
    b.phone
from
	employee a
join 
(
SELECT
		emp_name,
		(
        CONCAT(
			substring(phone, 1, 3),
            '-',
			substring(phone, 4, 4),
            '-',
			substring(phone, 8, 4))) AS phone
from
	employee
) b on b.emp_name = a.emp_name
where b.phone is not null;


SELECT(
        CONCAT(
			substring(phone, 1, 3),
            '-',
			substring(phone, 4, 4),
            '-',
			substring(phone, 8, 4))) AS phone
from
	employee;
    
--

select * from employee;    
    
select
	a.emp_name,
    b.hire_date,
    c.salary
from
	employee a
join (select 
		emp_name,
        concat(substring(hire_date, 1, 4), '년 ', 
				substring(hire_date, 6, 2), '월 ',
				substring(hire_date, 9, 2), '일 ') as 'hire_date'
from employee) as b
on b.emp_name = a.emp_name
join (select
		emp_name,
		concat(substring(salary, 1, 1), ',',
				substring(salary, 3, 3), ',',
                substring(salary, 5, 3)) as salary
		from employee) as c
on c.emp_name = a.emp_name;
        


