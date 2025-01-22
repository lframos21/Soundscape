<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Chart extends Model
{
    //id	Nombre_Cancion	Nombre_Artista	
    protected $table = 'charts';
    protected $primaryKey = 'id';
    public $timestamps = false;
    //protected $incrementing = true;
    //campos que van a ser alterados/utilizados
    protected $fillable = [
         
        'Puesto',
        'Nombre_Cancion',
        'Nombre_Artista',
        
    ];
}
