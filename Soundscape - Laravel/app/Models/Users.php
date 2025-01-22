<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Users extends Model
{
    //id	nombre	email	contraseña	
    protected $table = 'users';
    protected $primaryKey = 'id';
    public $timestamps = false;
    //protected $incrementing = true;
    //campos que van a ser alterados/utilizados
    protected $fillable = [

        'nombre',
        'email',
        'contraseña',
        
    ];
}
