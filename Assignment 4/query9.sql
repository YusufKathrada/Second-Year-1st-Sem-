select customerNumber, round(sum(amount),2) as 'total' from payments
group by customerNumber