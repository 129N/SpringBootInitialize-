set mode oracle;

--country
insert into country(id, version, name, sign)
values ((select country_seq.nextval from dual), 0,'USA', 'US');
insert into country(id,  version, name, sign)
values ((select country_seq.nextval from dual), 0,'Hungary', 'HU');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'United Kingdom', 'GB');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'France', 'FR');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Germany', 'DE');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Italy', 'IT');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Spain', 'ES');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'China', 'CN');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Japan', 'JP');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Brazil', 'BR');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'India', 'IN');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Canada', 'CA');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Australia', 'AU');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'South Africa', 'ZA');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Betelgeuse', 'BET');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Earth', 'EARTH');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Viltvolde VI', 'VV');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Vogsphere', 'VOG');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Magrathean', 'MA');
insert into country(id,  version,name, sign)
values ((select country_seq.nextval from dual), 0,'Ursa Minor Beta', 'UMB');

--client

insert into client(id,version, name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Zaphod Beeblebrox', 'Betelgeuse City', 42,
        (select id from country where sign = 'BET'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Ford Prefect', 'Betelgeuse City', 142,
        (select id from country where sign = 'BET'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Arthur Dent', 'London', 10042,
        (select id from country where sign = 'EARTH'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Tricia McMillan', 'Leshoto', 12,
        (select id from country where sign = 'ZA'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Humma Kavula', 'Viltvolde VI', 20042,
        (select id from country where sign = 'VV'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Prostenic Vogon Jeltz', 'Vogaria', 70042,
        (select id from country where sign = 'VOG'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Slartibartfast', 'Magrathea', 71,
        (select id from country where sign = 'VOG'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Marvin the Paranoid Android', 'Betelgeuse', 1,
        (select id from country where sign = 'BET'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Sirius Cybernetics Corporation', 'Ursa Minor Beta', 1000,
        (select id from country where sign = 'UMB'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Infinite Improbability Drive', 'Ursa Minor Beta', 2000,
        (select id from country where sign = 'UMB'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'The Hitchhikers Guide to the Galaxy', 'London', 5000,
        (select id from country where sign = 'EARTH'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Deep Thought', 'New York', 300,
        (select id from country where sign = 'EARTH'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'The Restaurant at the End of the Universe', 'Viltvolde VI', 10,
        (select id from country where sign = 'VV'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'The Vogon Constructor Fleet', 'Vogsphere', 10000,
        (select id from country where sign = 'VOG'));
insert into client(id, version,name, address, amount, country)
values ((select client_seq.nextval from dual), 0,'Magrathea planet builder Ltd', 'Magrathea', 12000,
        (select id from country where sign = 'MA'));

--company

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual), 0,
        (select id from client where name like 'Sirius%'),
        1978, '123456789', 84000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'Infinite%'),
        500, '234567890', 60000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'The Hitchhiker%'),
        1980, '345678901', 100000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'Deep%'),
        1000, '456789012', 90000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'The Restaurant%'),
        1500, '567890123', 41000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'The Vogon%'),
        300, '678901234', 10000);

insert into company(id, version,client_id, established, tax_id, capitalisation)
values ((select company_seq.nextval from dual),0,
        (select id from client where name like 'Magrathea%'),
        50, '789012345', 42000);

--person
insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Zaphod%'),
        '123456789012',
        DATE '1970-1-1');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Ford%'),
        '234567890123',
        DATE '1870-12-1');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Arthur%'),
        '345678901234',
        DATE '1980-1-1');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Tricia%'),
        '456789012345',
        DATE '1985-4-5');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Humma%'),
        '567890123456',
        DATE '1930-7-8');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Prostenic%'),
        '678901234567',
        DATE '1000-1-1');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Slarti%'),
        '789012345678',
        DATE '1-8-11');

insert into person(id, version,client_id, personal_id, birth_date)
values ((select person_seq.nextval from dual),0,
        (select id from client where name like 'Marvin%'),
        '890123456789',
        DATE '2005-9-11');

