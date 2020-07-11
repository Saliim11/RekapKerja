<?php  

    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);

    $response = array();

    $id = $_GET['id_user'];
    $nama = $_GET['nama_user'];
    $username = $_GET['username_user'];
    $password = md5($_GET["password_user"]);
    $level = $_GET['level_user'];
    $kelas = $_GET['kelas_user'];

    $sql = "UPDATE users SET nama_user = '$nama', username_user = '$username', password_user = '$password', 
    level_user = '$level', kelas_user = '$kelas' WHERE id_user = '$id' ";

    $result = mysqli_query($con, $sql);

    if ($result) {
        $resp["status"] = "1";
        $resp["message"] = "Update successfully"; 
        $resp["nama_user"] = $nama;
        $resp["username_user"] = $username;
        $resp["password_user"] = $password;
        $resp["level_user"] = $level;
        $resp["kelas_user"] = $kelas;

    }else{
        $resp["status"] = "0";
        $resp["message"] = "Update not successfully"; 
    }

    $response=$resp;  
    echo json_encode($response);  

    mysqli_close($con);

?>