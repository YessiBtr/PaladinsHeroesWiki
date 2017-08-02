<?php

class DbHandler {
 
    private $conn;
 
    function __construct() {
        require_once dirname(__FILE__) . './DbConnect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }
 
	/**
	* Fetching data champion
	*/
	public function getChampions(){
		$stmt = $this->conn->prepare("SELECT id, nama, role, skills1, skills2, skills3, skills4, ultimate, story, summary, image FROM champions ORDER BY id ASC");
		
		$stmt->execute();
		$tasks = $stmt->get_result();
        $stmt->close();
		
        return $tasks;		

	}
	/**
    * Fetching modes
    */
    public function getModes(){
        $stmt = $this->conn->prepare("SELECT id, mode, description, image FROM modes ORDER BY id ASC");
        
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        
        return $tasks;      
    }

	/**
    * Fetching patch
    */
    public function getPatch(){
        $stmt = $this->conn->prepare("SELECT id, judul, subjudul, general, url FROM patchnotes ORDER BY id ASC");
        
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        
        return $tasks;      
    }
	
}
 
?>