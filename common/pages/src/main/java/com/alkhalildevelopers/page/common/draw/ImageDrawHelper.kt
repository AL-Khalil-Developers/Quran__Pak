package com.alkhalildevelopers.page.common.draw

import android.graphics.Canvas
import android.widget.ImageView
import com.alkhalildevelopers.page.common.data.PageCoordinates

interface ImageDrawHelper {
  fun draw(pageCoordinates: PageCoordinates, canvas: Canvas, image: ImageView)
}
