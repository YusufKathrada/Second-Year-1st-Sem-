Select customers.customerName as 'CustomerName', round(sum(amount),2) as 'total'
from payments join customers
on payments.customerNumber = customers.customerNumber
group by customers.customerName