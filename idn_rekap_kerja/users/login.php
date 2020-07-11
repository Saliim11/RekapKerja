<?php 
    include "../connect.php";
    $con = mysqli_connect(HOST,USER,PASSWORD,DATABASE);
    
    $response = array();

        $username = $_POST['username_user'];
        $password = md5($_POST['password_user']);

        $sql = "SELECT * FROM users WHERE username_user= '$username' and password_user ='$password'";
   
        // die($sql);
        #Query the database to get the user details. 
        $leveldetails = mysqli_query($con, $sql); 

        #If no data was returned, check for any SQL errors 
        if (!$leveldetails) { 
	echo 'Could not run query: ' . mysqli_error($con); 
           		exit;
        } 
  
        #Get the first row of the results 
        $row = mysqli_fetch_row($leveldetails); 
        #Build the result array (Assign keys to the values) 
        $result_data = array( 
            'id_user' => $row[0],
            'nama_user' => $row[1],
            'username_user' => $row[2],
            'password_user' => $row[3],
            'level_user'   => $row[4],
            'kelas_user'   => $row[5]); 
	
    if (mysqli_num_rows($leveldetails) != 0) {
      $response['result'] = "1";
      $response['msg'] = "Berhasil login!!";
      $response['user'] = $result_data;
      
      }else{
      $response['result'] = "0";
      $response['msg'] = "Gagal login!!";
      }
      echo json_encode($response);
?>