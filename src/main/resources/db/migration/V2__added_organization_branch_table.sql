CREATE TABLE organization_branch (
    id BIGSERIAL PRIMARY KEY,
    address_name varchar(512),
    latitude float8,
    longitude float8,
    organization_id bigint,
    CONSTRAINT FK_ORGANIZATIONBRANCH_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organization(id)
);