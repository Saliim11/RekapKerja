<?php
    include "../connect.php";
    $con = mysqli_connect (HOST, USER, PASSWORD, DATABASE);

    $id = $_GET ['id_kerjaan'];

    $sql = "DELETE from list_kerjaan WHERE id_kerjaan = '$id'";

    $result = mysqli_query($con,$sql);

    $response = array();

    if($result) {
        $resp["status"] = 1;
        $resp["message"] = "hapus sukses";
    }else{
        $resp["status"] = 0;
        $resp["message"] = "hapus gagal";
    }

    $response = $resp;
    echo json_encode($response);
    mysqli_close($con);
?>