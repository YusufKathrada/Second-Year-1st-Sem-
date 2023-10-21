select productName,quantityInStock as 'quantityInstock',textDescription 
from products inner join productlines 
on products.productLine = productlines.productLines 
where quantityInStock < 100