<?php
if(!empty($_REQUEST['id'])&&isset($_REQUEST['id'])&& $_REQUEST['id'] < 6) {
    require_once "connect.class.php";
    $conn = (new connect())->laserConn();
    $sql = "UPDATE Status SET AG = AG + 1 WHERE id = ?;";
    $statement = $conn->prepare($sql);
    $statement->bind_param("i", $_REQUEST['id']);
    $statement->execute();
    $json->message = ($statement->affected_rows > 0) ? "Success update" : "No rows update";
    echo json_encode($json);
}else{
    $json = null;
    $json->message= "failed";
    echo json_encode($json);
}
