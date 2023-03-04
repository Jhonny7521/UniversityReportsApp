
CREATE TABLE FACULTIES (
    id          INTEGER NOT NULL,
    description VARCHAR2(50),
    createdAt   DATE
);

ALTER TABLE FACULTIES ADD CONSTRAINT FACULTIES_PK PRIMARY KEY ( id );

CREATE TABLE SCHOOLS (
    id               INTEGER NOT NULL,
    name             VARCHAR2(50) NOT NULL,
    numberOfStudents INTEGER DEFAULT 0,
    taxResource      NUMBER(5, 2),
    licenced         NUMBER(1) DEFAULT 0,
    clasification    INTEGER DEFAULT 0,
    createAt         DATE,
    facultiesId      INTEGER NOT NULL
);

ALTER TABLE SCHOOLS ADD CONSTRAINT SCHOOLS_PK PRIMARY KEY ( id );

ALTER TABLE SCHOOLS
    ADD CONSTRAINT SCHOOLS_FACULTIES_FK FOREIGN KEY ( facultiesId )
        REFERENCES FACULTIES ( id )
            ON DELETE CASCADE;
