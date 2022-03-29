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

$sql = "SELECT functie,ROUND(AVG(salariu),2)
      FROM Angajati
      GROUP BY functie ";

$result = mysqli_query($dsn, $sql);

if (!$result)
{
    die('Interogare gresita :' . mysqli_error());
}

$cheresults = mysqli_num_rows($result);


echo '<table style="width:100%">
  <tr>
    <td>functie</td>
    <td>salariu_avg</td>

  </tr>';
for ($i = 0; $i < $cheresults; $i++)
{
  $row = mysqli_fetch_assoc($result);
  echo '<td>'.htmlspecialchars(stripslashes($row['functie'])).'</td>';
  echo '<td>'.stripslashes($row['ROUND(AVG(salariu),2)']).'</td></tr>';

}
echo '</table>';
?>

<br><a href="principal.html">Revenire Pagina Principala</a><br>
</body>
</html>
