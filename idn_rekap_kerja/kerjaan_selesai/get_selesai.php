<?php
    include "../connect.php";
    $con = mysqli_connect (HOST, USER, PASSWORD, DATABASE);

    $response = array();

    $id = $_GET["id_user"];

    $sql = "SELECT * FROM `selesai` WHERE `id_user` = '$id' ORDER BY `tgl_selesai` DESC";

    $result = mysqli_query($con, $sql);

    foreach($result as $key => $value){
        array_push($response, $value);
    }

    print(json_encode($response));
?>