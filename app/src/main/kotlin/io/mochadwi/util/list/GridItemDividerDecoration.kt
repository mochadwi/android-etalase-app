/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mochadwi.util.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * A [RecyclerView.ItemDecoration] which draws dividers (along the right & bottom)
 * for [RecyclerView.ViewHolder]s.
 */
class GridItemDividerDecoration(@param:Dimension private val dividerSize: Int,
                                @ColorInt dividerColor: Int) : RecyclerView.ItemDecoration() {
    private val paint: Paint

    init {
        paint = Paint()
        paint.color = dividerColor
        paint.style = Paint.Style.FILL
    }

    constructor(context: Context,
                @DimenRes dividerSizeResId: Int,
                @ColorRes dividerColorResId: Int) : this(context.resources.getDimensionPixelSize(dividerSizeResId),
            ContextCompat.getColor(context, dividerColorResId)) {
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.isAnimating) return

        val childCount = parent.childCount
        val lm = parent.layoutManager
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val viewHolder = parent.getChildViewHolder(child)

            val right = lm!!.getDecoratedRight(child)
            val bottom = lm.getDecoratedBottom(child)
            // draw the bottom dividers
            canvas.drawRect(lm.getDecoratedLeft(child).toFloat(),
                    (bottom - dividerSize).toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint)
            // draw the right edge dividers
            canvas.drawRect((right - dividerSize).toFloat(),
                    lm.getDecoratedTop(child).toFloat(),
                    right.toFloat(),
                    (bottom - dividerSize).toFloat(),
                    paint)
        }
    }

}
