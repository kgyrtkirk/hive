create temporary table t3 (receipt_required_flag string, inspection_required_flag string);

explain extended select  CASE
WHEN concat(concat(coalesce(t3.receipt_required_flag, 'N'), '~'), coalesce(t3.inspection_required_flag, 'N')) = 'N~N' THEN '2'
WHEN concat(concat(coalesce(t3.receipt_required_flag, 'N'), '~'), coalesce(t3.inspection_required_flag, 'N')) = 'Y~N' THEN '3'
WHEN concat(concat(coalesce(t3.receipt_required_flag, 'N'), '~'), coalesce(t3.inspection_required_flag, 'N')) = 'Y~Y' THEN '4'
END from t3;
