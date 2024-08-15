create table products (
    id uuid not null primary key default gen_random_uuid(),
    name varchar not null unique,
    height integer,
    width integer,
    weight integer,
    color varchar,
    category_id uuid not null,
    created_at timestamp not null default current_timestamp,
    constraint fk_products_category_id
        foreign key(category_id)
	    references categories(id)
	    on delete cascade
);