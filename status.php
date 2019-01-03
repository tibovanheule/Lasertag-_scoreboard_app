<?php
header('Content-Type: application/json');
/*
require_once "connect.class.php";
$conn = (new connect())->laserConn();
$sql = "SELECT id,AG FROM Status ORDER BY AG DESC;";
$result = $conn->query($sql);
$table = array("eerste", "tweede", "derde", "vierde", "vijfde");
$json->verdiepen = array();
while ($row = $result->fetch_assoc()) {
    $verdiep = null;
    $verdiep->message = "Het " . $table[$row['id'] - 1] . " verdiep, heeft " . $row['AG'] . " spellen gewonnen.";
    array_push($json->verdiepen, $verdiep);
}
$sql = "SELECT id,AG FROM Status ORDER BY AG desc LIMIT 1;";
$result = $conn->query($sql);
$table = array("eerste", "tweede", "derde", "vierde", "vijfde");
$row = $result->fetch_assoc();
$verdiep = null;
$verdiep->message = "Het " . $table[$row['id'] - 1] . " verdiep, met " . $row['AG'] . " gewonnen spellen heeft dus de verdiepen strijd gewonnen.";
*/
// display a message that the game is over, announce winner....
$verdiep->message= "De verdiepen strijd 2018-2019 is over, Het vierde verdiep heeft gewonnen.";
$json->verdiepen = array();
array_push($json->verdiepen, $verdiep);

echo json_encode($json);
