insert into country(name,sign) values('USA','US');
insert into country(name,sign) values('Hungary','HU');
insert into country(name,sign) values('United Kingdom','GB');
insert into country(name,sign) values('France','FR');
insert into country(name,sign) values('Germany','DE');
insert into country(name,sign) values('Italy','IT');
insert into country(name,sign) values('Spain','ES');
insert into country(name,sign) values('China','CN');
insert into country(name,sign) values('Japan','JP');
insert into country(name,sign) values('Brazil','BR');
insert into country(name,sign) values('India','IN');
insert into country(name,sign) values('Canada','CA');
insert into country(name,sign) values('Australia','AU');
insert into country(name,sign) values('South Africa','ZA');
insert into country(name,sign) values('Betelgeuse','BET');
insert into country(name,sign) values('Earth','EARTH');
insert into country(name,sign) values('Viltvolde VI','VV');
insert into country(name,sign) values('Vogsphere','VOG');
insert into country(name,sign) values('Magrathean','MA');
insert into country(name,sign) values('Ursa Minor Beta','UMB');

insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'Sirius Cybernetics Corporation',
'Ursa Minor Beta',
1000,
(select id from country where sign='UMB'),
'123456789',
1978,
84000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'Infinite Improbability Drive',
'Ursa Minor Beta',
2000,
(select id from country where sign='UMB'),
'987654321',
-1500,
60000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'The Hitchhiker's Guide to the Galaxy',
'London',
5000,
(select id from country where sign='EARTH'),
'123123123',
1980,
100000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'Deep Thought',
'NY',
300,
(select id from country where sign='EARTH'),
'456456456',
-1000,
90000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'The Restaurant at the End of the Universe',
'Viltvolde VI',
10,
(select id from country where sign='VV'),
'789789789',
-2500,
41000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'The Vogon Constructor Fleet',
'Vogsphere',
10000,
(select id from country where sign='VOG'),
'321321321',
-3000,
8000)
;
insert into company(name,address,amount,country,tax_id,established,capitalisation) 
 values(
'Magrathea planet builder Ltd',
'Magrathea',
12000,
(select id from country where sign='MA'),
'654654654',
-5000,
42000)


