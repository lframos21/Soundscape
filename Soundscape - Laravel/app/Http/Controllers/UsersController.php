<?php

namespace App\Http\Controllers;

use App\Models\Users;
use Illuminate\Http\Request;

class UsersController extends Controller
{
    public function ObtenerUsuarios(Request $request)
    {
        $users = Users::all();
        return response()->json($users);
    }
}
