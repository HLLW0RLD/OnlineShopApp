package com.example.app.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.api.data.ProductDTO
import com.example.app.base.BaseApp
import com.example.app.base.Product
import io.reactivex.rxjava3.core.Single

object Helper {

    fun toastShort(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun toastLong(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_LONG).show()
        }
    }

    fun hideKeyboard(activity: Activity) {
        try {
            val imm =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val view: View? = if (activity.currentFocus == null) {
                View(activity)
            } else {
                activity.currentFocus
            }
            imm.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (ignored: Throwable) {
        }
    }

    fun hideKeyboard(fragment: Fragment) {
        val activity: Activity? = fragment.activity
        activity?.let { hideKeyboard(it) }
    }

    fun convertDtoToData(dto: ProductDTO): Single<List<Product>> {
        val products = mutableListOf<Product>()
        products.add(Product(dto.category, dto.name, dto.price, dto.discount, dto.image_url))
        return Single.just(products)
    }
}