package com.isen


import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.isen.databinding.ActivityDetailsBinding
import kotlin.math.max


class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var currentDish: Dish? = null
    private var itemCount = 1F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(MealsActivity.SELECTED_ITEM) as? Dish
        addToBasket()
        showcontent()
        observeClick()
        refreshShopButton()
    }

    private fun showcontent() {
        binding.title.text = currentDish?.name
        binding.ingredients.text = currentDish?.ingredients?.map{ it.name }?.joinToString(", ")
        binding.viewPager.adapter=currentDish?.images?.let { PhotoAdapter(this, it) }
    }

    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val total = price * itemCount
            binding.buttonShop.text = "${getString(R.string.total)} $total €"
            binding.quantity.text = itemCount.toInt().toString()
        }
    }

    private fun observeClick() {
        binding.buttonLess.setOnClickListener {
            itemCount = max(1f, itemCount - 1)
            refreshShopButton()
            //val count = itemCount - 1
            //if(count > 0) {
            //    itemCount = count
            //    refreshShopButton()
            //}
        }

        binding.buttonMore.setOnClickListener {
            itemCount++
            refreshShopButton()
        }


    }

    private fun addToBasket() {
        binding.buttonShop.setOnClickListener {
            currentDish?.let { dish ->
                val basket = Basket.getBasket(this)
                basket.addItem(dish, itemCount.toInt())
                basket.save(this)
                Snackbar.make(binding.root, R.string.itemAdded, Snackbar.LENGTH_SHORT).show()
                invalidateOptionsMenu()
            }
        }
    }

}