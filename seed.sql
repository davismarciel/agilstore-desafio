CREATE TABLE IF NOT EXISTS tb_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    price NUMERIC(10, 2) NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_product_category (
    product_id UUID NOT NULL REFERENCES tb_product(id) ON DELETE CASCADE,
    category_id INTEGER NOT NULL REFERENCES tb_category(id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, category_id)
);

INSERT INTO tb_category (name) VALUES
    ('Electronics'),
    ('Books'),
    ('Clothing'),
    ('Home Appliances')
ON CONFLICT (name) DO NOTHING;

INSERT INTO tb_product (name, price, stock, description) VALUES
    ('Laptop', 1200.00, 10, 'High performance laptop'),
    ('Smartphone', 800.00, 25, 'Latest model smartphone'),
    ('T-shirt', 20.00, 100, 'Comfortable cotton T-shirt'),
    ('Blender', 150.00, 30, 'High-speed blender')
ON CONFLICT (name) DO NOTHING;

INSERT INTO tb_product_category (product_id, category_id) VALUES
    ((SELECT id FROM tb_product WHERE name = 'Laptop'), (SELECT id FROM tb_category WHERE name = 'Electronics')),
    ((SELECT id FROM tb_product WHERE name = 'Smartphone'), (SELECT id FROM tb_category WHERE name = 'Electronics')),
    ((SELECT id FROM tb_product WHERE name = 'T-shirt'), (SELECT id FROM tb_category WHERE name = 'Clothing')),
    ((SELECT id FROM tb_product WHERE name = 'Blender'), (SELECT id FROM tb_category WHERE name = 'Home Appliances'));