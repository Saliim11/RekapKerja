<?php
    include "../../connect.php";
    $con = mysqli_connect (HOST, USER, PASSWORD, DATABASE);

    $id = $_GET ['id_stats_mingguan'];

    $sql = "DELETE from stats_mingguan WHERE id_stats_mingguan = '$id'";

    $result = mysqli_query($con,$sql);

    $response = array();

    if($result) {
        $resp["status"] = "1";
        $resp["message"] = "hapus sukses";
    }else{
        $resp["status"] = "0";
        $resp["message"] = "hapus gagal";
    }

    $response = $resp;
    echo json_encode($response);
    mysqli_close($con);
?>