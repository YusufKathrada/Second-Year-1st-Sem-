select productCode,sum(quantityOrdered) as 'total' from orderdetails
group by productCode
order by sum(quantityOrdered) desc limit 1