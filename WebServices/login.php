<?php

/*
 * Following code will authenticate login detials
 * A users is identified by email and password
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/include/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_POST["email"]) && isset($_POST["password"])) {
    $password = $_POST['password'];
	$email = $_POST['email'];

    // get a users from users table
    $result = mysql_query("SELECT * FROM user WHERE email = '$email' && password = '$password'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

           $result = mysql_fetch_array($result);

           
            // success
            $response["success"] = 1;

            // user node
            $response["name"] = $result["name"];
			$response["ID"] = $result["user_id"];
            

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Incorrect Credentials";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no users found
        $response["success"] = 0;
        $response["message"] = "Incorrect Credentials";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>