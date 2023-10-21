select firstName,lastName,round(sum(amount),2) as total from payments
inner join (select firstName,lastName,customerNumber from customers
inner join (select firstName,lastName,employeeNumber from employees
where officeCode = 7 and jobTitle = 'Sales Rep'
group by firstName,lastName,employeeNumber) as Derivedtable
on customers.salesRepEmployeeNumber = Derivedtable.employeeNumber
group by firstName,lastName,customerNumber) as Derivedtable2
on payments.customerNumber = Derivedtable2.customerNumber
group by firstName,lastName