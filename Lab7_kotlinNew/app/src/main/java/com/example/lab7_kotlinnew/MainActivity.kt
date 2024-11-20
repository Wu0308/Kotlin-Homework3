package com.example.lab7_kotlinnew

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //宣告xml元件
        val spinner = findViewById<Spinner>(R.id.spinner)
        val listView = findViewById<ListView>(R.id.listView)
        val gridView = findViewById<GridView>(R.id.gridView)

        val count = ArrayList<String>()//購買數量
        val item = ArrayList<Item>()//水果資訊
        val priceRange = IntRange(10, 100)//價格範圍

        val array = resources.obtainTypedArray(R.array.image_list)

        for(index in 0 until array.length()){
            val photo = array.getResourceId(index, 0)
            val name = "水果${index+1}"
            val price = priceRange.random()
            count.add("${index+1}個")

            item.add(Item(photo, name, price))//把每個資訊丟到item陣列裡
        }

        array.recycle()

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, count)


        gridView.numColumns = 3
        gridView.adapter = MyAdapter(this, item, R.layout.adapter_vertical)
        listView.adapter = MyAdapter(this, item, R.layout.adapter_horizontal)

        var toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            toast.cancel()
            val clickedItem = item[position]
            // 显示点击的 Item 信息，使用 Toast 提示
            toast = Toast.makeText(this, "${clickedItem.name}: ${clickedItem.price}元", Toast.LENGTH_SHORT)
            toast.show()
        }
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            toast.cancel()
            val clickedItem = item[position]
            // 显示点击的 Item 信息，使用 Toast 提示
            toast = Toast.makeText(this, "${clickedItem.name}: ${clickedItem.price}元", Toast.LENGTH_SHORT)
            toast.show()
        }












    }
}