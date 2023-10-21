select email from employees
inner join (select salesRepEmployeeNumber,count(salesRepEmployeeNumber) from customers
group by salesRepEmployeeNumber
having count(salesRepEmployeeNumber) < (select count(salesRepEmployeeNumber) from customers 
where salesRepEmployeeNumber = 1166)) AS Derivedtable
on employees.employeeNumber = Derivedtable.salesRepEmployeeNumber