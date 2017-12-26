CREATE TABLE ORGANISATION(
	organisation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(16) NOT NULL,
	type BIT NOT NULL,
	created_date DATE
);

CREATE TABLE USER(
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(16) NOT NULL,
	last_name VARCHAR(16) NOT NULL,
	PHONE_NUMBER VARCHAR(24),
	organisation_id BIGINT
);

