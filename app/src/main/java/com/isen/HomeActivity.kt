package com.isen
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.isen.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appetizers.setOnClickListener{
            Log.e("Closure tag", "Home Activity Closed")
            changeActivity(MealType.APPETIZERS)
        }

        binding.desserts.setOnClickListener{
            Log.e("Closure tag", "Home Activity Closed")
            changeActivity(MealType.DESSERTS)
        }

        binding.mainCourses.setOnClickListener{
            Log.e("Closure tag", "Home Activity Closed")
            changeActivity(MealType.MAIN_COURSES)
        }
    }

    private fun changeActivity (category: MealType){
        val changePage = Intent(this, MealsActivity::class.java)
        changePage.putExtra(MEALTYPE, category)
        Log.i("INFO", "End of HomeActivity")
        startActivity(changePage)
    }
    companion object{
        const val MEALTYPE= "MEALTYPE"
    }
}

