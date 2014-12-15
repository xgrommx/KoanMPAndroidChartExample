package com.gromm.koanexample

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.koan.*
import android.view.Gravity
import android.view.ViewManager
import com.github.mikephil.charting.charts.*
import com.github.mikephil.charting.utils.*
import com.github.mikephil.charting.data.*
import android.util.Log
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Legend.LegendForm
import android.graphics.Color
import android.util.DisplayMetrics
import java.util.ArrayList
import com.github.mikephil.charting.utils.XLabels.XLabelPosition
import com.github.mikephil.charting.utils.YLabels.YLabelPosition
import com.github.mikephil.charting.utils.Legend.LegendPosition

public class MainActivity() : Activity(), OnChartValueSelectedListener {
    override fun onValueSelected(e: Entry?, dataSetIndex: Int) {
        toast(e?.getVal().toString())
    }

    override fun onNothingSelected() {

    }

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

                fun getRadarData(): RadarData {
                    var mult = 150
                    var cnt = 9

                    var mParties = array("Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H", "Party I")

                    fun generateSourceY(k: Int) =
                        ((0..cnt).map { it -> Entry(((Math.random() * mult) + mult / k).toFloat(), it) }).toArrayList()

                    var xVals = (0..cnt) map { it -> mParties[it % mParties.size] }

                    return RadarData(xVals.toArrayList(), arrayListOf(
                           RadarDataSet(generateSourceY(2), "Set1") {
                            color = Color.RED
                            lineWidth = 2f
                            drawFilled = true
                        }, RadarDataSet(generateSourceY(3), "Set2") {
                            color = Color.GREEN
                            lineWidth = 2f
                            drawFilled = true
                        }, RadarDataSet(generateSourceY(4), "Set3") {
                            color = Color.BLUE
                            lineWidth = 2f
                            drawFilled = true
                        }, RadarDataSet(generateSourceY(5), "Set4") {
                            color = Color.CYAN
                            lineWidth = 2f
                            drawFilled = true
                        }))
                }

                description = ""
                unit = " $"
                drawUnitsInChart = true
                webLineWidth = 1.5f
                webLineWidthInner = 0.75f
                webAlpha = 100
                drawYValues = true

                data = getRadarData()

                highlightValues(null)

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