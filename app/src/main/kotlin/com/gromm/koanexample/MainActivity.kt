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
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import android.graphics.Color
import android.util.DisplayMetrics
import java.util.ArrayList
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.utils.XLabels.XLabelPosition
import com.github.mikephil.charting.utils.YLabels.YLabelPosition
import com.github.mikephil.charting.utils.Legend.LegendPosition

public class MainActivity() : Activity(), OnChartValueSelectedListener {
    override fun onValueSelected(e: Entry?, dataSetIndex: Int) {
        toast(e?.getVal().toString())
    }

    override fun onNothingSelected() {

    }

    fun ViewManager.lineChart(init: LineChart.() -> Unit = {}) = __dslAddView({LineChart(it)}, init, this)
    fun ViewManager.radarChart(init: RadarChart.() -> Unit = {}) = __dslAddView({ RadarChart(it)}, init, this)
    fun ViewManager.barChart(init: BarChart.() -> Unit = {}) = __dslAddView({ BarChart(it)}, init, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        var displaymetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)

        var widthScreen = displaymetrics.widthPixels
        var heightScreen = displaymetrics.heightPixels

        verticalLayout {
            barChart {
                layoutParams(width = widthScreen, height = heightScreen / 3)

                fun getBarData(count: Int, range: Float): BarData? {
                    var mMonths = array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec")
                    var xVals = ((0..count) map { mMonths[it % 12] }).toArrayList()

                    var yVals = ((0..count) map { BarEntry((Math.random() * (range + 1)).toFloat(), it) }).toArrayList()

                    var set = BarDataSet(yVals, "DataSet")
                    set.setBarSpacePercent(35f)

                    return BarData(xVals, arrayListOf(set))
                }

                setOnChartValueSelectedListener(this@MainActivity);

                // enable the drawing of values
                setDrawYValues(true);

                setDrawValueAboveBar(true);

                setDescription("");

                setMaxVisibleValueCount(60);

                setUnit(" €");

                setDrawGridBackground(false);
                setDrawHorizontalGrid(true);
                setDrawVerticalGrid(false);

                setValueTextSize(10f);

                setDrawBorder(false);

                var xl = getXLabels();
                xl.setPosition(XLabelPosition.BOTTOM);
                xl.setCenterXLabelText(true);

                var yl = getYLabels();
                yl.setLabelCount(8);
                yl.setPosition(YLabelPosition.BOTH_SIDED);


                setData(getBarData(12, 50f));

                var l = getLegend();
                l.setPosition(LegendPosition.BELOW_CHART_LEFT);
                l.setFormSize(8f);
                l.setXEntrySpace(4f);
            }
            lineChart {
                layoutParams(width = widthScreen, height = heightScreen / 3)
                setOnChartValueSelectedListener(this@MainActivity)

                setData(LineData(arrayListOf("1.Q", "2.Q", "3.Q", "4.Q"),
                        arrayListOf(
                                LineDataSet(arrayListOf(Entry(100.000f, 0), Entry(50.000f, 1)), "Company1"),
                                LineDataSet(arrayListOf(Entry(120.000f, 0), Entry(110.000f, 1)), "Company2"))))

                getLegend().setForm(LegendForm.LINE)
            }
            radarChart {
                layoutParams(width = widthScreen, height = heightScreen / 3)

                fun getRadarData(): RadarData? {
                    var mult = 150
                    var cnt = 9

                    var mParties = array("Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H", "Party I")

                    fun generateSourceY(k: Int) =
                        ((0..cnt).map { it -> Entry(((Math.random() * mult) + mult / k).toFloat(), it) }).toArrayList()

                    var xVals = (0..cnt) map { it -> mParties[it % mParties.size] }

                    var set1 = RadarDataSet(generateSourceY(2), "Set 1")
                    set1.setColor(Color.RED)
                    set1.setDrawFilled(true)
                    set1.setLineWidth(2f)

                    var set2 = RadarDataSet(generateSourceY(3), "Set 2")
                    set2.setColor(Color.GREEN)
                    set2.setDrawFilled(true)
                    set2.setLineWidth(2f)

                    var set3 = RadarDataSet(generateSourceY(4), "Set 3")
                    set3.setColor(Color.BLUE)
                    set3.setDrawFilled(true)
                    set3.setLineWidth(2f)

                    var set4 = RadarDataSet(generateSourceY(5), "Set 4")
                    set4.setColor(Color.CYAN)
                    set4.setDrawFilled(true)
                    set4.setLineWidth(2f)

                    return RadarData(xVals.toArrayList(), arrayListOf(set1, set2, set3, set4))
                }

                setDescription("")
                setUnit(" $")
                setDrawUnitsInChart(true)
                setWebLineWidth(1.5f)
                setWebLineWidthInner(0.75f)
                setWebAlpha(100)
                setDrawYValues(true)

                setData(getRadarData())

                highlightValues(null)

                invalidate()

                setOnChartValueSelectedListener(this@MainActivity)

                getXLabels().setTextSize(9f)

                var yl = getYLabels()

                yl.setLabelCount(5)
                yl.setTextSize(9f)
                yl.setDrawUnitsInYLabel(true)

                var l = getLegend()
                l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART)
                l.setXEntrySpace(7f)
                l.setYEntrySpace(5f)
            }
        }
    }
}