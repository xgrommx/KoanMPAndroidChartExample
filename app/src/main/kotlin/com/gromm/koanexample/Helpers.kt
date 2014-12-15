package com.gromm.koanexample

import android.view.ViewManager
import kotlinx.android.koan.__dslAddView
import com.github.mikephil.charting.charts.*
import com.github.mikephil.charting.data.*
import java.util.ArrayList
import com.github.mikephil.charting.utils.*

fun ViewManager.lineChart(init: LineChart.() -> Unit = {}) = __dslAddView({LineChart(it)}, init, this)
fun ViewManager.radarChart(init: RadarChart.() -> Unit = {}) = __dslAddView({ RadarChart(it)}, init, this)
fun ViewManager.barChart(init: BarChart.() -> Unit = {}) = __dslAddView({ BarChart(it)}, init, this)

public var RadarChart.description: String
    get() = this.toString()
    set(value) = setDescription(value)

public var RadarChart.unit: String
    get() = getUnit()
    set(value) = setUnit(value)

public var RadarChart.drawUnitsInChart: Boolean
    get() = false
    set(value) = setDrawUnitsInChart(value)

public var RadarChart.webLineWidth: Float
    get() = 0.0f
    set(value) = setWebLineWidth(value)

public var RadarChart.webLineWidthInner: Float
    get() = 0.0f
    set(value) = setWebLineWidthInner(value)

public var RadarChart.webAlpha: Int
    get() = 0
    set(value) = setWebAlpha(value)

public var RadarChart.drawYValues: Boolean
    get() = isDrawYValuesEnabled()
    set(value) = setDrawYValues(value)

public var RadarChart.data: RadarData
    get() = getData()
    set(value) = setData(value)

public var RadarDataSet.color: Int
    get() = getColor()
    set(value) = setColor(value)

public var RadarDataSet.lineWidth: Float
    get() = getLineWidth()
    set(value) = setLineWidth(value)

public var RadarDataSet.drawFilled: Boolean
    get() = isDrawFilledEnabled()
    set(value) = setDrawFilled(value)

fun RadarDataSet(list: ArrayList<Entry>, label: String, init: RadarDataSet.() -> Unit): RadarDataSet {
    val obj = com.github.mikephil.charting.data.RadarDataSet(list, label)
    obj.init()
    return obj;
}

public var XLabels.textSize: Float
    get() = getTextSize()
    set(value) = setTextSize(value)

public var YLabels.labelCount: Int
    get() = getLabelCount()
    set(value) = setLabelCount(value)

public var YLabels.textSize: Float
    get() = getTextSize()
    set(value) = setTextSize(value)

public var YLabels.drawUnitsInYLabel: Boolean
    get() = isDrawTopYLabelEntryEnabled()
    set(value) = setDrawUnitsInYLabel(value)

public var Legend.position: Legend.LegendPosition
    get() = getPosition()
    set(value) = setPosition(value)

public var Legend.xEntrySpace: Float
    get() = getXEntrySpace()
    set(value) = setXEntrySpace(value)

public var Legend.yEntrySpace: Float
    get() = getYEntrySpace()
    set(value) = setYEntrySpace(value)
