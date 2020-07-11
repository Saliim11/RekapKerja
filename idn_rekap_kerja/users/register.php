<?php
    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    $nama = $_POST['nama_user'];
    $username = $_POST['username_user'];
    $password = md5($_POST['password_user']);
    $level = $_POST['level_user'];
    $kelas = $_POST['kelas_user'];

    $sql = "INSERT INTO users(id_user, nama_user, username_user, password_user, level_user, kelas_user) VALUES(UUID(),'$nama','$username','$password','$level','$kelas')";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Create successfully"; 
        $resp["nama_user"] = $nama;
        $resp["username_user"] = $username;
        $resp["level_user"] = $level;
        $resp["kelas_user"] = $kelas;
    }else{
        $resp["status"] = "0";
        $resp["message"] = "Create not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);
?>