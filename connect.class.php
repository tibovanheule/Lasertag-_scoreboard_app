<?php

/**
 * Class to return databases connections. */
class connect
{
    # returns a connection with the lasertag database
    public function laserConn()
    {
        $con = mysqli_connect($this->servername, "<your db user>", '<your db password>', "lasertag");
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        return $con;
    }
}
