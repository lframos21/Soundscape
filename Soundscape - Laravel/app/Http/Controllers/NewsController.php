<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;

class NewsController extends Controller
{
    public function ObtenerNoticias(Request $request)
    {
        $news = News::all();
        return response()->json($news);
    }
}
