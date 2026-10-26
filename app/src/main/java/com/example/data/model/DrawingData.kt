package com.example.data.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = true)
data class SerializablePoint(val x: Float, val y: Float)

@JsonClass(generateAdapter = true)
data class SerializableSticker(val x: Float, val y: Float, val emoji: String)

@JsonClass(generateAdapter = true)
data class SerializableDrawing(
    val lines: List<SerializablePoint>,
    val stickers: List<SerializableSticker>
)

object DrawingSerializer {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val adapter = moshi.adapter(SerializableDrawing::class.java)

    fun toJson(lines: List<SerializablePoint>, stickers: List<SerializableSticker>): String {
        val drawing = SerializableDrawing(lines, stickers)
        return try {
            adapter.toJson(drawing)
        } catch (e: Exception) {
            ""
        }
    }

    fun fromJson(json: String): SerializableDrawing? {
        if (json.isBlank()) return null
        return try {
            adapter.fromJson(json)
        } catch (e: Exception) {
            null
        }
    }
}
