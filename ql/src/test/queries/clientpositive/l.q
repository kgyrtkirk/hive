
create table q as select custom_struct_parse(
'[{"product_held_identifier":1701441499,"active_date_time":"2017-08-1414:45:23.522000","party_visible_indicator":"N","manufacturer_legal_entity_code":"HAL","expiry_date":null,"product_held_role_classification_code":"001","seller_legal_entity_code":"HAL","load_date":null,"external_system_name":"CBS","product_held_role_start_timestamp":"2017-08-1415:45:19.837859","external_product_held_identifier_text":"1100050072500000000","product_held_role_code":"010","external_system_identifier":4,"product_held_role_classification_narrative":"FundsOwner","party_identifier":1000542355,"product_held_open_date":"2017-08-14","external_product_identifier_text":"3533116000","product_held_role_narrative":"HolderofProduct","amendment_effective_date":"2017-08-14","delete_flag":null,"product_held_close_date":null},{"product_held_identifier":1701441499,"active_date_time":"2017-08-1414:45:23.530000","party_visible_indicator":"N","manufacturer_legal_entity_code":"HAL","expiry_date":null,"product_held_role_classification_code":"001","seller_legal_entity_code":"HAL","load_date":null,"external_system_name":"CBS","product_held_role_start_timestamp":"2017-08-1415:45:19.914956","external_product_held_identifier_text":"1100050072500000000","product_held_role_code":"011","external_system_identifier":4,"product_held_role_classification_narrative":"FundsOwner","party_identifier":1000542355,"product_held_open_date":"2017-08-14","external_product_identifier_text":"3533116000","product_held_role_narrative":"FundsOwner","amendment_effective_date":"2017-08-14","delete_flag":null,"product_held_close_date":null}]'
) as f;

select f.product_held_role_narrative from q;
