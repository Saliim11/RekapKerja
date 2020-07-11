<?php
    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    date_default_timezone_set('Asia/Jakarta');
    $tgl = date("Y-m-d H:i:s");

    $idh = $_POST['id_history'];
    $id = $_POST['id_user'];
    $nama = $_POST['nama_user'];
    $kerjaan = $_POST['nama_kerjaan'];
    $poin = $_POST['total_poin'];
    $keterangan = $_POST['keterangan'];

    $sql = "INSERT INTO history(id_history, id_user, nama_user, nama_kerjaan, total_poin, tgl_dibuat, keterangan) VALUES('$idh','$id','$nama','$kerjaan','$poin','$tgl','$keterangan')";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Create successfully";
        $resp["nama_user"] = $nama;
        $resp["nama_kerjaan"] = $kerjaan;
        $resp["total_poin"] = $poin;
        $resp["keterangan"] = $keterangan;
        
    }else{
        $resp["status"] = "0";
        $resp["message"] = "Create not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);
?>