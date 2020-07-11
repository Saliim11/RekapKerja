<?php
    include "../connect.php";
    $con = mysqli_connect (HOST, USER, PASSWORD, DATABASE);

    $response = array();

    $hari = $_GET["hari_kerjaan"];

    $sql = "SELECT * FROM `list_kerjaan` WHERE `hari_kerjaan` = '$hari' ORDER BY `waktu_kerjaan` ASC";

    $result = mysqli_query($con, $sql);

    foreach($result as $key => $value){
        array_push($response, $value);
    }

    print(json_encode($response));
?>