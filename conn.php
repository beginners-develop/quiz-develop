<?php

$host = "db";
$user = "root";
$pass = "";
$db   = "cee_db";
$conn = null;

try {
    $conn = new PDO("mysql:host=$host;dbname=$db;",$user,$pass);
    $conn -> setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
} catch (PDOException  $e) {
    echo "Connection failed: " . $e -> getMessage();
}
