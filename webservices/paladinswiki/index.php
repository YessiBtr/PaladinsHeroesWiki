<?php
 
require_once 'include/DbHandler.php';
require_once 'include/PassHash.php';
require 'libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

/**
 * ----------- METHODS WITHOUT AUTHENTICATION ---------------------------------
 */
 
//Champions
$app->post('/champions', function () {
	$response = array();

	$db = new DbHandler();

	// fetching all hasil
	$result = $db->getChampions();
		//print_r($result);


	$response["error"] = false;
	$response["champions"] = array();

	// looping through result and preparing materi array
	while ($strData = $result->fetch_assoc()) {
	    $tmp = array();
	    $tmp["id"] = utf8_encode($strData["id"]);
	    $tmp["nama"] = utf8_encode($strData["nama"]);
		$tmp["role"] = utf8_encode($strData["role"]);
	    $tmp["skills1"] = utf8_encode($strData["skills1"]);
	    $tmp["skills2"] = utf8_encode($strData["skills2"]);
	    $tmp["skills3"] = utf8_encode($strData["skills3"]);
	    $tmp["skills4"] = utf8_encode($strData["skills4"]);
	    $tmp["ultimate"] = utf8_encode($strData["ultimate"]);
	    $tmp["story"] = utf8_encode($strData["story"]);
	    $tmp["summary"] = utf8_encode($strData["summary"]);
	    $tmp["image"] = utf8_encode($strData["image"]);

	    array_push($response["champions"], $tmp);
	}

	echoRespnse(200, $response);
});

//modes
$app->post('/modes', function () {
    $response = array();

    $db = new DbHandler();

    // fetching all hasil
    $result = $db->getModes();
        //print_r($result);


    $response["error"] = false;
    $response["modes"] = array();

    // looping through result and preparing materi array
    while ($strData = $result->fetch_assoc()) {
        $tmp = array();
        $tmp["id"] = utf8_encode($strData["id"]);
        $tmp["mode"] = utf8_encode($strData["mode"]);
        $tmp["description"] = utf8_encode($strData["description"]);
        $tmp["image"] = utf8_encode($strData["image"]);
        
        array_push($response["modes"], $tmp);
    }

    echoRespnse(200, $response);
});


//modes
$app->post('/patch', function () {
    $response = array();

    $db = new DbHandler();

    // fetching all hasil
    $result = $db->getPatch();
        //print_r($result);


    $response["error"] = false;
    $response["patch"] = array();

    // looping through result and preparing materi array
    while ($strData = $result->fetch_assoc()) {
        $tmp = array();
        $tmp["id"] = utf8_encode($strData["id"]);
        $tmp["judul"] = utf8_encode($strData["judul"]);
        $tmp["subjudul"] = utf8_encode($strData["subjudul"]);
        $tmp["general"] = utf8_encode($strData["general"]);
		$tmp["url"] = utf8_encode($strData["url"]);
        
        array_push($response["patch"], $tmp);
    }

    echoRespnse(200, $response);
});

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 * Daftar response
 * 200	OK
 * 201	Created
 * 304	Not Modified
 * 400	Bad Request
 * 401	Unauthorized
 * 403	Forbidden
 * 404	Not Found
 * 422	Unprocessable Entity
 * 500	Internal Server Error
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

	//print_r($response);
    echo json_encode($response);
}

$app->run();
?>