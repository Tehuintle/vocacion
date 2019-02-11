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
                                  'Mensaje' => "Conexión con éxito..",
                                  'IP'      => $varIPAddress
                                );
        }
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }


    public function loginCURP(){
        $response      = null;
        $varCurp         = $this->input->post("Curp");

        $logueo = $this->servicios_modelo->usuarioCURP($varCurp);
        $validaCurp = $this->servicios_modelo->buscarCURP($varCurp);

        if (!$validaCurp) {
            $response = array('Estatus' => false, 
                                    'Mensaje' => "El Curp no existe, ingresa un curp válido");
        }
        elseif($logueo != null){
            foreach ($logueo as $row) {
                $response = array('Estatus' => true,
                                        'Mensaje' => "Has ingresado correctamente",
                                        'IdAlumno' => $row->id_alumno,
                                        'Nombre' => $row->nombre,
                                        'ApellidoP' => $row->apellido_p,
                                        'ApellidoM' => $row->apellido_m,
                                        'Grupo' => $row->grupo,
                                        'Genero' => $row->genero,
                                        'Curp' => $row->curp,
                                        'FechaRegistro' => $row->Fecha_Registro
                                    );
            }
        }
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }

    public function guardarVocacionAreas(){
        $response      = null;
        $getIdUsuario = $this->servicios_modelo->usuarioCURP($this->input->post("Curp"));
        $idUsuario = $getIdUsuario[0]->id_alumno; 
        $data['Fecha_Registro']    = date("Y-m-d H:i:s");
        $data['Curp']              = $this->input->post("Curp");
        $data['Id_alum']           = $idUsuario;
        $data['Vocacion1']         = $this->input->post("Vocacion1");
        $data['Vocacion2']         = $this->input->post("Vocacion2");
        $data['Vocacion3']         = $this->input->post("Vocacion3");

        $this->servicios_modelo->insertarVocacionAreas($data);

         $response = array('Estatus' => true, 
                                    'Mensaje' => "Datos guardados correctamente!");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);

    }


    public function guardarVocacionCapacitacion(){
        $response      = null;
        $getIdUsuario = $this->servicios_modelo->usuarioCURP($this->input->post("Curp"));
        $idUsuario = $getIdUsuario[0]->id_alumno; 
        $data['Fecha_Registro']    = date("Y-m-d H:i:s");
        $data['Curp']              = $this->input->post("Curp");
        $data['Id_alum']           = $idUsuario;
        $data['Capacitacion1']     = $this->input->post("Capacitacion1");
        $data['Capacitacion2']     = $this->input->post("Capacitacion2");
        $data['Capacitacion3']     = $this->input->post("Capacitacion3");

        $this->servicios_modelo->insertarVocacionCapacitacion($data);

         $response = array('Estatus' => true, 
                                    'Mensaje' => "Datos guardados correctamente!");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }

    public function registrarTicsTerminales(){
        $response      = null;
        $data['Id_alum']           = $this->input->post("IdAlumno");
        $data['Tableta']             = $this->input->post("Tics1");
        $data['Telefono']            = $this->input->post("Tics2");
        $data['Televisor']           = $this->input->post("Tics3");
        $data['Computadora']         = $this->input->post("Tics4");
        $data['Celular']             = $this->input->post("Tics5");
        $data['Tablets']             = $this->input->post("Tics6");
        $data['Proyector']           = $this->input->post("Tics7");
        $data['Grabadora']           = $this->input->post("Tics8");
        $data['TvInternet']          = $this->input->post("Tics9");
        $data['NavegadoresInternet'] = $this->input->post("Tics10");
        $this->servicios_modelo->insertTerminales($data);

         $response = array('Estatus' => true, 
                                    'Mensaje' => "Datos guardados correctamente!");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }


    public function registrarTicsRedes(){
        $response = null;
        $data['Id_alum']           = $this->input->post("IdAlumno");
        $data['Internet']          = $this->input->post("Tics1");
        $data['TvCable']           = $this->input->post("Tics2");
        $data['InternetTvCable']   = $this->input->post("Tics3");
        $data['RedesSociales']     = $this->input->post("Tics4");
        $data['CorreoElectronico'] = $this->input->post("Tics5");
        $data['LibrosDigitales']   = $this->input->post("Tics6");
        $this->servicios_modelo->insertRedes($data);

         $response = array('Estatus' => true, 
                                    'Mensaje' => "Datos guardados correctamente!");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }


     public function registrarTicsAlmacenamiento(){
        $response = null;
        $data['Id_alum']     = $this->input->post("IdAlumno");
        $data['USBMemoria']  = $this->input->post("Tics1");
        $data['SDMemoria']   = $this->input->post("Tics2");
        $this->servicios_modelo->insertAlmacenamiento($data);

         $response = array('Estatus' => true, 
                                    'Mensaje' => "Datos guardados correctamente!");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode ($response);
    }


}