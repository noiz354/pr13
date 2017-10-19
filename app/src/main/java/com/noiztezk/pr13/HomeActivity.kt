package com.noiztezk.pr13

import android.app.SearchManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.google.gson.Gson
import com.noiztezk.db.*
import com.noiztezk.pr13.model.Example
import com.noiztezk.pr13.presenters.HomeView
import com.noiztezk.pr13.presenters.HomeView.DEF_PR13_JSON
import com.noiztezk.pr13.presenters.HomeView.FIRST_TIME
import com.noiztezk.pr13.view.DzikirAdapter2
import com.raizlabs.android.dbflow.sql.language.Select
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by normansyahputa on 9/29/17.
 */
class HomeActivity : AppCompatActivity(), HomeView {

    private var coordinatorLayout:CoordinatorLayout? = null

    private var toolbar: Toolbar? = null

    private var appBarLayout: AppBarLayout? = null

    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var sharePreference: SharedPreferences

    @Inject
    lateinit var gson: Gson

    private lateinit var dzkrs:MutableList<com.noiztezk.pr13.model.Dzikir>

    private var person:Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        coordinatorLayout = findViewById(R.id.coor_layout_main_activity) as? CoordinatorLayout
        toolbar = findViewById(R.id.toolbar_main_activity) as? Toolbar
        appBarLayout = findViewById(R.id.appbar_main_activity) as? AppBarLayout
        recyclerView = findViewById(R.id.recylerview_main_activity) as? RecyclerView

        if(application is App){
            (application as App).mNetComponent.inject(this)
        }

        setSupportActionBar(toolbar)

        if(isFirstTime) {
            setFirstTime(salamText, false)
            insertKnownAudioType(knownAudioFormat)
            insertUnknownUser()
            coordinatorLayout?.let {
                Snackbar.make(it as View, salamText, Snackbar.LENGTH_SHORT).show()
            }

            try{
                initData()
                saveDzikirJsonToDb(dzkrs)
            }catch (ioe : IOException){
                setFirstTime(null, true)
                coordinatorLayout.let {
                    Snackbar.make(it as View, salamText, Snackbar.LENGTH_SHORT).show()
                }
            }
        }else{
            person = Select()
                    .from(Person::class.java)
                    .where(Person_Table.name.eq(getUnIdentifiedPerson(getUnIdentifiedPerson()).name))
                    .querySingle()
            Select().from(Dzikir::class.java).queryList()
        }
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_main, menu)

        val searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.action_search)) as? SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        searchView?.setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_settings -> {
                coordinatorLayout.let {
                    Snackbar.make(it as View, "setting not configured yet", Snackbar.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setupRecyclerView(){
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = DzikirAdapter2(person,dzkrs)
    }

    @Synchronized fun fromDb(dzikirLis : List<Dzikir>) : List<com.noiztezk.pr13.model.Dzikir>{
        val dzikirs = ArrayList<com.noiztezk.pr13.model.Dzikir>()
        for( dzikir in dzikirLis ){
            dzikirs.add(fromDzikirDb(dzikir))
        }
        return dzikirs
    }

    @Synchronized fun saveDzikirJsonToDb(dzikirs: List<com.noiztezk.pr13.model.Dzikir>){
        for (dzikir in dzikirs) {
            val dzikirDb = Dzikir()

            dzikirDb.dzikirName = dzikir.name
            dzikirDb.arabicDzikirText = dzikir.text
            dzikirDb.countDzikir = dzikir.count.toInt()

            var temp = ""
            val end = dzikir.read.size-1
            var count = 0

            for (read in dzikir.read){
                if (count == end){
                    temp += read
                }else{
                    temp += read + ","
                }
                count++
            }

            dzikirDb.read = temp;
            dzikirDb.save()
        }
    }

    fun initData(){
        HomeActivity.getData(this, gson)?.let {
            dzkrs = it
        }
    }

    companion object {
        @JvmStatic fun getData(context: Context, gson: Gson): MutableList<com.noiztezk.pr13.model.Dzikir>? {
            val inputStream:InputStream = context.assets.open(DEF_PR13_JSON)
            val buffer: ByteArray = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            val bufferString = String(buffer)

            return gson.fromJson(bufferString, Example::class.java).dzkir
        }

        @JvmStatic fun fromDzikirDb(dzikirDb: Dzikir) : com.noiztezk.pr13.model.Dzikir{
            val dzikir = com.noiztezk.pr13.model.Dzikir()
            dzikir.name = dzikirDb.dzikirName
            dzikir.text = dzikirDb.arabicDzikirText
            dzikir.count = dzikirDb.countDzikir.toString()

            var result = ArrayList<String>()
            result.addAll(dzikirDb.read.split(","))

            dzikir.read = result
            return dzikir
        }

        @JvmStatic fun fromDzikirNameDb(name: String) = Select().from(Dzikir::class.java)
                    .where(Dzikir_Table.dzikirName.eq(name)).querySingle()

        @JvmStatic fun fromDzikirName(name: String): com.noiztezk.pr13.model.Dzikir? {
            val dzikir = Select().from(Dzikir::class.java)
                    .where(Dzikir_Table.dzikirName.eq(name)).querySingle()
            dzikir?.let {
                return fromDzikirDb(dzikir)
            }

            return null
        }
    }

    fun insertUnknownUser(){
        person = getUnIdentifiedPerson(resources.getStringArray(R.array.unknown_user))
        person?.save()
    }

    private fun getUnIdentifiedPerson(user:Array<String>): Person{
        val person = Person()

        val name = 0
        val age = 1
        val email = 2
        val deviceId = 3

        for(findHere in user){
            val split = findHere.split("_")
            val type = split[0].toInt()
            val value = split[1]
            when(type){
                name -> person.name = value
                age -> person.age = value
                email -> person.email = value
                deviceId -> person.deviceId = value
            }
        }

        return person
    }

    fun insertKnownAudioType(audioFormat: Array<String>){
        for (audioKnownType in audioFormat) {
            val audioType = AudioType()
            audioType.audioExtension = audioKnownType
            audioType.save()
        }
    }

    fun getUnIdentifiedPerson() = resources.getStringArray(R.array.unknown_user)

    override fun getKnownAudioFormat(): Array<String> = resources.getStringArray(R.array.audio_format)

    override fun getSalamText() =  getString(R.string.salam)

    override fun isFirstTime() = sharePreference.getString(FIRST_TIME, "").equals("")

    override fun setFirstTime(firstTimeText: String?, firstTime: Boolean) {
        if(isFirstTime){
            sharePreference.edit().remove(FIRST_TIME).apply()
        }else{
            sharePreference.edit().putString(FIRST_TIME, firstTimeText).apply()
        }
    }


}