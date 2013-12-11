<?php
include_once("conexion.php");

$name=$_POST['nombreEvento'];
$actividad=$_POST['selCombo'];
echo $actividad;
$dateInicio=$_POST['fecha_inicio'];
$dateFin=$_POST['fecha_fin'];
$amigoSeleccionado=$_POST['rival'];

$sql="insert into `desafio`(`idDesafio`,`nombre`,`fechaInicio`,`fechaFin`,`estado`,`idUsuario`,`idRival`,`idActividad`) values(null,'$name','$dateInicio','$dateFin',0,$amigoSeleccionado,3,$actividad)";
mysql_query($sql);
echo $sql;
mysql_close();


?>