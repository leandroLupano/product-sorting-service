-- Insert Example Products
INSERT INTO product (id, name, sales_units) VALUES (1, 'V-NECH BASIC SHIRT', 100);
INSERT INTO product (id, name, sales_units) VALUES (2, 'CONTRASTING FABRIC T-SHIRT', 50);
INSERT INTO product (id, name, sales_units) VALUES (3, 'RAISED PRINT T-SHIRT', 80);
INSERT INTO product (id, name, sales_units) VALUES (4, 'PLEATED T-SHIRT', 3);
INSERT INTO product (id, name, sales_units) VALUES (5, 'CONTRASTING LACE T-SHIRT', 650);
INSERT INTO product (id, name, sales_units) VALUES (6, 'SLOGAN T-SHIRT', 20);

-- Insert Example Product Stocks
INSERT INTO product_stocks (product_id, size, quantity) VALUES (1, 'S', 4);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (1, 'M', 9);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (1, 'L', 0);

INSERT INTO product_stocks (product_id, size, quantity) VALUES (2, 'S', 35);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (2, 'M', 9);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (2, 'L', 9);

INSERT INTO product_stocks (product_id, size, quantity) VALUES (3, 'S', 20);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (3, 'M', 2);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (3, 'L', 20);

INSERT INTO product_stocks (product_id, size, quantity) VALUES (4, 'S', 25);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (4, 'M', 30);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (4, 'L', 10);

INSERT INTO product_stocks (product_id, size, quantity) VALUES (5, 'S', 0);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (5, 'M', 1);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (5, 'L', 0);

INSERT INTO product_stocks (product_id, size, quantity) VALUES (6, 'S', 9);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (6, 'M', 2);
INSERT INTO product_stocks (product_id, size, quantity) VALUES (6, 'L', 5);