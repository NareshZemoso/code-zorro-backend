create table candidate(
candidate_id bigint(20) NOT NULL AUTO_INCREMENT,
candidate_name varchar(100) NOT NULL,
candidate_address varchar(300) NOT NULL,
candidate_email varchar(100) NOT NULL,
resume longblob ,
PRIMARY KEY (candidate_id)
);

create table session(
session_id bigint(20) NOT NULL AUTO_INCREMENT,
candidate_id bigint(20) NOT NULL,
test_id bigint(20) NOT NULL,
recruiter_id varchar(100) NOT NULL,
start_session DATETIME ,
end_session DATETIME ,
PRIMARY KEY (session_id),
CONSTRAINT FK_session_candidate FOREIGN KEY(candidate_id)
REFERENCES candidate(candidate_id)
);

create table audit(
audit_id bigint(20) NOT NULL AUTO_INCREMENT,
candidate_id bigint(20) NOT NULL,
test_id bigint(20) NOT NULL,
question_id bigint(20) NOT NULL,
code longblob,
PRIMARY KEY (audit_id),
CONSTRAINT FK_audit_candidate FOREIGN KEY(candidate_id)
REFERENCES candidate(candidate_id)
);


insert into candidate
(candidate_name,candidate_address,candidate_email)
values("sreetej","bachupally","sreetej1998@gmail.com");

