<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/API_Controller.php';

class Servicios_vocacion extends API_Controller{
    public function __construct() {
        parent::__construct();
        //Load Library
        $this->load->helper('language'); 
        $this->load->library('session');
        $this->load->library('user_agent'); //get IP
        //load model
        $this->load->model('servicios_modelo','',TRUE);
    }

    
    public function loginIP(){
        $response      = null;
        $varIP         = $this->input->post("IP");
        $varIPAddress  = $this->input->ip_address();
        
        if($varIP != null){
                $response = array('Estatus' => true,
                                  'Mensaje' => "Se ha conectado correctamente..",
                                  'IP'      => $varIPAddress
                                );
        }
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }


    public function loginCURP(){
        $response      = null;
        $varIP         = $this->input->post("IP");
        $varIPAddress  = $this->input->ip_address();
        
        if($varIP != null){
                $response = array('Estatus' => true,
                                  'Mensaje' => "Se ha conectado correctamente..",
                                  'IP'      => $varIPAddress
                                );
        }
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }
}