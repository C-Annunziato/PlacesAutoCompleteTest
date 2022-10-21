package com.example.placesautocompletetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*

const val TAG = "activity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
Places.initialize(applicationContext, BuildConfig.API_KEY, Locale.US)
        setAutocomplete()
    }

    private fun setAutocomplete() {
        // init support Frag
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        //customize object
        autocompleteFragment.apply {
            setPlaceFields(listOf(Place.Field.ADDRESS))
            setCountries("US")
            setTypeFilter(TypeFilter.ADDRESS)
        }

        //add listener for response

            autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {

                override fun onError(status: Status) {
                    Log.i(TAG, "An error occurred $status")
                }

                override fun onPlaceSelected(place: Place) {
                    Log.i(TAG, "this is the address ${place.address}")
                }
            })

    }
}