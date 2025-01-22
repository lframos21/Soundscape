<?php

namespace App\Http\Controllers;

use App\Models\Chart;
use Illuminate\Http\Request;

class ChartController extends Controller
{
    public function ObtenerCancion(Request $request)
    {
        $chart = Chart::all();
        return response()->json($chart);
    }
}
