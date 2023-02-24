DROP PROCEDURE get_customer_name;
DELIMITER //
CREATE PROCEDURE get_customer_name (IN customer_id INT, OUT customer_name VARCHAR(50))
BEGIN
    SELECT first_name INTO customer_name FROM customer WHERE customer.customer_id = customer_id;
END //
DELIMITER ;


DROP FUNCTION get_film_count;
DELIMITER //
CREATE FUNCTION get_film_count(category_name VARCHAR(50))
    RETURNS INT
    READS SQL DATA
BEGIN
    DECLARE count INT;
    SELECT COUNT(*) INTO count
    FROM film
    WHERE film_id IN (
        SELECT film_id
        FROM film_category
        WHERE category_id = (
            SELECT category_id
            FROM category
            WHERE name = category_name
        )
    );
    RETURN count;
END //
DELIMITER ;

call get_customer_name(1,@a);
select @a;

SELECT get_film_count('Comedy');