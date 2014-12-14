package com.gromm.koanexample

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.koan.*
import android.view.Gravity
import android.view.ViewManager
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.utils.XLabels
import com.github.mikephil.charting.utils.YLabels
import com.github.mikephil.charting.utils.Legend
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import android.util.Log
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener
import com.github.mikephil.charting.charts.Chart
import kotlin.dom.getCurrentTarget
import com.github.mikephil.charting.utils.Legend.LegendForm

public class MainActivity() : Activity(), OnChartValueSelectedListener {
    override fun onValueSelected(e: Entry?, dataSetIndex: Int) {
        toast(e?.getVal().toString())
    }

    override fun onNothingSelected() {
        throw UnsupportedOperationException()
    }

    fun ViewManager.lineChart(init: LineChart.() -> Unit = {}) = __dslAddView({LineChart(it)}, init, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)

        verticalLayout {
            lineChart {
                layoutParams(width = matchParent, height = matchParent)

                var valsComp1 = listOf(Entry(100.000f, 0), Entry(50.000f, 1))
                var valsComp2 = listOf(Entry(120.000f, 0), Entry(110.000f, 1))

                var setComp1 = LineDataSet(valsComp1.toArrayList(), "Company1")
                var setComp2 = LineDataSet(valsComp2.toArrayList(), "Company2")

                var xVals = listOf("1.Q", "2.Q", "3.Q", "4.Q")
                var data = LineData(xVals.toArrayList(), listOf(setComp1, setComp2).toArrayList())

                setData(data)

                var legend = getLegend()
                legend.setForm(LegendForm.LINE)
            }
        }
    }
}