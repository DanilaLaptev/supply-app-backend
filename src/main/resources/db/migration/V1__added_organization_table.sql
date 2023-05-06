CREATE TABLE organization
(
    id                bigserial primary key,
    organization_role varchar(250),
    title             varchar(250),
    email             varchar(250) UNIQUE,
    password          varchar(250),
    approved          BOOLEAN
);