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

$sql = "SELECT *
        FROM Zboruri
        WHERE zi='Ma' OR zi='Vi'
        ORDER BY plecare";

$result = mysqli_query($dsn, $sql);

if (!$result)
{
    die('Interogare gresita :' . mysqli_error());
}

$cheresults = mysqli_num_rows($result);


echo '<table style="width:100%">
  <tr>
    <td>Nrz</td>
    <td>De_la</td>
    <td>La</td>
    <td>Distanta</td>
    <td>Plecare</td>
    <td>Sosire</td>
    <td>Zi</td>
  </tr>';
for ($i = 0; $i < $cheresults; $i++)
{
  $row = mysqli_fetch_assoc($result);
  echo '<td>'.htmlspecialchars(stripslashes($row['nrz'])).'</td>';
  echo '<td>'.stripslashes($row['de_la']).'</td>';
  echo '<td>'.stripslashes($row['la']).'</td>';
  echo '<td>'.stripslashes($row['distanta']).'</td>';
  echo '<td>'.stripslashes($row['plecare']).'</td>';
  echo '<td>'.stripslashes($row['sosire']).'</td>';
  echo '<td>'.stripslashes($row['zi']).'</td></tr>';

}
echo '</table>';
?>

<br><a href="principal.html">Revenire Pagina Principala</a><br>
</body>
</html>
