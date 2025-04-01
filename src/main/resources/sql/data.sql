insert into country(name, sign) values (,);



public static final List<Country> COUNTRIES=List.of(

INSERT INTO country (name, sign) VALUES ('USA', 'US');
INSERT INTO country (name, sign) VALUES ('Hungary', 'HU');
INSERT INTO country (name, sign) VALUES ('United Kingdom', 'GB');
INSERT INTO country (name, sign) VALUES ('France', 'FR');
INSERT INTO country (name, sign) VALUES ('Germany', 'DE');
INSERT INTO country (name, sign) VALUES ('Italy', 'IT');
INSERT INTO country (name, sign) VALUES ('Spain', 'ES');
INSERT INTO country (name, sign) VALUES ('China', 'CN');
INSERT INTO country (name, sign) VALUES ('Japan', 'JP');
INSERT INTO country (name, sign) VALUES ('Brazil', 'BR');
INSERT INTO country (name, sign) VALUES ('India', 'IN');
INSERT INTO country (name, sign) VALUES ('Canada', 'CA');
INSERT INTO country (name, sign) VALUES ('Australia', 'AU');
INSERT INTO country (name, sign) VALUES ('South Africa', 'ZA');
INSERT INTO country (name, sign) VALUES ('Betelgeuse', 'BET');
INSERT INTO country (name, sign) VALUES ('Earth', 'EARTH');
INSERT INTO country (name, sign) VALUES ('Viltvolde VI', 'VV');
INSERT INTO country (name, sign) VALUES ('Vogsphere', 'VOG');
INSERT INTO country (name, sign) VALUES ('Magrathean', 'MA');
INSERT INTO country (name, sign) VALUES ('Ursa Minor Beta', 'UMB');



insert into company(name, address, amount, country)
 values(
"Sirius Cybernetics Corporation",
"Ursa Minor Beta",
1000,
(select id from country where sign = 'UMB'),
'123456789',
1978,
84000)

insert into company(name, address, amount, country)
 values(
"Infinite Improbability Drive",
"Ursa Minor Beta",
2000,
(select id from country where sign = 'UMB'),
'987654321',
-1500,
60_000L)

insert into company(name, address, amount, country)
 values(
.name("The Hitchhiker's Guide to the Galaxy")
.address("London")
5000,
.country(Country.COUNTRIES.stream()
        .filter(c->c.getSign().equals("EARTH"))
        .findFirst()
        .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
'123123123',
.established(1980)
.capitalisation(100_000L)
.build(),
insert into company(name, address, amount, country)
 values(
.name("Deep Thought")
.address("NY")
300,
.country((select id from country where sign = 'EARTH'))
        .findFirst()
        .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
'456456456',
.established(-1000)
.capitalisation(90_000L)
.build(),
insert into company(name, address, amount, country)
 values(
.name("The Restaurant at the End of the Universe")
.address("Viltvolde VI")
10,
.country((select id from country where sign = 'VV'))
        .findFirst()
        .orElseThrow(()->new RuntimeException(Country.COUNTRY_NOT_FOUND)))
'789789789',
.established(-2500)
.capitalisation(41_000)
insert into company(name, address, amount, country)
 values(
"The Vogon Constructor Fleet",
"Vogsphere",
10000,
(select id from country where sign = 'VOG'),
'321321321',
-3000,
8000)

insert into company(name, address, amount, country)
 values(
"Magrathea planet builder Ltd",
"Magrathea",
12000,
(select id from country where sign = 'MA'),
'654654654',
-5000.
42000)
