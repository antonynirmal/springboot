drop table transactions if exists;
drop table account if exists;
drop sequence if exists id_sequence;
create sequence id_sequence start with 1 increment by 1;

create table account (
        account_id bigint AUTO_INCREMENT not null,
		account_number bigint not null,
        account_name varchar(255),
		account_type varchar(255),
		balance_date Date not null,
		currency varchar(10),
		opening_available_balance decimal(15,2),
        primary key (account_id)
    );

     create table transactions (
            transactions_id bigint AUTO_INCREMENT not null,
			account_id bigint,
			value_date Date,
			debit_amount decimal(15,2) not null,
			credit_amount decimal(15, 2) not null,
            transaction_type varchar(10),
			transaction_narrative varchar(255),
            primary key (transactions_id)
        );

         alter table transactions
               add constraint FK_account_transaction
               foreign key (account_id)
               references account(account_id);

-- inserts for account table

INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('SGSavings726', '585309209','Savings','2018-11-08','SGD',84327.51);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUSavings933', '791066619','Savings','2018-11-08','SGD',88005.93);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUCurrent433', '321143048','Current','2018-11-08','SGD',38010.62);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('SGCurrent166', '347786244','Current','2018-11-08','SGD',50664.65);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUCurrent374', '680168913','Current','2018-11-08','SGD',41327.28);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUSavings938', '136056165','Savings','2018-11-08','SGD',48928.79);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUSavings253', '453963528','Savings','2018-11-08','SGD',72117.53);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUCurrent754', '334666982','Savings','2018-11-08','SGD',84327.51);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUCurrent754', '793949180','Current','2018-11-08','SGD',20588.16);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('SGCurrent294', '768759901','Current','2018-11-08','SGD',5906.55);
INSERT into account (account_name, account_number, account_type, balance_date, currency, opening_available_balance) values ('AUCurrent591', '847257972','Current','2018-11-08','SGD',92561.68);

-- inserts for transactions table
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1, '2012-01-12', 0, 9540.98,'Credit','First TransactionsService');
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 7497.82,'Credit','Second TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 5564.79,'Credit','Third TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 8136.18,'Credit','Fourth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 9442.46,'Credit','Fifth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 7614.45,'Credit','Sixth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 3311.55,'Credit','Seventh TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 9186.09,'Credit','Eighth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 1905.36,'Credit','Ninth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 197.78,'Credit','Tenth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 8430.49,'Credit','Eleventh TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (1,'2012-01-12', 0, 8.33,'Credit','Twelfth TransactionsService');

-- Second Account
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (2, '2015-01-12', 0, 9540.98,'Credit','First TransactionsService');
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (2,'2015-01-12', 0, 7497.82,'Credit','Second TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (2,'2015-01-12', 0, 5564.79,'Credit','Third TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (2,'2015-01-12', 0, 8136.18,'Credit','Fourth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative )values (2,'2015-01-12', 0, 9442.46,'Credit','Fifth TransactionsService');

-- Third Account
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (3, '2017-01-12', 0, 9540.98,'Credit','First TransactionsService');
insert into transactions (account_id, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative ) values (3,'2017-01-12', 0, 7497.82,'Credit','Second TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (3,'2017-01-12', 0, 5564.79,'Credit','Third TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (3,'2017-01-12', 0, 8136.18,'Credit','Fourth TransactionsService');
insert into transactions (account_id, value_date,  debit_amount, credit_amount, transaction_type, transaction_narrative ) values (3,'2017-01-12', 0, 9442.46,'Credit','Fifth TransactionsService');