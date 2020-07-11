<?php
    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    date_default_timezone_set('Asia/Jakarta');
    $tgl = date("Y-m-d H:i:s");

    $id = $_POST['id_user'];
    $kerjaan = $_POST['kerjaan_selesai'];
    $poin = $_POST['poin_selesai'];

    $sql = "INSERT INTO selesai(id_selesai, id_user, kerjaan_selesai, poin_selesai, tgl_selesai) VALUES(UUID(),'$id','$kerjaan','$poin','$tgl')";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Create successfully"; 
        $resp["kerjaan_selesai"] = $kerjaan;
        $resp["poin_selesai"] = $poin;
        
    }else{
        $resp["status"] = "0";
        $resp["message"] = "Create not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);
?>