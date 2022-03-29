<html>
<head>
<title></title>
</head>
<body>
<?php


require_once('PEAR.php');
$user = 'scoala';
$pass = 'scoala123';
$host = 'localhost';
$db_name = 'colocviu_10';
$dsn= mysqli_connect( $host, $user, $pass, $db_name);
if ($dsn->connect_error)
{
    die('Eroare la conectare:'. $dsn->connect_error);
}

$sql = "SELECT  idav1.idan,idav1.idav as idav_1,idav2.idav as idav_2
        FROM certificare idav1,certificare idav2
        WHERE idav1.idan=idav2.idan AND idav1.idav< idav2.idav ";

$result = mysqli_query($dsn, $sql);

if (!$result)
{
    die('Interogare gresita :' . mysqli_error());
}

$cheresults = mysqli_num_rows($result);


echo '<table style="width:100%">
  <tr>
    <td>idan</td>
    <td>idav1</td>
    <td>idav2</td>
  </tr>';
for ($i = 0; $i < $cheresults; $i++)
{
  $row = mysqli_fetch_assoc($result);
  echo '<td>'.htmlspecialchars(stripslashes($row['idan'])).'</td>';
  echo '<td>'.stripslashes($row['idav_1']).'</td>';
  echo '<td>'.stripslashes($row['idav_2']).'</td></tr>';

}
echo '</table>';
?>
<br><a href="principal.html">Revenire Pagina Principala</a><br>
</body>
</html>
