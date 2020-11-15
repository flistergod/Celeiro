<?php
header ('Content-type: text/html; charset=utf-8');

//multiple rest requests in only 1 php file
if(function_exists($_GET['f'])) {
   $_GET['f']();
}

//open connection 
 function OpenCon(){
   
$db_name ="cw3tc1rx_app525";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";
$conn = new mysqli($server_name, $mysql_username, $mysql_password,$db_name) or die("Connect failed: %s\n". $conn -> error);

mysqli_set_charset($conn,'utf8');
 
 return $conn;
 }

 //close connection
function CloseCon($conn){
 $conn -> close();
}

//verifies login credentials of user with username and password
//returns 'LOGIN FAILED' and MSG, if given wrong credentials
//returns 'LOGIN OK', username and ID, if given right credentials
 function login(){
 
   $conn=OpenCon();
   $username= $_POST["post_username"];
   $password= $_POST["post_password"];


//searches user with given username
  $sql="SELECT * FROM appuser WHERE usern = '$username' AND userp = '$password'";
  $result = mysqli_query($conn, $sql);
  $data=array();

//verifies if user exists
if(mysqli_num_rows($result)<=0){

    $array= array("LOGIN"=>"FAILED", "MSG"=>"Invalid data");
    echo json_encode($array);
}else{

//returns user username and ID
  while ($row=mysqli_fetch_assoc($result)) {
  $data=$row;
}
      $array= array("LOGIN"=>"OK", "MSG"=>"Valid data", "USERNAME"=>$data['usern'], "ID"=>$data['id']);
      echo json_encode($array);
}
  CloseCon($conn);
}

//returns json with user data for his profile given his username
//if the user does not exists, returns 'LOAD FAILED' and MSG
function load_data(){

  $conn=OpenCon();
  $username= $_POST["post_username"];
  // $username= "teste";

//searches for user
  $sql="SELECT * FROM appuser WHERE usern = '$username'";
  $result = mysqli_query($conn, $sql);
  $data=array();

if(mysqli_num_rows($result)>0){

//builds json array with user data
while ($row=mysqli_fetch_assoc($result)) {
  $data=$row;
}

 $array= array("LOAD"=>"OK", "USERNAME"=>$data['usern'], "NOME"=>$data['nome'], "SOBRENOME"=>$data['sobrenome'], "PASSWORD"=>$data['userp'], "EMAIL"=>$data['email'], "TM"=>$data['tm'], "RUA"=>$data['rua'], "NUMERO"=>$data['numero'], "LOCALIDADE"=>$data['localidade'], "CP"=>$data['codpostal'], "NIF"=>$data['nif']);
 echo json_encode($array);

}else{

   $array= array("LOAD"=>"FAILED", "MSG"=>"Invalid data");
    echo json_encode($array);
  }
  CloseCon($conn);
}

//function that validates given password of user if he tries to edit his data
//returns 'VALIDATE OK' if given correct password
//returns 'VALIDATE FAILED' if given wrong password
function validatePassword(){

  $conn=OpenCon();
  $username= $_POST["post_username"];

//searches user with given username
  $sql="SELECT * FROM appuser WHERE usern = '$username'";
  $result = mysqli_query($conn, $sql);
  $data=array();

if(mysqli_num_rows($result)>0){

//gets password
while ($row=mysqli_fetch_assoc($result)) {
  $data=$row;
}
    $array= array("VALIDATE"=>"OK", "PASSWORD"=>$data['userp']);
    echo json_encode($array);

}else{

    $array= array("VALIDATE"=>"FAILED", "MSG"=>"Invalid data");
    echo json_encode($array);
  }
   CloseCon($conn);
}

//edits user data with given data
//validates every important field, before editing the data
//returns 'EDIT OK' if the editing was successfull
//otherwise returns 'EDIT FAILED' and MSG
function edit_data(){

  $conn=OpenCon(); 
  $nome=$_POST["post_nome"]; 
  $username= $_POST["post_username"];
  $sobrenome=$_POST["post_sobrenome"];
  $rua=$_POST["post_rua"];
  $numero=$_POST["post_num"];
  $localidade=$_POST["post_localidade"];
  $cp=$_POST["post_cp"];
  $email=$_POST["post_email"];
  $nif=$_POST["post_nif"];
  $telemovel=$_POST["post_telemovel"];
  $password=$_POST["post_password"];

//searches user
  $sql="SELECT * FROM appuser WHERE usern = '$username'";
  $result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result)<=0){

    $array= array("EDIT"=>"FAILED", "MSG"=>"Utilizador nao encontrado");
    echo json_encode($array);


}else{

//validates important data (lines - 167-190)
  $mysql_qry="SELECT * FROM appuser WHERE email = '$email' and usern != '$username';";
  $confirm_email = mysqli_query($conn, $mysql_qry);

  $mysql_qry="SELECT * FROM appuser WHERE nif = '$nif' and usern != '$username';";
  $confirm_nif = mysqli_query($conn, $mysql_qry);

  $mysql_qry="SELECT * FROM appuser WHERE tm = '$telemovel' and usern != '$username';";
  $confirm_tlf = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($confirm_email)>0){

    $array= array("EDIT"=>"FAILED", "MSG"=>"Email utilizado por uma conta ja existente");
    echo json_encode($array);

}else if(mysqli_num_rows($confirm_nif)>0){

    $array= array("EDIT"=>"FAILED", "MSG"=>"NIF utilizado por uma conta já existente");
    echo json_encode($array);

}else if(mysqli_num_rows($confirm_tlf)>0){

    $array= array("EDIT"=>"FAILED", "MSG"=>"Telemovel utilizado por uma conta já existente");
    echo json_encode($array);

}else  {
      
    $sql2= "UPDATE appuser
            SET nome = '$nome', sobrenome = '$sobrenome', userp = '$password', email = '$email', tm = '$telemovel', 
            rua = '$rua', numero = '$numero', localidade = '$localidade', codpostal = '$cp', nif = '$nif'
            WHERE usern = '$username';";
            
    mysqli_query($conn, $sql2);

    $array= array("EDIT"=>"OK", "MSG"=>"User edit", "nome"=>$nome, "sobrenome"=>$sobrenome, "username" =>$username, "userp"=>$password, "rua"=>$rua, "email"=>$email, "tm"=>$telemovel, "numero"=>$numero, "localidade"=>$localidade, "codigopostal"=>$cp, "nif"=>$nif);

    echo json_encode($array);
    
    }
}
  CloseCon($conn);
}

//regists new user with given data
//validates important data before registing
//returns 'REGIST OK' if the creation of the new user was successfull
//returns 'REGIST FAILED' otherwise
 function regist_user(){

  $conn=OpenCon();  
  $username= $_POST["post_username"];
  $nome=$_POST["post_nome"]; 
  $sobrenome=$_POST["post_sobrenome"];
  $rua=$_POST["post_rua"];
  $numero=$_POST["post_num"];
  $localidade=$_POST["post_localidade"];
  $cp=$_POST["post_cp"];
  $email=$_POST["post_email"];
  $nif=$_POST["post_nif"];
  $telemovel=$_POST["post_telemovel"];
  $password=$_POST["post_password"];
  $whatsapp=$_POST["post_whatsapp"];
  $avatar=$_POST["post_avatar"];


//searches user
  $sql="SELECT * FROM appuser WHERE usern = '$username'";
  $result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result)>0){

    $array= array("REGISTER"=>"FAILED", "MSG"=>"Nome de utilizador ja existente");
    echo json_encode($array);

}else{

//validates important data
  $mysql_qry="SELECT * FROM appuser WHERE email = '$email';";
  $confirm_email = mysqli_query($conn, $mysql_qry);

  $mysql_qry="SELECT * FROM appuser WHERE nif = '$nif';";
  $confirm_nif = mysqli_query($conn, $mysql_qry);

  $mysql_qry="SELECT * FROM appuser WHERE tm = '$telemovel';";
  $confirm_tlf = mysqli_query($conn, $mysql_qry);

if(mysqli_num_rows($confirm_email)>0){

    $array= array("REGISTER"=>"FAILED", "MSG"=>"Email utilizado por uma conta ja existente");
    echo json_encode($array);

}else if(mysqli_num_rows($confirm_nif)>0){

    $array= array("REGISTER"=>"FAILED", "MSG"=>"NIF utilizado por uma conta já existente");
    echo json_encode($array);

}else if(mysqli_num_rows($confirm_tlf)>0){

    $array= array("REGISTER"=>"FAILED", "MSG"=>"Telemovel utilizado por uma conta já existente");
    echo json_encode($array);

}else  {

     $sql2=  "INSERT INTO appuser (nome,sobrenome,usern,userp,email,tm,whatsapp,rua,numero,localidade,codpostal,nif,avatar) 
              VALUES('$nome','$sobrenome','$username','$password','$email','$telemovel','$whatsapp','$rua','$numero','
              $localidade','$cp','$nif','$avatar');";
                      
    mysqli_query($conn, $sql2);

     $array= array("REGISTER"=>"OK", "MSG"=>"User registed", "nome"=>$nome, "sobrenome"=>$sobrenome, "username" =>$username, "userp"=>$password, "rua"=>$rua, "email"=>$email, "tm"=>$telemovel, "numero"=>$numero, "localidade"=>$localidade, "codigopostal"=>$cp, "nif"=>$nif, "whatsapp"=>$whatsapp, "avatar"=>$avatar);

    echo json_encode($array);
    
    }
}
        CloseCon($conn);
}


function getCategories(){

    $conn=OpenCon(); 
    $sql="SELECT * FROM familias";
    $result = mysqli_query($conn, $sql);
    $data=array();

    if(mysqli_num_rows($result)<=0){

       $array= array("LOAD"=>"FAILED", "MSG"=>"Error loading menu categories");
       echo json_encode($array);
    
    }else{
  
        while ($row=mysqli_fetch_assoc($result)) {

          
           $data[]=$row;
        }

        echo json_encode($data);
        }
    CloseCon($conn);
}

function getMenuItems(){

    $conn=OpenCon(); 
    $sql="SELECT * FROM menugeral";
    $result = mysqli_query($conn, $sql);
    $data=array();

    if(mysqli_num_rows($result)<=0){

       $array= array("LOAD"=>"FAILED", "MSG"=>"Error loading menu items");
       echo json_encode($array);
    
    }else{
  
        while ($row=mysqli_fetch_assoc($result)) {
           $data[]=  $row;
        }

        echo json_encode($data, JSON_UNESCAPED_UNICODE);
        }
    CloseCon($conn);
}

//sms tests
/*
function sms1(){
  
$curl = curl_init();

curl_setopt_array($curl, array(
	CURLOPT_URL => "https://nexmo-nexmo-sms-verify-v1.p.rapidapi.com/send-verification-code?phoneNumber=351938249102&brand=Celeiro",
	CURLOPT_RETURNTRANSFER => true,
	CURLOPT_FOLLOWLOCATION => true,
	CURLOPT_ENCODING => "",
	CURLOPT_MAXREDIRS => 10,
	CURLOPT_TIMEOUT => 30,
	CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
	CURLOPT_CUSTOMREQUEST => "POST",
	CURLOPT_POSTFIELDS => "",
	CURLOPT_HTTPHEADER => array(
		"content-type: application/x-www-form-urlencoded",
		"x-rapidapi-host: nexmo-nexmo-sms-verify-v1.p.rapidapi.com",
		"x-rapidapi-key: 7b3da0d917mshc6f7c8291f58b87p1780fbjsn6089082631e5"
	),
));

$response = curl_exec($curl);
$err = curl_error($curl);
curl_close($curl);
/*
 $headers = get_headers("https://nexmo-nexmo-sms-verify-v1.p.rapidapi.com/send-verification-code?phoneNumber=351938249102&brand=Celeiro");
  echo substr($headers[0], 9, 3);

if ($err) {
	echo "cURL Error #:" . $err;
} else {
	echo $response;
}
}

function sms2(){

  $curl = curl_init();
  $httpheader=array(
    "accept: application/json",
		"authorization: Basic anNibDY1NzE6Mk9aQW1qd1Y=",
		"content-type: application/json",
		"x-rapidapi-host: d7sms.p.rapidapi.com",
		"x-rapidapi-key: 86adfec49emsh18c96f718d669b5p1066d5jsnb4013ceb2ef4"
	);

curl_setopt($curl, CURLOPT_URL, "https://d7sms.p.rapidapi.com/secure/send");
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_FOLLOWLOCATION, true);
curl_setopt($curl, CURLOPT_MAXREDIRS, 10);
curl_setopt($curl, CURLOPT_TIMEOUT, 30);
curl_setopt($curl, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
curl_setopt($curl, CURLOPT_POST, "POST");
curl_setopt($curl, CURLOPT_POSTFIELDS, "{\"to\": 351938249102,\"from\": \"D7-Rapid\",\"content\": \"Test Message\"}");
curl_setopt($curl, CURLOPT_HTTPHEADER, $httpheader);

$response = curl_exec($curl);
$err = curl_error($curl);
curl_close($curl);

if ($err) {
	echo json_encode("cURL Error #:" . $err);
} else {
	echo json_encode($response);
}                              
}


function sms3(){

  $curl = curl_init();
curl_setopt_array($curl, array(
  CURLOPT_URL => "https://http-api.d7networks.com/send?username=eliz3040&password=och02mhD
                  &dlr-method=POST&dlr-url=https://4ba60af1.ngrok.io/receive&dlr=yes&dlr-level=3&to=938249102
                  &from=smsinfo&content=This%20is%20the%20sample%20content%20sent%20to%20test%20",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 0,
  CURLOPT_FOLLOWLOCATION => true,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
));
$response = curl_exec($curl);
curl_close($curl);
echo json_encode($response);
}
*/
?>
