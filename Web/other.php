<?php

include_once("conexion.php");

$result = mysql_query($_POST['consulta']);
while($obj = mysql_fetch_object($result)) {
$arr[] = $obj;
}
$datos = json_encode($arr);

echo $datos;

/*
if(isset($_POST['data']))
{
$tipo = $_POST['tipo'];
$data = $_POST['data'];
$jsonArray= json_encode($data, true);

	if($tipo=="SELECT"){
	echo "SELECT";
	}
	if($tipo=="INSERT"){
    
    echo "INSERT";
	}
}
*/
?>