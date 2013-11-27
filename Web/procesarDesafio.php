<?php
include_once("conexion.php");

$name=$_POST['nombreEvento'];
$actividad=$_POST['selCombo'];
$dateInicio=$_POST['fecha_inicio'];
$dateFin=$_POST['fecha_fin'];
//echo $dateFin;
list($año, $mes, $dia) = split('[/.-]', $dateFin);
$fecha=$dia."/".$mes."/".$año;
//echo $fecha;
$amigoSeleccionado=$_POST['rival'];
$log=$_POST['usuario'];
//$amigo=$amigoSeleccionado.substr(0,strlen($amigoSeleccionado)-1);
//echo "ok";

$sql="insert into `desafio`(`idDesafio`,`nombre`,`fechaInicio`,`fechaFin`,`estado`,`idUsuario`,`idRival`,`idActividad`) values(null,'$name','$dateInicio','$fecha',0,$log,$amigoSeleccionado,$actividad)";
mysql_query($sql);
mysql_close();
header("Location: desafios.html");

?>