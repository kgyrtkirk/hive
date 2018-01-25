DESCRIBE FUNCTION java_read;
DESCRIBE FUNCTION EXTENDED java_read;


select json_read('[{"name":"john","alias":"j","address":{"city":"LA"}}},{"name":"kinga","alias":"binga","age":2}]',
		'array<struct<name:string,age:int,alias:string,address:struct<city:string,street:string>>>');

create table t (info array<struct<name:string,age:int,alias:string,address:struct<city:string,street:string>>>);

insert into t
	select json_read('[{"name":"john","alias":"j","address":{"city":"LA"}}},{"name":"kinga","alias":"binga","age":2}]',
			'array<struct<name:string,age:int,alias:string,address:struct<city:string,street:string>>>');

