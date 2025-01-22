<?php

namespace App\Http\Controllers;

use App\Models\Blog;
use Illuminate\Http\Request;

class BlogController extends Controller
{
    public function ObtenerBlogs(Request $request)
    {
        $blog = Blog::all();
        return response()->json($blog);
    }
}
