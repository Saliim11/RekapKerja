<?php
    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    $kerjaan = $_POST['nama_kerjaan'];
    $level = $_POST['level_kerjaan'];
    $hari = $_POST['hari_kerjaan'];
    $waktu = $_POST['waktu_kerjaan'];
    $poin = $_POST['poin_kerjaan'];

    $sql = "INSERT INTO list_kerjaan(id_kerjaan, nama_kerjaan, level_kerjaan, hari_kerjaan, 
    waktu_kerjaan, poin_kerjaan) VALUES(UUID(),'$kerjaan','$level','$hari','$waktu','$poin')";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Create successfully"; 
        $resp["nama_kerjaan"] = $kerjaan;
        $resp["level_kerjaan"] = $level;
        $resp["hari_kerjaan"] = $hari;
        $resp["waktu_kerjaan"] = $waktu;
        $resp["poin_kerjaan"] = $poin;
    }else{
        $resp["status"] = "0";
        $resp["message"] = "Create not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);
?>