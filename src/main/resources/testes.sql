INSERT INTO sandbox.user (name, status) values ('daniel', true);
INSERT INTO sandbox.user (name, status) values ('Joao', true);
INSERT INTO sandbox.user (name, status) values ('Joao 44', true);

SELECT * FROM sandbox.user;

UPDATE sandbox.user SET name = 'daniel' WHERE id = 1;

START TRANSACTION;
SELECT * FROM sandbox.user WHERE status = true
    FOR UPDATE SKIP LOCKED;


CREATE TABLE sandbox.seats (
                               seat_no INT PRIMARY KEY,
                               booked ENUM('YES', 'NO') DEFAULT 'NO'
);

# generate 100 sample rows
INSERT INTO sandbox.seats (seat_no)
WITH RECURSIVE my_cte AS
                   (
                       SELECT 1 AS n
                       UNION ALL
                       SELECT 1+n FROM my_cte WHERE n<100
                   )
SELECT * FROM my_cte;

START TRANSACTION;
SELECT * FROM sandbox.seats WHERE seat_no BETWEEN 2 AND 3 AND booked = 'YES'
    FOR UPDATE SKIP LOCKED;

UPDATE sandbox.seats SET booked = 'YES' WHERE seat_no BETWEEN 2 AND 3 AND booked = 'NO';
COMMIT;
SELECT * FROM sandbox.seats