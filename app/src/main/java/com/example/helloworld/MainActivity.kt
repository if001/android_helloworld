package com.example.helloworld

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service = RakutenService.createService()
        val searchText = findViewById<EditText>(R.id.search_text)
        val searchSubmit = findViewById<Button>(R.id.search_submit)
        searchText.text?.let { author ->
            searchSubmit.setOnClickListener{
                this.currentFocus?.let {
                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
                getRanking(service, null, author.toString())
            }
        }


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        // fab.isEnabled = false
        fab.setOnClickListener {
            Toast.makeText(this, "トースト", Toast.LENGTH_LONG).show()
        }
    }



    fun getRanking(service: RakutenService, title: String?, author: String?) {
        runBlocking {
            val job = async { service.getBook(title, author) }
            job.await().body()?.let {
                val items = mutableListOf<String>()
                for (item in it.Items.iterator()) {
                    items.add(item.Item.title)
                }
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, items)
                val list = findViewById<ListView>(R.id.book_list)
                list.adapter = adapter
            }
        }
    }
}


interface RakutenService {
    @GET("BooksBook/Search/20170404?applicationId=1035362638897131844")
    suspend fun getBook(@Query("title") title: String?, @Query("author") author: String?): retrofit2.Response<SearchResult>

    companion object {
        fun createService(): RakutenService {
            val baseApiUrl = "https://app.rakuten.co.jp/services/api/"

            val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging)

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseApiUrl)
                .client(httpClientBuilder.build())
                .build()

            return retrofit.create(RakutenService::class.java)
        }
    }
}