create table categories (
    id uuid not null primary key default gen_random_uuid(),
    name varchar not null unique,
    created_at timestamp not null default current_timestamp
);