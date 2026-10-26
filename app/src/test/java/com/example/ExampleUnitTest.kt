package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun test_drawing_serialization_and_deserialization() {
    val lines = listOf(
      com.example.data.model.SerializablePoint(10f, 20f),
      com.example.data.model.SerializablePoint(30f, 40f)
    )
    val stickers = listOf(
      com.example.data.model.SerializableSticker(15f, 25f, "⭐")
    )

    val json = com.example.data.model.DrawingSerializer.toJson(lines, stickers)
    assertNotNull(json)
    assertTrue(json.contains("10.0") || json.contains("10"))
    assertTrue(json.contains("⭐"))

    val deserialized = com.example.data.model.DrawingSerializer.fromJson(json)
    assertNotNull(deserialized)
    assertEquals(2, deserialized!!.lines.size)
    assertEquals(1, deserialized.stickers.size)
    assertEquals(10f, deserialized.lines[0].x)
    assertEquals("⭐", deserialized.stickers[0].emoji)
  }
}
