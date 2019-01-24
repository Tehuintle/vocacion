<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
 
class Servicios_modelo extends CI_Model {
	
	public function __construct(){
		parent::__construct();	

	}
   
	
    public function buscarCURP($varCurp){
    	$this->db->where('curp',$varCurp);
        $query = $this->db->get('alumnos');
        if ($query->num_rows() > 0){
            return true;
        }
        else{
            return false;
        }
    }


     public function usuarioCURP($varCurp) {
		$resp = Array();
        $q = $this->db->query("SELECT * FROM alumnos WHERE curp='".$varCurp."' ");
						  
		if ($q->num_rows() > 0){
         foreach ($q->result() as $row)
			{
			$resp[] = $row;
			}
			$q->free_result();
		}
		return $resp;
	}

	public function insertarVocacionAreas($data){
        $this->db->insert("areas", $data);
    }


    public function insertarVocacionCapacitacion($data){
        $this->db->insert("capacitacion", $data);
    }

}