<?php

use App\Http\Controllers\NewsController;
use App\Http\Controllers\BlogController;
use App\Http\Controllers\UsersController;
use App\Http\Controllers\Login_Controller;
use App\Http\Controllers\ChartController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::get('/listacanciones', [ChartController::class,'ObtenerCancion']);

Route::get('/listausuarios', [UsersController::class,'ObtenerUsuarios']);

Route::get('/listablogs', [BlogController::class,'ObtenerBlogs']);

Route::get('/listanoticias', [NewsController::class,'ObtenerNoticias']);

Route::post('/loginsoundscape', [Login_Controller::class,'login']);

Route::post('/registersoundscape', [Login_Controller::class,'register']);

Route::get('/test', function () {
    return response()->json(['message' => 'API funcionando']);
});