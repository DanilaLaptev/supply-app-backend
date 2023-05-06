CREATE TABLE contact_person(
    id BIGSERIAL PRIMARY KEY,
    full_name varchar(256),
    email varchar(128),
    phone varchar(32),
    organization_branch_id bigint,
    CONSTRAINT FK_CONTACTPERSON_ON_ORGANIZATIONBRANCH FOREIGN KEY (organization_branch_id) REFERENCES organization_branch (id)
);