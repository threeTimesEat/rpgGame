use employee;

SELECT
	emp_id,
    emp_name,
    dept_code,
    salary
FROM
	employee a
JOIN department b ON b.dept_id = a.dept_code
WHERE dept_title like '%영업%';

-- 서브쿼리
SELECT
	dept_id
FROM
	department
WHERE dept_title LIKE '%영업%';

-- 메인쿼리
SELECT
	emp_id,
    emp_name,
    dept_code,
    salary
FROM
	employee;

--

SELECT
	emp_id,
    emp_name,
    dept_code,
    salary
FROM
	employee a
LEFT JOIN (SELECT dept_id FROM department WHERE dept_title LIKE '%영업%') AS B ON b.dept_id = a.dept_code
WHERE dept_code is not null;

--

SELECT
	a.emp_id,
    a.emp_name,
	c.dept_title,
    a.salary
FROM
	employee a
LEFT JOIN (SELECT dept_id FROM department WHERE dept_title LIKE '%영업%') AS B ON b.dept_id = a.dept_code
LEFT JOIN (SELECT dept_id, dept_title FROM department WHERE dept_title LIKE '%영업%') AS C ON c.dept_id = a.dept_code
WHERE dept_title is not null;

SELECT * FROM nation;
SELECT * FROM LOCATION;
SELECT * FROM department;

SELECT
	NATIONAL_NAME,
    LOCAL_NAME
FROM
	nation a
JOIN location b on b.national_code = a.national_code; -- 지역명, 국가명 추출

--


SELECT
	a.dept_id,
    a.dept_title,
    b.local_name,
    c.national_name
from 
	department a
LEFT JOIN location b ON a.location_id = b.local_code
LEFT JOIN nation c ON b.national_code = c.national_code;

--

SELECT * FROM employee;

SELECT
	emp_id,
    emp_name,
    salary,
    dept_title,
    national_name
FROM
	employee a
LEFT JOIN
	(SELECT
	a.dept_id,
    a.dept_title,
    b.local_name,
    c.national_name
	from 
	department a
	LEFT JOIN location b ON a.location_id = b.local_code
	LEFT JOIN nation c ON b.national_code = c.national_code) AS b
ON a.dept_code = b.dept_id
WHERE dept_title is not null
ORDER BY national_name;

--

SELECT * FROM sal_grade;
-- 급여 등급 : sal_level / 최소 금액 : min_sal
SELECT * FROM nation;
SELECT * FROM LOCATION;
SELECT * FROM department;
SELECT * FROM employee;

SELECT
	emp_id,
    emp_name,
    salary,
    dept_title,
    national_name,
    (e.min_sal + salary) as 위로금
FROM
	employee a
LEFT JOIN
	(SELECT
	a.dept_id,
    a.dept_title,
    b.local_name,
    c.national_name
	from 
	department a
	LEFT JOIN location b ON a.location_id = b.local_code
	LEFT JOIN nation c ON b.national_code = c.national_code) AS b
ON a.dept_code = b.dept_id
LEFT JOIN
	sal_grade e on e.sal_level = a.sal_level
WHERE national_name like '%러시아%'
ORDER BY 위로금 desc;


SELECT * FROM sal_grade;
-- 급여 등급 : sal_level / 최소 금액 : min_sal




    
	



	