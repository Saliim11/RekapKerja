<?php  

    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    $id = $_GET['id_kerjaan'];
    $kerjaan = $_GET['nama_kerjaan'];
    $level = $_GET['level_kerjaan'];
    $hari = $_GET["hari_kerjaan"];
    $waktu = $_GET['waktu_kerjaan'];
    $poin = $_GET['poin_kerjaan'];

    $sql = "UPDATE list_kerjaan SET nama_kerjaan = '$kerjaan', level_kerjaan = '$level', hari_kerjaan = '$hari', 
    waktu_kerjaan = '$waktu', poin_kerjaan = '$poin' WHERE id_kerjaan = '$id' ";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Update successfully"; 
        $resp["nama_kerjaan"] = $kerjaan;
        $resp["level_kerjaan"] = $level;
        $resp["hari_kerjaan"] = $hari;
        $resp["waktu_kerjaan"] = $waktu;
        $resp["poin_kerjaan"] = $poin;

    }else{
        $resp["status"] = "0";
        $resp["message"] = "Update not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);

?>