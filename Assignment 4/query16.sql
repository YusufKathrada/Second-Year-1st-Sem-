select round(sum(quantityOrdered*priceEach),2) as totalCost from orderdetails
inner join (select orderNumber from orders
where customerNumber = 121) AS Derivedtable
on orderdetails.orderNumber = Derivedtable.orderNumber