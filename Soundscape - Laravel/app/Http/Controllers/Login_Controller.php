<?php

namespace App\Http\Controllers;

use App\Models\Users;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class Login_Controller extends Controller
{
    public function login(Request $request){

        //validar nuestros datos
        $request->validate([
            'email' => 'required|email',
            'contraseña' => 'required'
        ]);
        
        //buscar nuestro usuario por email
        $usuario = Users::where('email',$request->email)->first();

        //verificar si existe
        if (!$usuario || $usuario->contraseña !== $request->contraseña) {
            return response()->json([
                'message' => 'Los Datos son Incorrectos'
            ], 401);
        }

        return response()->json(
            $usuario
        );
    

    }

    public function register(Request $request)
    {

        $validator = Validator::make($request->all(), [
            'nombre' => 'required',
            'email' => 'required|email',
            'contraseña' => 'required',

        ]);

        if ($validator->fails()) {
            $data = [
                'message' => 'Error en la validación de los datos',
                'errors' => $validator->errors(),
                'status' => 400
            ];
            return response()->json($data, 400);
        }

        $usuario = Users::create($request->all());


        if (!$usuario) {
            $data = [
                'message' => 'Error al crear la cuenta',
                'status' => 500
            ];
            return response()->json($data, 500);
        }

        $data = [
            'Usuario' => $usuario,
            'status' => 201
        ];

        return response()->json($data, 201);
    }
}