create table employees
  (id bigint auto_increment,
   emp_name varchar(255),
   constraint pk_employee primary key (id));
insert into employees(emp_name) values ('John Doe');
insert into employees(emp_name) values ('Jack Doe');