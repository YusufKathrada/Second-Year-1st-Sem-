update employees
set employeeNumber = 
(select employeeNumber from 
(select (employeeNumber+1) as employeeNumber from employees
order by employeeNumber desc
limit 1) as t)
where employeeNumber = 1625