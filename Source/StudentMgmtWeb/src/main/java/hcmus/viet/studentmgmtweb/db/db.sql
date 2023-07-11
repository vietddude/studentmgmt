CREATE DATABASE StudentManagement
USE StudentManagement

CREATE TABLE student (
                         id CHAR(5) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         grade FLOAT NOT NULL,
                         birthday DATE NOT NULL,
                         address VARCHAR(255) NOT NULL,
                         notes VARCHAR(255),
                         PRIMARY KEY (id)
);

CREATE TABLE course (
                        id CHAR(5) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        lecture VARCHAR(100) NOT NULL,
                        year INT NOT NULL,
                        notes VARCHAR(255),
                        PRIMARY KEY (id)
);

CREATE TABLE enrollment (
                            student_id CHAR(5) NOT NULL,
                            course_id CHAR(5) NOT NULL,
                            year INT NOT NULL,
                            grade FLOAT NOT NULL,
                            PRIMARY KEY (student_id, course_id),
                            FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
                            FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

INSERT INTO student (id, name, grade, birthday, address, notes)
VALUES
    ('S0001', 'John Doe', 3.5, '1999-05-10', '123 Main St', ''),
    ('S0002', 'Jane Smith', 4.0, '2000-01-15', '456 Elm St', ''),
    ('S0003', 'Bob Johnson', 2.8, '1998-09-01', '789 Oak Ave', ''),
    ('S0004', 'Alice Brown', 3.2, '1999-12-20', '234 Maple Rd', ''),
    ('S0005', 'Tom Wilson', 3.9, '1997-06-30', '567 Pine Blvd', ''),
    ('S0006', 'Sarah Lee', 3.6, '2001-03-05', '890 Cedar Ln', ''),
    ('S0007', 'Mike Chen', 3.1, '1998-11-12', '1234 Birch Ave', ''),
    ('S0008', 'Lily Wang', 2.5, '2002-02-28', '5678 Cedar Ave', ''),
    ('S0009', 'Kevin Zhang', 3.8, '1997-04-22', '9012 Pine Rd', ''),
    ('S0010', 'Lucy Kim', 2.9, '2001-07-07', '3456 Elm Blvd', '');


INSERT INTO course (id, name, lecture, year, notes)
VALUES
    ('C0001', 'Introduction to Computer Science', 'Dr. Smith', 2021, ''),
    ('C0002', 'Calculus I', 'Dr. Johnson', 2022, ''),
    ('C0003', 'English Composition', 'Prof. Lee', 2021, ''),
    ('C0004', 'Organic Chemistry', 'Dr. Brown', 2022, ''),
    ('C0005', 'World History', 'Prof. Garcia', 2021, ''),
    ('C0006', 'Data Structures and Algorithms', 'Dr. Chen', 2022, ''),
    ('C0007', 'Physics I', 'Dr. Wang', 2021, ''),
    ('C0008', 'Business Ethics', 'Prof. Kim', 2022, ''),
    ('C0009', 'Spanish I', 'Prof. Rodriguez', 2021, ''),
    ('C0010', 'Art History', 'Prof. Davis', 2022, '');


INSERT INTO enrollment (student_id, course_id, year, grade)
VALUES
    ('S0001', 'C0001', 2021, 3.7),
    ('S0001', 'C0004', 2021, 3.4),
    ('S0001', 'C0007', 2021, 3.9),
    ('S0002', 'C0002', 2022, 4.0),
    ('S0002', 'C0005', 2021, 3.8),
    ('S0002', 'C0008', 2022, 3.5),
    ('S0003', 'C0003', 2021, 2.9),
    ('S0003', 'C0006', 2022, 3.1),
    ('S0004', 'C0001', 2021, 3.2),
    ('S0004', 'C0004', 2022, 2.8),
    ('S0004', 'C0007', 2021, 3.5),
    ('S0005', 'C0002', 2022, 3.9),
    ('S0005', 'C0005', 2021, 4.0),
    ('S0005', 'C0008', 2022, 3.7),
    ('S0006', 'C0003', 2021, 3.6),
    ('S0006', 'C0006', 2022, 3.2),
    ('S0006', 'C0009', 2021, 3.8),
    ('S0007', 'C0001', 2021, 3.1),
    ('S0007', 'C0004', 2022, 2.9),
    ('S0007', 'C0007', 2021, 2.8),
    ('S0008', 'C0002', 2022, 2.5),
    ('S0008', 'C0005', 2021, 3.1),
    ('S0008', 'C0008', 2022, 3.3),
    ('S0009', 'C0003', 2021, 3.8),
    ('S0009', 'C0006', 2022, 3.6),
    ('S0009', 'C0009', 2021, 3.5),
    ('S0010', 'C0002', 2022, 2.9),
    ('S0010', 'C0010', 2022, 3.2);
