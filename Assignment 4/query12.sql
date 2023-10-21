SELECT customerName,phone FROM customers
LEFT JOIN payments 
ON customers.customerNumber = payments.customerNumber
WHERE payments.customerNumber IS NULL;