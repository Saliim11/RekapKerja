<?php
    include "../../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    date_default_timezone_set('Asia/Jakarta');
    $tgl = date("Y-m-d H:i:s");

    $id = $_POST['id_user'];
    $poin = $_POST['total_poin'];

    $sql = "INSERT INTO stats_bulanan(id_stats_bulanan, id_user, total_poin, tgl_dibuat) VALUES(UUID(),'$id','$poin','$tgl')";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Create successfully"; 
        $resp["total_poin"] = $poin;
        
    }else{
        $resp["status"] = "0";
        $resp["message"] = "Create not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);
?>