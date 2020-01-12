create table test(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    tname varchar(255) NOT NULL,
    category varchar(255) NOT NULL,
    time TIME NOT NULL,
    test_link varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

create table question(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    qname varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    level varchar(255) NOT NULL,
    prob_stmt varchar(1500) NOT NULL,
    func_desc varchar(1500) NOT NULL,
    in_fmt varchar(1500) NOT NULL,
    out_fmt varchar(1500) NOT NULL,
    sam_in varchar(1500) NOT NULL,
    sam_out varchar(1500) NOT NULL,
    explanation varchar(1500) NOT NULL,
    PRIMARY KEY(id)
);

create table accesslink(
    linkid bigint(20) NOT NULL AUTO_INCREMENT,
    accesskey varchar(255) NOT NULL,
    PRIMARY KEY(linkid)
);

CREATE TABLE test_question (
    test_id bigint(20) NOT NULL,
    question_id bigint(20) NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (test_id, question_id)
);